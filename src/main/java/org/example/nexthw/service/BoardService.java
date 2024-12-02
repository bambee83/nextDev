package org.example.nexthw.service;

import org.example.nexthw.dto.BoardRequestDto;
import org.example.nexthw.dto.BoardResponseDto;
import org.example.nexthw.entity.Board;
import org.example.nexthw.repository.BoardRepository;
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
    public BoardResponseDto createBoard(BoardRequestDto boardRequestDto) {
        Board board = new Board(boardRequestDto);
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
    public BoardResponseDto updateBoard(Long id, BoardRequestDto boardRequestDto) {
        Board board = checkBoard(id);
        board.update(boardRequestDto);
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

    // 공통 메서드 추출 (근데 전역처리에서 걸러짐 ....)
    private Board checkBoard(Long id) {
        return boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("해당되는 아이디 (%d)의 게시글이 없습니다.", id)));
    }


}
