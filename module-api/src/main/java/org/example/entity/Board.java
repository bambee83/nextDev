package org.example.entity;

import lombok.Getter;
import org.example.vo.CreateBoardVo;

import javax.persistence.*;

@Entity @Getter
//@Table(name = "board")
public class Board extends Timestamped {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_ID")
    private Long id;

    @Column(nullable = false)
    private String title;

    public Board() {}

    public Board(CreateBoardVo createBoardVo) {
        this.title = createBoardVo.getTitle();
    }

    public void update(CreateBoardVo createBoardVo) {
        this.title = createBoardVo.getTitle();
    }

    // testCode 용 추가 생성자 (Service 단)
    public Board(Long id, String Title) {
        this.id = id;
        this.title = Title;
    }


}