package org.example.nexthw.conf;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

//@Component
@Slf4j
public class LogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        // 외부 > filter ( > 처리 > ) filter > 외부
        System.out.println("==============================");
        System.out.println("startFilter");
        log.info(Thread.currentThread().toString());
        filterChain.doFilter(servletRequest, servletResponse);
        log.info(Thread.currentThread().toString());
        System.out.println("endFilter");
        System.out.println("==============================");



    }
}
