package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IgnoreController {

    @GetMapping("/ignore")
    public String ignoreEndpoint() {
        return "[인터셉터] 특정 컨트롤러 제외 성공";
    }
}
