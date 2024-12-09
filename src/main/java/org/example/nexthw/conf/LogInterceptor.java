package org.example.nexthw.conf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("==============================");
        log.info("Interceptor preHandle");
        log.info("Interceptor REQUEST [{}] [{}] ", request.getRequestURI(), request.getMethod());

        String uuid = UUID.randomUUID().toString();
        request.setAttribute("uuid", uuid);

        // @RequestMapping : HandlerMethod
        // 정적 리소스 : RequestHttpRequestHandler
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler; // 호출할 컨트롤러 메서드의 모든 정보 포함
        }

        log.info("Interceptor handler : " + handler);
        log.info("==============================");
        return true; // 컨트롤러 호출 (handler 호출) 또는 다음 인터셉터 호출
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
         log.info("==============================");
         log.info("Interceptor postHandle [{}]", modelAndView);
         log.info("==============================");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
         log.info("==============================");
         log.info("Interceptor afterCompletion");
        log.info("Interceptor RESPONSE [{}] [{}] [{}]", request.getRequestURI(), response.getStatus(), response.getContentType());
        request.getAttribute("uuid");
         log.info("==============================");

        if (ex != null) {
            log.error("Interceptor afterCompletion exception" + ex.getMessage(), ex);
        }
    }
}
