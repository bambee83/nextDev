package org.example.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DELIVERY_ID")
    private Long id;

    @Column(nullable = false)
    private Character status;

    @Column(nullable = false)
    private LocalDateTime deliveryDate;

    @OneToOne
    @JoinColumn(name = "ORDERS_ID", nullable = false) // 외래 키 설정
    Orders orders;

}
