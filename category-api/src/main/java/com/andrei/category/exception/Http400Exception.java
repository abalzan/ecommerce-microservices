package com.andrei.category.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class Http400Exception extends RuntimeException {

    public Http400Exception() {
        super();
    }

    public Http400Exception(String message) {
        super(message);
    }

    public Http400Exception(Throwable cause) {
        super(cause);
    }

    public Http400Exception(String message, Throwable cause) {
        super(message, cause);
    }

}
