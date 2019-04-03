package com.andrei.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public final class HttpErrors {

    private HttpErrors() {
        throw new UnsupportedOperationException();
    }

    public static <T> ResponseStatusException badRequestException(String reason) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, reason);
    }

    public static <T> ResponseStatusException notFoundException() {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.RESOURCE_NOT_FOUND);
    }

}
