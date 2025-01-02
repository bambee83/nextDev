package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.BoardResponseDto;
import org.example.entity.Board;
import org.example.exception.CustomErrorCode;
import org.example.exception.CustomException;
import org.example.repository.BoardRepository;
import org.example.vo.CreateBoardVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service @RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final AutoCompleteService autoCompleteService;

    // 게시글 생성
    @Transactional
    public BoardResponseDto createBoard(CreateBoardVo createBoardVo) {
        Board board = new Board(createBoardVo);
        autoCompleteService.addTrie(createBoardVo.getTitle());
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
        autoCompleteService.deleteTrie(board.getTitle());
        autoCompleteService.addTrie(createBoardVo.getTitle());
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
                () -> new CustomException(CustomErrorCode. BOARD_NOT_FOUND)
        );
    }


}
