package org.example.nexthw.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
//    protected CustomErrorCode errorCode;
    private final int statusCode;

    public CustomException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

//    public CustomException(CustomErrorCode errorCode) {
//        super(errorCode.getMessage());
//        this.statusCode = errorCode.getStatusCode();
//    }
}