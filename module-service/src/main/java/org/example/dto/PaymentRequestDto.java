package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDto {
    private String paymentKey; // 결제 키 (결제 승인에 필요)
    private String orderId;  // 주문 ID
    private Long amount; // 결제 금액

}
