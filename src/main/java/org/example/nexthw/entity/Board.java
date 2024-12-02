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

    //    public Board(String title) {
//        this.title = title;
//    }

    public Board(BoardRequestDto boardRequestDto) {
        this.title = boardRequestDto.getTitle();
    }

    public void update(BoardRequestDto boardRequestDto) {
        this.title = boardRequestDto.getTitle();
    }

    // testCode 용 추가 생성자 (Service 단)
    public Board(Long id, String Title) {
        this.id = id;
        this.title = Title;
    }


}
