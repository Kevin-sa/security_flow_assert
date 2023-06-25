package com.kevinsa.security.service.service.collect.base;

import com.sun.istack.internal.NotNull;

import java.util.regex.Pattern;

public interface FilterActionUnit<T> {

    @NotNull
    String getPattern();

    @NotNull
    String getBizMsg();

    @NotNull
    boolean isEnable();

    ProcessContext<T> execute(ProcessContext<T> processContext);
}
