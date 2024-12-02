package org.example.nexthw.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.nexthw.dto.BoardRequestDto;
import org.example.nexthw.dto.BoardResponseDto;
import org.example.nexthw.service.BoardService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BoardController.class)
class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BoardService boardService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void 게시글_생성_테스트() throws Exception {
        // Given
        BoardRequestDto requestDto = new BoardRequestDto("Test");
        BoardResponseDto responseDto = new BoardResponseDto(1L, "Test");

        when(boardService.createBoard(Mockito.any(BoardRequestDto.class))).thenReturn(responseDto);

        // When & Then
        mockMvc.perform(post("/api/boards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Test"));

        verify(boardService, times(1)).createBoard(any(BoardRequestDto.class));
    }

    @Test
    void 게시글_전체_조회_테스트() throws Exception {
        // Given
        List<BoardResponseDto> boardList = Arrays.asList(
                new BoardResponseDto(1L, "Test1"),
                new BoardResponseDto(2L, "Test2")
        );

        when(boardService.getAllBoards()).thenReturn(boardList);

        // When & Then
        mockMvc.perform(get("/api/boards")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].title").value("Test1"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].title").value("Test2"));

        verify(boardService, times(1)).getAllBoards();
    }

    @Test
    void 단건_게시글_조회_테스트() throws Exception {
        // Given
        BoardResponseDto responseDto = new BoardResponseDto(1L, "Test");

        when(boardService.getBoardById(1L)).thenReturn(responseDto);

        // When & Then
        mockMvc.perform(get("/api/boards/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Test"));

        verify(boardService, times(1)).getBoardById(1L);
    }

    @Test
    void 게시글_수정_테스트() throws Exception {
        // Given
        BoardRequestDto requestDto = new BoardRequestDto("Update");
        BoardResponseDto responseDto = new BoardResponseDto(1L, "Update");

        when(boardService.updateBoard(eq(1L), any(BoardRequestDto.class))).thenReturn(responseDto);

        // When & Then
        mockMvc.perform(put("/api/boards/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Update"));

        verify(boardService, times(1)).updateBoard(eq(1L), any(BoardRequestDto.class));
    }

    @Test
    void 게시글_삭제_테스트() throws Exception {
        // Given
        BoardResponseDto responseDto = new BoardResponseDto(1L, "Delete");

        when(boardService.deleteBoard(1L)).thenReturn(responseDto);

        // When & Then
        mockMvc.perform(delete("/api/boards/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Delete"));

        verify(boardService, times(1)).deleteBoard(1L);
    }


}