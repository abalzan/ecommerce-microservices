package com.andrei.product.exception;

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
