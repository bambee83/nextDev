package org.example.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CustomErrorCode {

    //== 400 ==//
    NOT_SUPPORTED_HTTP_METHOD(HttpStatus.BAD_REQUEST, "지원하지 않는 Http Method 방식입니다."),
    NOT_VALID_METHOD_ARGUMENT(HttpStatus.BAD_REQUEST, "유효하지 않은 Request Body 혹은 Argument입니다."),
    BOARD_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당 게시글을 찾을 수 없습니다."),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "파라미터가 유효하지 않습니다."),

    BAD_REQUEST(HttpStatus.BAD_REQUEST, "ᕕ( ᐛ )ᕗ 잘못된 요청입니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "ᕕ( ᐛ )ᕗ 리소스를 찾을 수 없습니다."),

    //== FEIGN ==//
    FEIGN_BAD_REQUEST(HttpStatus.BAD_REQUEST, "ᕕ( ᐛ )ᕗ 잘못된 요청입니다."),
    FEIGN_NOT_FOUND(HttpStatus.NOT_FOUND, "ᕕ( ᐛ )ᕗ 리소스를 찾을 수 없습니다."),
    FEIGN_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "ᕕ( ᐛ )ᕗ 서버 오류가 발생했습니다."),


    //== 500 ==//
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final String message;

}
