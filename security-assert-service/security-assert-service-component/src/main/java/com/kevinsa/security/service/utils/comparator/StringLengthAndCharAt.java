package com.kevinsa.security.service.utils.comparator;

import java.util.Comparator;

import org.springframework.stereotype.Component;


@Component
public class StringLengthAndCharAt implements Comparator<String> {

    @Override
    public int compare(String s1, String s2) {
        int lengthCompare = Integer.compare(s1.length(), s2.length());
        if (lengthCompare != 0) {
            return lengthCompare;
        }

        for (int i = 0; i < s1.length(); i++) {
            int asciiCompare = Integer.compare(s1.charAt(i), s2.charAt(i));
            if (asciiCompare != 0) {
                return asciiCompare;
            }
        }
        return 0;
    }
}
