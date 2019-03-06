package com.andrei.product.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class RestAPIException {
    private Date timestamp;
    private final String message;
    private final String details;
}
