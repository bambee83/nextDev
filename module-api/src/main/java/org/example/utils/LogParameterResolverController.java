package org.example.utils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogParameterResolverController {

    // http://localhost:8080/test2?name=John
    @GetMapping("/test2")
    public String testLogParameter(@LogParameter String name) {
        return "Hello, " + name;
    }
}
