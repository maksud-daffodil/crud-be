package com.example.crud.exceptions;

import lombok.Getter;

@Getter
public class BusinessException extends Exception {
    public final String errorCode;

    public BusinessException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
