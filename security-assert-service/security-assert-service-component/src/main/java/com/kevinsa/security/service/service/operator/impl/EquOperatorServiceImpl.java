package com.kevinsa.security.service.service.operator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kevinsa.security.service.service.operator.base.OperatorUnitTemplate;
import com.kevinsa.security.service.service.operator.engine.CommonOperatorService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EquOperatorServiceImpl<T> extends OperatorUnitTemplate<T> {

    @Autowired
    private CommonOperatorService<T> commonOperatorService;

    @Override
    protected <T extends Comparable<T>> boolean doBusiness(T left, T right) {
        if (left.getClass().getName().equals(right.getClass().getName())) {
            return commonOperatorService.compareTwoValues(left, right) == 0;
        }
        return commonOperatorService.compareTwoValues(String.valueOf(left), String.valueOf(right)) == 0;
    }
}
