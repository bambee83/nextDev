package org.example.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private Long stock;

    @Column(nullable = false)
    private Long score;

    @Column(nullable = false)
    private Long deliveryFee;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "PRODUCT_ID") // 외래 키 설정
    private List<Category> categoryList = new ArrayList<>();
}
