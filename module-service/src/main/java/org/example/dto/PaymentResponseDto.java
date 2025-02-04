package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentResponseDto {
    private String paymentKey;// 결제 키
    private String orderId;  // 주문 ID
    private Long totalAmount;// 결제 금액
    private String status;  // 결제 상태
    private String method;    // 결제 방식 (카드, 계좌이체 등)
}
