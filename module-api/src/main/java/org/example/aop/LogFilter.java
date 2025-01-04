package org.example.aop;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

//@Component
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("==============================");
        log.info("LogFilter init");
        log.info("==============================");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        final UUID uuid = UUID.randomUUID();
        MDC.put("request_id", uuid.toString());
        log.info("==============================");
        log.info("LogFilter doFilter");

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();
        String httpMethod = httpRequest.getMethod();
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        try {
            log.info("LogFilter REQUEST [{}] [{}]", httpMethod, requestURI);
            log.info("request_id [{}]}", MDC.get("request_id"));
            filterChain.doFilter(servletRequest, servletResponse); // 다음 필터 또는 서블릿 호출
        } catch (Exception e) {
            throw e;
        } finally {
            log.info("LogFilter RESPONSE [{}]", requestURI);
            MDC.clear();
        }
        log.info("==============================");

     /*   외부 > filter( > 처리 > )filter > 외부
        log.info(Thread.currentThread().toString());*/
    }

    @Override
    public void destroy() {
        log.info("==============================");
        log.info("LogFilter destroy");
        log.info("==============================");
    }
}

