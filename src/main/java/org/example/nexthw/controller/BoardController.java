package org.example.nexthw.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.nexthw.dto.BoardRequestDto;
import org.example.nexthw.exception.ResponseMessage;
import org.example.nexthw.service.BoardService;
import org.example.nexthw.vo.CreateBoardVo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/boards")
@Tag(name = "Board", description = "게시글")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @Operation(summary= "게시글 생성", description = "게시글 생성")
    @PostMapping
    public ResponseEntity<ResponseMessage> createBoard(@RequestBody BoardRequestDto requestDto) {
        CreateBoardVo createBoardVo = new CreateBoardVo(requestDto.getTitle());
        return ResponseEntity.ok(
                ResponseMessage.success(HttpStatus.CREATED, "게시글 생성 성공", boardService.createBoard(createBoardVo))
        );
    }

    @Operation(summary= "전체 게시글 조회", description = "전체 게시글 조회")
    @GetMapping
    public ResponseEntity<ResponseMessage> getAllBoards() {
        return ResponseEntity.ok(
                ResponseMessage.success(HttpStatus.OK,"전체 게시글 조회 성공", boardService.getAllBoards())
        );
    }

    @Operation(summary= "단건 게시글 조회", description = "단건 게시글 조회")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage> getBoardById(@PathVariable Long id) {
        return ResponseEntity.ok(
                ResponseMessage.success(HttpStatus.OK,"게시글 조회 성공", boardService.getBoardById(id))
        );
    }

    @Operation(summary= "게시글 수정", description = "게시글 수정")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage> updateBoard(@PathVariable Long id,
                                                        @RequestBody BoardRequestDto requestDto) {
        CreateBoardVo createBoardVo = new CreateBoardVo(requestDto.getTitle());
        return ResponseEntity.ok(
                ResponseMessage.success(HttpStatus.OK,"게시글 수정 성공", boardService.updateBoard(id, createBoardVo))
        );
    }

    @Operation(summary= "게시글 삭제", description = "게시글 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> deleteBoard(@PathVariable Long id) {
        return ResponseEntity.ok(
                ResponseMessage.success(HttpStatus.OK,"게시글 삭제 성공", boardService.deleteBoard(id))
        );
    }

}
