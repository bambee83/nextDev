package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.PaymentRequestDto;
import org.example.dto.PaymentResponseDto;
import org.example.exception.ResponseMessage;
import org.example.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/payment")
@RequiredArgsConstructor
public class PaymentControlller {
    private final PaymentService paymentService;

    // 결제 승인 API
    @PostMapping("/")//    @GetMapping("/{productId}")
    public ResponseEntity<ResponseMessage> confirmPayment(@RequestBody PaymentRequestDto requestDto) {
        PaymentResponseDto responseDto = paymentService.confirmPayment(requestDto);
        return ResponseMessage.success(HttpStatus.OK, "결제 성공", responseDto);
    }
}
