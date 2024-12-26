package org.example.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    //== 200 ==//
    SUCCESS(HttpStatus.OK, "OK"),

    //== 400 ==//
    NOT_SUPPORTED_HTTP_METHOD(HttpStatus.BAD_REQUEST,"지원하지 않는 Http Method 방식입니다."),
    NOT_VALID_METHOD_ARGUMENT(HttpStatus.BAD_REQUEST,"유효하지 않은 Request Body 혹은 Argument입니다."),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당 사용자를 찾을 수 없습니다."),


    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"),
    IllegalArgumentException(HttpStatus.NOT_FOUND, "IllegalArgumentException"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "BAD_REQUEST"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED,"METHOD_NOT_ALLOWED"),
    AUTHENTICATION_FAILED(HttpStatus.FORBIDDEN,"AUTHENTICATION_FAILED");

    private final HttpStatus httpStatus;
    private final String message;

}
