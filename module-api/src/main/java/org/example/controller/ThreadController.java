package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.exception.ResponseMessage;
import org.example.service.ThreadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @Slf4j
public class ThreadController {

    private final ThreadService threadService;

    public ThreadController(ThreadService threadService) {
        this.threadService = threadService;
    }

    @GetMapping("/execute-threads")
    public ResponseEntity<ResponseMessage> executeThreads() {
        log.info("Controller: executeThreads called.");
        return ResponseMessage.success(HttpStatus.OK,"쓰레드 테스트 성공", threadService.executeThreads());
    }
}
