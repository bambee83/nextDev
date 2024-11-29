package org.example.nexthw.entity;

import org.example.nexthw.dto.BoardRequestDto;

import javax.persistence.*;

@Entity @Table(name = "board")
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Board() {}

    public Board(BoardRequestDto boardRequestDto) {
        this.title = boardRequestDto.getTitle();
    }

    public void update(BoardRequestDto boardRequestDto) {
        this.title = boardRequestDto.getTitle();
    }


}
