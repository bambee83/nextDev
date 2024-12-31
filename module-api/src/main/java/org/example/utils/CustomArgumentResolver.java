package org.example.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class CustomArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 지원하는 파라미터 타입인지 확인
        return parameter.getParameterType().equals(MyCustomType.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // 커스텀 로직으로 파라미터 값을 처리
        String paramValue = webRequest.getParameter("customParam");
        return new MyCustomType(paramValue);
    }
}

@RequiredArgsConstructor
@Getter
class MyCustomType {
    private final String value;

}