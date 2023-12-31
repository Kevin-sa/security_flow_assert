package com.kevinsa.security.service.service.collect;


import com.kevinsa.security.service.dto.RequestInfoDTO;
import com.kevinsa.security.service.dto.ResponseInfoDTO;
import com.kevinsa.security.service.enums.StateMachineStatusEnums;
import com.kevinsa.security.service.service.collect.base.FilterActionUnit;
import com.kevinsa.security.service.service.collect.base.ProcessContext;
import com.kevinsa.security.service.service.bizDao.impl.FlowDataDaoServiceImpl;

import com.kevinsa.security.service.service.dao.ClickHouseFactoryService;
import org.apache.logging.log4j.util.Strings;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.regex.Pattern;


@Lazy
@Service
public class BaseExecutor<T> {

    private static final String PREFIX = "BaseExecutor ->";
    private static final Logger logger = LoggerFactory.getLogger(BaseExecutor.class);

    private Map<String, ProcessContext<T>> flowUuidData = new ConcurrentHashMap<String, ProcessContext<T>>();
    private Map<String, String> flowUuidWithHostMap = new ConcurrentHashMap<String, String>();
    private Map<String, ResponseInfoDTO> respWaitFilterMap = new ConcurrentHashMap<>();

    private static final Map<String, Class<? extends FilterActionUnit>> requestFilterActionUnitMap = new ConcurrentSkipListMap<>();
    private static final Map<String, Class<? extends FilterActionUnit>> responseFilterActionUnitMap = new ConcurrentSkipListMap<>();

    private static final Set<Class<? extends FilterActionUnit>> requestActionClassSet;
    private static final Set<Class<? extends FilterActionUnit>> responseActionClassSet;
    private static final Map<String, Pattern> patternCacheSet = new ConcurrentHashMap<String, Pattern>();

    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;

    @Autowired
    private FlowDataDaoServiceImpl FlowDataDaoService;

    @Autowired
    private ClickHouseFactoryService clickHouseFactoryService;

    static {
        Reflections requestReflections = new Reflections("com.kevinsa.security.service.service.collect.filterAction.request");
        requestActionClassSet = requestReflections.getSubTypesOf(FilterActionUnit.class);
        Reflections responseReflections = new Reflections("com.kevinsa.security.service.service.collect.filterAction.response");
        responseActionClassSet = responseReflections.getSubTypesOf(FilterActionUnit.class);
    }

