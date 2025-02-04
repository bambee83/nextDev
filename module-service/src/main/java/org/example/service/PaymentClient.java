package org.example.service;

import org.example.dto.PaymentRequestDto;
import org.example.dto.PaymentResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "tossPayments",
        url = "${toss.payments.url}"
)
public interface PaymentClient {

    @PostMapping("/v1/payments/confirm")
    ResponseEntity<PaymentResponseDto> confirmPayment(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody PaymentRequestDto requestDto
    );
}
