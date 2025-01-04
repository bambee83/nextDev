package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.exception.ResponseMessage;
import org.example.service.FeignClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/api/feignClient")
@RequiredArgsConstructor
public class FeignClientController {
    private final FeignClientService feignClientService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage> getPostById(@PathVariable Long id) {
        return ResponseMessage.success(HttpStatus.OK, "FeignClient 조회 성공", feignClientService.getPostById(id));
    }

}
