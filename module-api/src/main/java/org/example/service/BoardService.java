package org.example.service;

import org.example.dto.BoardResponseDto;
import org.example.entity.Board;
import org.example.exception.CustomErrorCode;
import org.example.exception.CustomException;
import org.example.repository.BoardRepository;
import org.example.vo.CreateBoardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 게시글 생성
    @Transactional
    public BoardResponseDto createBoard(CreateBoardVo createBoardVo) {
        Board board = new Board(createBoardVo);
        return new BoardResponseDto(boardRepository.save(board));
    }

    // 전체 게시글 조회
    @Transactional(readOnly = true)
    public List<BoardResponseDto> getAllBoards() {
        List<BoardResponseDto> boardResponseDtoList = new ArrayList<>();
        List<Board> boardList = boardRepository.findAll();
        for (Board board : boardList) {
            boardResponseDtoList.add(new BoardResponseDto(board));
        }
        return boardResponseDtoList;
    }

    // 단건 게시글 조회
    @Transactional(readOnly = true)
    public BoardResponseDto getBoardById(Long id) {
        Board board = checkBoard(id);
        return new BoardResponseDto(board);
    }

    // 게시글 수정
    @Transactional
    public BoardResponseDto updateBoard(Long id, CreateBoardVo createBoardVo) {
        Board board = checkBoard(id);
        board.update(createBoardVo);
        boardRepository.save(board);
        return new BoardResponseDto(board);
    }

    // 게시글 삭제
    @Transactional
    public BoardResponseDto deleteBoard(Long id) {
        Board board = checkBoard(id);
        boardRepository.deleteById(id);
        return new BoardResponseDto(board);
    }

    // 커스텀 예외처리
    private Board checkBoard(Long id) {
        return boardRepository.findById(id).orElseThrow(
                () -> new CustomException(CustomErrorCode.IllegalArgumentException)
        );
    }


}
