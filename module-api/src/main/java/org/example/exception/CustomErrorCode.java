package org.example.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CustomErrorCode {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"),
    IllegalArgumentException(HttpStatus.NOT_FOUND, "IllegalArgumentException"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "BAD_REQUEST"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED,"METHOD_NOT_ALLOWED"),
    AUTHENTICATION_FAILED(HttpStatus.FORBIDDEN,"AUTHENTICATION_FAILED");

    private final HttpStatus httpStatus;
    private final String message;

}
