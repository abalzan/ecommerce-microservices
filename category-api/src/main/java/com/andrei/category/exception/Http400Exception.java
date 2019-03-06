package com.andrei.category.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class Http400Exception extends RuntimeException {

    private Date timestamp;

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

    public Http400Exception(Date timestamp, String message, Throwable cause) {
        super(message, cause);
        this.timestamp = timestamp;
    }
}
