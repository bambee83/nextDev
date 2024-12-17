package org.example.nexthw.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j @RestController
public class HealthCkController {

    // healthCk
    @GetMapping("/healthCk")
    public String healthCk() throws InterruptedException {
        log.info("hello - start");
        Thread.sleep(1000);
        log.info("hello - end");
        return "OK";
    }

    // log test
    @GetMapping("/test")
    public void test() {
        log.trace("trace log={}", log.getName());
        log.debug("debug log={}", log.getName());
        log.info("info log={}", log.getName());
        log.warn("warn log={}",log.getName());
        log.error("error log={}", log.getName());
    }




}
