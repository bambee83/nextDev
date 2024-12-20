package org.example.nexthw.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CustomErrorCode {
    IllegalArgumentException(HttpStatus.NOT_FOUND, "IllegalArgumentException"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "BAD_REQUEST"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED,"METHOD_NOT_ALLOWED");

    private final HttpStatus httpStatus;
    private final String message;

    CustomErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
