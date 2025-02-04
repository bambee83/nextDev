package org.example.entity;

import javax.persistence.*;

@Entity
public class Address {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    private Long id;

    @Column(nullable = false)
    private String name; // 주소

    @Column(nullable = false)
    private Character isMain; // 기본 주소 여부, yes(1), no(0)

}
