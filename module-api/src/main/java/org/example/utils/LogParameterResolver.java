package org.example.utils;

import lombok.extern.slf4j.Slf4j;
import org.example.exception.CustomErrorCode;
import org.example.exception.CustomException;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j @Component
public class LogParameterResolver implements HandlerMethodArgumentResolver {
    /**
     * 호출되는 Controller의 파라미터 값을 검사하는 콜백 함수
     *
     * @param parameter 클라이언트로 부터 받은 파라미터
     * @return 적용 여부
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 파라미터에 LogParameter 어노테이션이 있으면 true 반환
        return parameter.hasParameterAnnotation(LogParameter.class);
    }
    /**
     * supportsParameter 콜백 함수에서 true를 반환했을 경우
     * 호출되는 콜백 함수
     *
     * @param parameter
     * @param mavContainer
     * @param webRequest
     * @param binderFactory
     * @return
     * @throws Exception
     */

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // 파라미터 이름 가져오기
        String paramName = Optional.ofNullable(parameter.getParameterName())
                .orElseThrow(() -> new CustomException(CustomErrorCode.INVALID_PARAMETER));


        // 요청에서 파라미터 값 가져오기
        String paramValue = Optional.ofNullable(webRequest.getParameter(paramName))
                .filter(value -> !value.isEmpty())  // 빈 문자열 필터링
                .orElseThrow(() -> new CustomException(CustomErrorCode.INVALID_PARAMETER));

        log.info("Parameter - [{}] [{}]", paramName, paramValue);

        return paramValue;
    }
}
