package com.kevinsa.security.service.utils;

import java.security.MessageDigest;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HashUtils {

    public static final String PREFIX = "HashUtils execute ->";
    private static Logger logger = LoggerFactory.getLogger(HashUtils.class);

    private static final String MD5 = "MD5";

    public String md5(String msg) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MD5);
            messageDigest.update(msg.getBytes());
            byte[] digest = messageDigest.digest();
            return DatatypeConverter.printHexBinary(digest).toUpperCase();
        } catch (Exception e) {
            logger.error(PREFIX + "md5 error:", e);
            return null;
        }
    }
}
