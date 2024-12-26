package org.example.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private CustomErrorCode customErrorCode;

    public CustomException(CustomErrorCode customErrorCode) {
        this.customErrorCode = customErrorCode;
    }
}