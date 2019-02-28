package com.andrei.product.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RestAPIException {

    private final String message;

    private final String details;

    //
//    public RestAPIException(String message) {
//        super(message);
//    }
//
//    public RestAPIException(Throwable cause) {
//        super(cause);
//    }
//
//    public RestAPIException(String message, Throwable cause) {
//        super(message, cause);
//    }

}
