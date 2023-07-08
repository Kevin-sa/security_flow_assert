package com.kevinsa.security.service.service.operator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.kevinsa.security.service.service.operator.base.OperatorUnitTemplate;
import com.kevinsa.security.service.service.operator.engine.CommonOperatorService;

@Lazy
@Component
public class LtOperatorServiceImpl<T> extends OperatorUnitTemplate<T> {
    @Autowired
    private CommonOperatorService<T> commonOperatorService;


    @Override
    protected <T extends Comparable<T>> boolean doBusiness(T left, T right) {
        if (left.getClass().getName().equals(right.getClass().getName())) {
            return commonOperatorService.compareTwoValues(left, right) < 0;
        }
        return commonOperatorService.compareTwoValues(Integer.parseInt((String) left)
                , Integer.parseInt((String) right)) < 0;
    }

}
