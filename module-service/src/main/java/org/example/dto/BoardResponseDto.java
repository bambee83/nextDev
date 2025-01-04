package org.example.dto;

import lombok.Getter;
import org.example.entity.Board;

@Getter
public class BoardResponseDto {
    private Long id;
    private String title;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
    }

    // tesc Code 용 (Controller 단)
    public BoardResponseDto(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
