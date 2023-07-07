package com.kevinsa.security.service.service.operator.base;

public interface OperatorUnit<T> {
    <T extends Comparable<T>> boolean execute(T left, T right);
}
