package org.example.nexthw.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.example.nexthw.dto.BoardRequestDto;
import org.example.nexthw.dto.BoardResponseDto;
import org.example.nexthw.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/boards")
@Tag(name = "Board", description = "게시글")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @ApiOperation(value = "게시글 생성", notes = "게시글 생성")
    @PostMapping
    public ResponseEntity<BoardResponseDto> createBoard(@RequestBody BoardRequestDto requestDto) {
//        BoardResponseDto responseDto = boardService.createBoard(requestDto);
        return ResponseEntity.ok(boardService.createBoard(requestDto));
    }

    @ApiOperation(value = "전체 게시글 조회", notes = "전체 게시글 조회")
    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> getAllBoards() {
//        List<BoardResponseDto> boards = boardService.getAllBoards();
        return ResponseEntity.ok(boardService.getAllBoards());
    }

    @ApiOperation(value = "단건 게시글 조회", notes = "단건 게시글 조회")
    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> getBoardById(@PathVariable Long id) {
//        BoardResponseDto responseDto = boardService.getBoardById(id);
        return ResponseEntity.ok(boardService.getBoardById(id));
    }

    @ApiOperation(value = "게시글 수정", notes = "게시글 수정")
    @PutMapping("/{id}")
    public ResponseEntity<BoardResponseDto> updateBoard(@PathVariable Long id,
                                                        @RequestBody BoardRequestDto requestDto) {
//        BoardResponseDto responseDto = boardService.updateBoard(id, requestDto);
        return ResponseEntity.ok(boardService.updateBoard(id, requestDto));
    }

    @ApiOperation(value = "게시글 삭제", notes = "게시글 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<BoardResponseDto> deleteBoard(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.deleteBoard(id));
    }

}
