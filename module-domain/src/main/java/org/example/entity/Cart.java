package org.example.entity;

import javax.persistence.*;

@Entity
public class Cart  extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CART_ID")
    private Long id;

    @Column(nullable = false, unique = true)
    private Long productNum;

    @Column(nullable = false, unique = true)
    private Character isRegular;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", nullable = false) // 외래 키 설정
    private Product product;

}
