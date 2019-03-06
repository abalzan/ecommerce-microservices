package com.andrei.product.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class Http404Exception extends RuntimeException {

    private Date timestamp;

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

    public Http404Exception(String message, Date timestamp, Throwable cause) {
        super(message, cause);
        this.timestamp = timestamp;
    }

}
