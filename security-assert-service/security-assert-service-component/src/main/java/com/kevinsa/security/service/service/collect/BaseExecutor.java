package com.kevinsa.security.service.service.collect;


import com.kevinsa.security.service.dto.RequestInfoDTO;
import com.kevinsa.security.service.dto.ResponseInfoDTO;
import com.kevinsa.security.service.service.collect.base.FilterActionUnit;
import com.kevinsa.security.service.service.collect.base.ProcessContext;
import com.kevinsa.security.service.service.collect.impl.CollectDataDaoServiceImpl;

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

    private static final Map<String, Class<? extends FilterActionUnit>> requestFilterActionUnitMap = new ConcurrentSkipListMap<>();
    private static final Map<String, Class<? extends FilterActionUnit>> responseFilterActionUnitMap = new ConcurrentSkipListMap<>();

    private static final Set<Class<? extends FilterActionUnit>> requestActionClassSet;
    private static final Set<Class<? extends FilterActionUnit>> responseActionClassSet;
    private static final Map<String, Pattern> patternCacheSet = new ConcurrentHashMap<String, Pattern>();

    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;

    @Autowired
    private CollectDataDaoServiceImpl collectDataDaoService;

    static {
        Reflections requestReflections = new Reflections("com.kevinsa.security.service.service.collect.action.request");
        requestActionClassSet = requestReflections.getSubTypesOf(FilterActionUnit.class);
        Reflections responseReflections = new Reflections("com.kevinsa.security.service.service.collect.action.response");
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
            for (String regex : patternCacheSet.keySet()) {
                Pattern pattern = patternCacheSet.get(regex);
                if (pattern == null) {
                    continue;
                }
                if (pattern.matcher(requestInfoDTO.getHost()).matches()) {
                    ProcessContext<T> processContext = commonExecute((T) requestInfoDTO, regex, requestFilterActionUnitMap);

                    if (processContext.isFilterResult()) {
                        flowUuidData.put(requestInfoDTO.getUuid(), processContext);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            logger.error(PREFIX + "requestExecute error:", e);
        }
    }

    public void responseExecute(ResponseInfoDTO responseInfoDTO) {
        try {
            String host = flowUuidWithHostMap.get(responseInfoDTO.getUuid());
            if (Strings.isBlank(host)) {
                return;
            }
            for (String regex : patternCacheSet.keySet()) {
                Pattern pattern = patternCacheSet.get(regex);
                if (pattern == null) {
                    continue;
                }
                if (pattern.matcher(host).matches()) {
                    ProcessContext<T> processContext = commonExecute((T) responseInfoDTO, regex, responseFilterActionUnitMap);
                    if (processContext.isFilterResult()) {
                        ProcessContext<T> reqContext = flowUuidData.get(responseInfoDTO.getUuid());
                        collectDataDaoService.flowDataSave((RequestInfoDTO) reqContext.getData(), responseInfoDTO, processContext.getBizMsg());
                        break;
                    }
                }
            }
        } catch (Exception e) {
            logger.error(PREFIX + "responseExecute error:", e);
        } finally {
            flowUuidData.remove(responseInfoDTO.getUuid());
            flowUuidWithHostMap.remove(responseInfoDTO.getUuid());
        }
    }

    public void pluginUnload(String uuid) {
        for (String key : flowUuidData.keySet()) {
            if (key.startsWith(uuid)) flowUuidData.remove(key);
        }

        for (String key : flowUuidWithHostMap.keySet()) {
            if (key.startsWith(uuid)) flowUuidData.remove(key);
        }
    }

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
}
