package com.andrei.product.exception;

public class Http404Exception extends RuntimeException {

    public Http404Exception() {
        super();
    }

    public Http404Exception(String message) {
        super(message);
    }

    public Http404Exception(Throwable cause) {
        super(cause);
    }

    public Http404Exception(String message, Throwable cause) {
        super(message, cause);
    }

}