package org.example.nexthw.dto;

import org.example.nexthw.entity.Board;

public class BoardResponseDto {
    private Long id;
    private String title;


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
    }

//    // tesc Code 용 (Controller 단)
    public BoardResponseDto(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
