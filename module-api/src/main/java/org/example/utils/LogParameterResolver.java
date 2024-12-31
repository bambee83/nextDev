package org.example.utils;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class LogParameterResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 파라미터에 LogParameter 어노테이션이 있으면 true 반환
        return parameter.hasParameterAnnotation(LogParameter.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // 파라미터의 이름을 로그로 출력
        String paramName = parameter.getParameterName();
        String paramValue = webRequest.getParameter(paramName); // 쿼리 파라미터 값을 가져옴
        System.out.println("Parameter - " + paramName + ": " + paramValue);  // 로그 출력

        return paramValue;  // 해당 값을 그대로 반환
    }
}
