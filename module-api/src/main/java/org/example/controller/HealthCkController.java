package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController@RequiredArgsConstructor
public class HealthCkController {

    // healthCk
    @GetMapping("/health")
    public String healthCk() {
        return "OK";
    }

    // log test
    @GetMapping("/logTest")
    public void test() {
        try {
            log.trace("trace log={}", log.getName());
            log.debug("debug log={}", log.getName());
            log.info("info log={}", log.getName());
            log.warn("warn log={}", log.getName());
            log.error("error log={}", log.getName());
        } catch (Exception e) {
            throw e; //예외를 꼭 다시 던져주어야 한다.
        }
    }

}
