package org.example.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor   // JPA 기본 생성자 (리플렉션을 사용하여 객체를 생성)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAMENT_ID")
    private Long id;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String payMethod;

    @Column(nullable = false)
    private Long totalPrice;

    @Column(nullable = false)
    private LocalDateTime deliveryDate;

    @OneToOne
    @JoinColumn(name = "ORDERS_ID", nullable = false) // 외래 키 설정
    Orders orders;

    public Payment(String status, String method, Long totalAmount, LocalDateTime deliveryDate, Orders orders) {
        this.status = status;
        this.payMethod = method;
        this.totalPrice = totalAmount;
        this.deliveryDate = deliveryDate;
        this.orders = orders;
    }
}
