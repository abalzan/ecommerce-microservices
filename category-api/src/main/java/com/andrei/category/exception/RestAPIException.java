package com.andrei.category.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RestAPIException {

    private final String message;

    private final String details;

}
