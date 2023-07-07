package com.kevinsa.security.service.service.operator.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class OperatorUnitTemplate<T> implements OperatorUnit<T> {

    private static final Logger logger = LoggerFactory.getLogger(OperatorUnitTemplate.class);


    @Override
    public <T extends Comparable<T>> boolean execute(T left, T right) {
        try {
            paramCheck(left, right);
            return doBusiness(left, right);
        } catch (Exception e) {
            logger.error("OperatorUnitTemplate error:", e);
            return false;
        }
    }

    protected <T extends Comparable<T>> void paramCheck(T left, T right) throws Exception {
        if (left == null || right == null) {
            throw new Exception("operator is null");
        }
    }

    protected abstract <T extends Comparable<T>> boolean doBusiness(T left, T right) throws Exception;

}
