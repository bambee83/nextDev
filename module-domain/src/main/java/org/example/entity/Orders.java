package org.example.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "ORDERS")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDERS_ID")
    private Long id;

    @Column(nullable = false)
    private LocalDateTime orderDate; // 주문 날짜

    @Column(nullable = false)
    private String status; // 주문 상태

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", nullable = false) // 회원과 연관관계 설정
    private Member member;
}
