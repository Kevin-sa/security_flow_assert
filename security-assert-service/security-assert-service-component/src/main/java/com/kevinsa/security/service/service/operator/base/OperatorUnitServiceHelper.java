package com.kevinsa.security.service.service.operator.base;

import static com.kevinsa.security.service.enums.OperatorTypeEnums.EQU;
import static com.kevinsa.security.service.enums.OperatorTypeEnums.LT;
import static com.kevinsa.security.service.enums.OperatorTypeEnums.MT;
import static com.kevinsa.security.service.enums.OperatorTypeEnums.NEQ;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.kevinsa.security.service.service.operator.impl.EquOperatorServiceImpl;
import com.kevinsa.security.service.service.operator.impl.LtOperatorServiceImpl;
import com.kevinsa.security.service.service.operator.impl.MtOperatorServiceImpl;
import com.kevinsa.security.service.service.operator.impl.NeqOperatorServiceImpl;

@Lazy
@Service
public class OperatorUnitServiceHelper<T> {

    @Autowired
    private NeqOperatorServiceImpl neqOperatorService;

    @Autowired
    private EquOperatorServiceImpl equOperatorService;

    @Autowired
    private LtOperatorServiceImpl ltOperatorService;

    @Autowired
    private MtOperatorServiceImpl mtOperatorService;

    private final Map<String, OperatorUnit<T>> operatorUnitServiceMap = new HashMap<String, OperatorUnit<T>>();

    @PostConstruct
    private void initOperatorUnitServiceMap() {
        operatorUnitServiceMap.put(EQU.getType(), equOperatorService);
        operatorUnitServiceMap.put(NEQ.getType(), neqOperatorService);
        operatorUnitServiceMap.put(LT.getType(), ltOperatorService);
        operatorUnitServiceMap.put(MT.getType(), mtOperatorService);
    }


    public <T extends Comparable<T>> boolean execute(String operator, T left, T right) throws Exception {
        if (operatorUnitServiceMap.containsKey(operator)) {
            return operatorUnitServiceMap.get(operator).execute(left, right);
        }
        throw new Exception(String.format("execute operator:%s service not found", operator));
    }
}
