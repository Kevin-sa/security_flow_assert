package com.kevinsa.security.service.service.collect;


import com.kevinsa.security.service.dto.RequestInfoDTO;
import com.kevinsa.security.service.service.collect.base.FilterActionUnit;
import com.kevinsa.security.service.service.collect.base.ProcessContext;
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
import java.util.regex.Pattern;


@Lazy
@Service
public class BaseExecutor {

    private static final String PREFIX = "BaseExecutor ->";

    private static final Logger logger = LoggerFactory.getLogger(BaseExecutor.class);

    private Map<String, String> flowUuidData = new ConcurrentHashMap<String, String>();

    private static Map<Pattern, Class<? extends  FilterActionUnit>> filterActionUnitMap = new ConcurrentHashMap<Pattern, Class<? extends  FilterActionUnit>>();

    private static Set<Class<? extends FilterActionUnit>> actionClassSet;

    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;

    static {
        Reflections reflections = new Reflections("com.kevinsa.security.service.service.collect.action");
        actionClassSet = reflections.getSubTypesOf(FilterActionUnit.class);
    }

    @PostConstruct
    public void init() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        for (Class<? extends  FilterActionUnit> clazz : actionClassSet) {
            FilterActionUnit obj = clazz.getDeclaredConstructor().newInstance();
            filterActionUnitMap.put(obj.getPattern(), clazz);
        }
    }


    public void requestExecute(RequestInfoDTO requestInfoDTO) {
        try {
            for (Pattern pattern : filterActionUnitMap.keySet()) {
                if (pattern.matcher(requestInfoDTO.getPath()).matches()) {
                    ProcessContext<RequestInfoDTO> processContext = new ProcessContext<RequestInfoDTO>();
                    processContext.setData(requestInfoDTO);
                    Class<? extends FilterActionUnit> clazz = filterActionUnitMap.get(pattern);
                    FilterActionUnit obj = clazz.getDeclaredConstructor().newInstance();
                    autowireCapableBeanFactory.autowireBean(obj);
                    obj.execute(processContext);
                }
            }

        } catch (Exception e) {
            logger.error(PREFIX + "requestExecute error:", e);
        }

    }
}
