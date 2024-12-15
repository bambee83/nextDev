package org.example.nexthw.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j @RestController
public class HealthCkController {

    // healthCk
    @GetMapping("/healthCk")
    public String healthCk() {
        return "OK";
    }

    // log test
    @GetMapping("/test")
    public void test() {
        log.trace("TRACE!!");
        log.debug("DEBUG!!");
        log.info("INFO!!");
        log.warn("WARN!!");
        log.error("ERROR!!");
    }




}
