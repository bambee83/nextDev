package org.example.controller;

import org.example.exception.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/ignore")
public class IgnoreController {

    @GetMapping
    public ResponseEntity<ResponseMessage> ignore() {
        return ResponseMessage.success(HttpStatus.OK, "조회 성공", null);
    }
}