    @PostConstruct
    public void init() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        clazzMapInit(requestActionClassSet, requestFilterActionUnitMap);
        clazzMapInit(responseActionClassSet, responseFilterActionUnitMap);
    }

    private void clazzMapInit(Set<Class<? extends FilterActionUnit>> actionClassSet,
                              Map<String, Class<? extends FilterActionUnit>> filterActionUnitMap) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<? extends FilterActionUnit> defaultClazz = null;
        for (Class<? extends FilterActionUnit> clazz : actionClassSet) {
            if (clazz.getName().equals("com.kevinsa.security.service.service.collect.base.FilterActionUnitTemplate")) {
                continue;
            }
            if (clazz.getName().endsWith("DefaultFilterAction")) {
                defaultClazz = clazz;
                continue;
            }
            FilterActionUnit obj = clazz.getDeclaredConstructor().newInstance();
            if (!obj.isEnable()) {
                continue;
            }
            patternCacheSet.putIfAbsent(obj.getPattern(), Pattern.compile(obj.getPattern()));
            filterActionUnitMap.put(obj.getPattern(), clazz);
        }
        if (defaultClazz != null) {
            String regex = defaultClazz.getDeclaredConstructor().newInstance().getPattern();
            filterActionUnitMap.put(regex, defaultClazz);
            patternCacheSet.putIfAbsent(regex, Pattern.compile(regex));
        }

    }


    public void requestExecute(RequestInfoDTO requestInfoDTO) {
        try {
            loadOtherFactory(requestInfoDTO, null, StateMachineStatusEnums.PLUGIN_REQUEST.getStatusId());
            for (String regex : patternCacheSet.keySet()) {
                Pattern pattern = patternCacheSet.get(regex);
                if (pattern == null) {
                    continue;
                }
                if (pattern.matcher(requestInfoDTO.getHost()).matches()) {
                    ProcessContext<T> processContext = commonExecute((T) requestInfoDTO, regex, requestFilterActionUnitMap);

                    if (processContext.isFilterResult() && Strings.isBlank(processContext.getExceptMsg())) {
                        logger.info("requestExecute filter matched uuid:" + requestInfoDTO.getUuid());
                        flowUuidWithHostMap.put(requestInfoDTO.getUuid(), requestInfoDTO.getHost());
                        if (!flowDataCache(requestInfoDTO.getUuid(), processContext)) {
                            flowDataSave(requestInfoDTO, null, processContext.getBizMsg());
                        }
                        break;
                    } else {
                        executorFinally(requestInfoDTO.getUuid());
                    }
                }
            }
        } catch (Exception e) {
            logger.error(PREFIX + "requestExecute error:", e);
        }
    }

    public void responseExecute(ResponseInfoDTO responseInfoDTO) {
        try {
            loadOtherFactory(null, responseInfoDTO, StateMachineStatusEnums.PLUGIN_RESPONSE.getStatusId());
            String host = flowUuidWithHostMap.get(responseInfoDTO.getUuid());
            if (Strings.isBlank(host)) {
                respWaitFilterMap.put(responseInfoDTO.getUuid(), responseInfoDTO);
                return;
            }
            responseCommonFilter(host, responseInfoDTO, true);
        } catch (Exception e) {
            logger.error(PREFIX + "responseExecute error:", e);
        }
    }

    private boolean responsePocketFilter(String uuid) {
        try {
            String host = flowUuidWithHostMap.get(uuid);
            if (Strings.isBlank(host)) {
                return false;
            }
            ResponseInfoDTO responseInfoDTO = respWaitFilterMap.get(uuid);
            ProcessContext<T> processContext = responseCommonFilter(host, responseInfoDTO, false);
            return processContext.isFilterResult();
        } catch (Exception e) {
            logger.error(PREFIX + "responsePocketFilter error:", e);
        }
        return false;
    }

    private ProcessContext<T> responseCommonFilter(String host, ResponseInfoDTO responseInfoDTO, boolean dataSave) throws Exception {
        ProcessContext<T> processContext = new ProcessContext<>();
        for (String regex : patternCacheSet.keySet()) {
            Pattern pattern = patternCacheSet.get(regex);
            if (pattern == null) {
                continue;
            }
            if (pattern.matcher(host).matches()) {
                processContext = commonExecute((T) responseInfoDTO, regex, responseFilterActionUnitMap);
                if ((processContext.isFilterResult() && Strings.isBlank(processContext.getExceptMsg())) && dataSave) {
                    if (!flowDataCache(responseInfoDTO.getUuid(), processContext)) {
                        flowDataSave(null, responseInfoDTO, processContext.getBizMsg());
                    }
                    break;
                }
            }
        }
        return processContext;
    }

    /**
     * unload接口
     */
    public void pluginUnload(String uuid) {
        for (String key : flowUuidData.keySet()) {
            if (key.startsWith(uuid)) flowUuidData.remove(key);
        }

        for (String key : flowUuidWithHostMap.keySet()) {
            if (key.startsWith(uuid)) flowUuidData.remove(key);
        }
    }

    /**
     * process执行器
     */
    private ProcessContext<T> commonExecute(T data, String regex, Map<String, Class<? extends FilterActionUnit>> actionUnitMap)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ProcessContext<T> processContext = new ProcessContext<T>();
        processContext.setData(data);
        processContext.setFilterResult(true);
        Class<? extends FilterActionUnit> clazz = actionUnitMap.get(regex);
        FilterActionUnit obj = clazz.getDeclaredConstructor().newInstance();
        autowireCapableBeanFactory.autowireBean(obj);
        obj.execute(processContext);
        processContext.setBizMsg(obj.getBizMsg());
        return processContext;
    }

    private boolean flowDataCache(String uuid, ProcessContext<T> processContext) {
        if (!flowUuidData.containsKey(uuid) && !respWaitFilterMap.containsKey(uuid)) {
            flowUuidData.put(uuid, processContext);
            return true;
        }
        return false;
    }

    private void flowDataSave(RequestInfoDTO requestInfoDTO, ResponseInfoDTO responseInfoDTO, String bizMsg) {
        String uuid = getUuidWithDataSave(requestInfoDTO, responseInfoDTO);
        try {
            if (requestInfoDTO == null) {
                uuid = responseInfoDTO.getUuid();
                ProcessContext<T> reqContext = flowUuidData.get(uuid);
                FlowDataDaoService.flowDataSave((RequestInfoDTO) reqContext.getData(), responseInfoDTO, bizMsg);
            } else {
                ResponseInfoDTO responseInfoDTOCache = null;
                uuid = requestInfoDTO.getUuid();
                if (respWaitFilterMap.containsKey(uuid)) {
                    if (!responsePocketFilter(uuid)) {
                        executorFinally(uuid);
                        return;
                    }
                    responseInfoDTOCache = respWaitFilterMap.get(uuid);
                } else {
                    ProcessContext<T> respContext = flowUuidData.get(uuid);
                    responseInfoDTOCache = (ResponseInfoDTO) respContext.getData();
                }
                FlowDataDaoService.flowDataSave(requestInfoDTO, responseInfoDTOCache, bizMsg);
            }
        } catch (Exception e) {
            logger.error(PREFIX + "flowDataSave error:", e);
        } finally {
            executorFinally(uuid);
        }
    }

    private String getUuidWithDataSave(RequestInfoDTO requestInfoDTO, ResponseInfoDTO responseInfoDTO) {
        if (requestInfoDTO == null) {
            return responseInfoDTO.getUuid();
        }
        return requestInfoDTO.getUuid();
    }

    private void executorFinally(String uuid) {
        flowUuidData.remove(uuid);
        flowUuidWithHostMap.remove(uuid);
        respWaitFilterMap.remove(uuid);
    }


    private void loadOtherFactory(RequestInfoDTO requestInfoDTO, ResponseInfoDTO responseInfoDTO, int statusId) {
        clickHouseFactoryService.pluginCollectEntrance(requestInfoDTO, responseInfoDTO, statusId);
    }
}
