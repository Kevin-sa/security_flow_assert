package com.kevinsa.security.service.service.operator.engine;

import org.springframework.stereotype.Service;

@Service
public class CommonOperatorService<T> {

    public <T extends Comparable<T>> int compareTwoValues(T right, T left) {
        return right.compareTo(left);
    }
}
