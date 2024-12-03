package org.example.nexthw.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HealthCkController {
    @Value("${spring.profiles.active}")
    String profilesActive;

    // MultiProfile test
    @GetMapping("/healthCk")
    public String healthCk() {
        log.info("Active Profile: {}", profilesActive);
        return String.format("Active Profile: %s", profilesActive);
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
