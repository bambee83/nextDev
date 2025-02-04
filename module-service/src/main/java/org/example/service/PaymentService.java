package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.PaymentRequestDto;
import org.example.dto.PaymentResponseDto;
import org.example.entity.Orders;
import org.example.entity.Payment;
import org.example.repository.OrderRepository;
import org.example.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PaymentService {
    private final PaymentClient paymentClient;
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    @Value("${toss.secretKey}")
    private String secretKey;

    // 결제 승인
    public PaymentResponseDto confirmPayment(PaymentRequestDto paymentRequestDto) {
        // Authorization 헤더 생성
        String authorizationHeader = generateAuthorizationHeader(secretKey);
        // Feign Client 호출
        ResponseEntity<PaymentResponseDto> responseDto = paymentClient.confirmPayment(
                authorizationHeader,
                new PaymentRequestDto(
                        paymentRequestDto.getPaymentKey(),
                        paymentRequestDto.getOrderId(),
                        paymentRequestDto.getAmount()
                )
        );

        // 응답 처리
        if (responseDto.getStatusCode().is2xxSuccessful() && responseDto.getBody() != null) {

            PaymentResponseDto response = responseDto.getBody();

            // 결제 정보 엔티티 저장
            savePayment(response);


            return new PaymentResponseDto(
                    response.getPaymentKey(),
                    response.getOrderId(),
                    response.getTotalAmount(),
                    response.getStatus(),
                    response.getMethod()
            );
        } else {
            throw new RuntimeException("Failed to confirm payment: " + responseDto.getStatusCode());
        }
    }

    private String generateAuthorizationHeader(String secretKey) {
        String encodedKey = Base64.getEncoder().encodeToString((secretKey + ":").getBytes(StandardCharsets.UTF_8));
        System.out.println("Encoded Secret Key: " + encodedKey);
        return "Basic " + encodedKey;
    }

    // 결제 정보를 Payment 엔티티로 저장
    private void savePayment(PaymentResponseDto paymentResponseDto) {
        // orderId가 1인 Orders 객체를 조회
        Orders order = orderRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Payment 객체 생성
        Payment payment = new Payment(
                paymentResponseDto.getStatus(),  // 결제 상태
                paymentResponseDto.getMethod(),  // 결제 방법
                paymentResponseDto.getTotalAmount(),  // 총 금액
                LocalDateTime.now(),  // 현재 시간을 배달일로 설정
                order
        );

        // Payment 엔티티 저장
        paymentRepository.save(payment);
        log.info("Payment saved: {}", payment);
    }
}
