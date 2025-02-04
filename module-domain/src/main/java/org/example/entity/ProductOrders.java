package org.example.entity;

import javax.persistence.*;

@Entity
public class ProductOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ORDERS_ID")
    private Long id;

    @Column(nullable = false)
    private Long quantity; // 주문 수량

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", nullable = false) // Product와 연관
    private Product product;

    @ManyToOne
    @JoinColumn(name = "ORDERS_ID", nullable = false) // Orders와 연관
    private Orders orders;
}
