package org.example.nexthw.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final int errorCode;

    public CustomException(CustomErrorCode customErrorCode) {
        super(customErrorCode.getMessage());
        this.errorCode = customErrorCode.getErrorCode();
    }
}