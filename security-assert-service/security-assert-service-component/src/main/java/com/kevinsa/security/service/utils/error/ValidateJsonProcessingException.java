package com.kevinsa.security.service.utils.error;

import java.io.UncheckedIOException;

import com.fasterxml.jackson.core.JsonProcessingException;

public class ValidateJsonProcessingException extends UncheckedIOException {

    public ValidateJsonProcessingException(JsonProcessingException cause) {
        super(cause.getMessage(), cause);
    }
}
