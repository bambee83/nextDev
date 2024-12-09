package org.example.nexthw.conf;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

//@Component
@Slf4j
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
         log.info("==============================");
        log.info("LogFilter doFilter");

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;


        try{
            log.info("LogFilter REQUEST [{}]", requestURI);
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            throw e;
        } finally {
            log.info("LogFilter RESPONSE [{}]", requestURI);
        }
         log.info("==============================");


        // 외부 > filter ( > 처리 > ) filter > 외부
//        System.out.println("==============================");
//        System.out.println("startFilter");
//        log.info(Thread.currentThread().toString());
//        filterChain.doFilter(servletRequest, servletResponse);
//        log.info(Thread.currentThread().toString());
//        System.out.println("endFilter");
//         log.info("==============================");



    }

    @Override
    public void destroy() {
         log.info("==============================");
        log.info("LogFilter destroy");
         log.info("==============================");
    }
}
