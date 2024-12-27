package org.example.entity;

import lombok.Getter;

import javax.persistence.*;

@Deprecated
@Entity @Getter
@Table(name = "members")
public class Member extends Timestamped {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 60)
    private String password;
}
