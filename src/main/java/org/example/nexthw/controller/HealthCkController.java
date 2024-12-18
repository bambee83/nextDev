package org.example.nexthw.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.nexthw.utils.LogTrace;
import org.example.nexthw.utils.TraceStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController@RequiredArgsConstructor
public class HealthCkController {

    private final LogTrace logTrace;

    // healthCk
    @GetMapping("/healthCk")
    public String healthCk() throws InterruptedException {
        return "OK";
    }

    // log test
    @GetMapping("/test")
    public void test() {
        TraceStatus status = null;
        try {
            status = logTrace.begin("test");
            log.trace("trace log={}", log.getName());
            log.debug("debug log={}", log.getName());
            log.info("info log={}", log.getName());
            log.warn("warn log={}", log.getName());
            log.error("error log={}", log.getName());
            logTrace.end(status);
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e; //예외를 꼭 다시 던져주어야 한다.
        }
    }

}
