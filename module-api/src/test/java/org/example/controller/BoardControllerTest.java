package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.BoardRequestDto;
import org.example.dto.BoardResponseDto;
import org.example.service.BoardService;
import org.example.vo.CreateBoardVo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BoardController.class)
@MockBean(JpaMetamodelMappingContext.class) // Error creating bean with name 'jpaAuditingHandler' 에러
class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BoardService boardService;

    @Test
    void 게시글_생성_테스트() throws Exception {
        // Given
        BoardRequestDto requestDto = new BoardRequestDto("Test");
        BoardResponseDto responseDto = new BoardResponseDto(1L, "Test");

        when(boardService.createBoard(Mockito.any(CreateBoardVo.class))).thenReturn(responseDto);

        // When & Then
        mockMvc.perform(post("/api/boards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isCreated())  // HTTP 201 응답으로 수정
                .andExpect(jsonPath("$.status").value("CREATED"))
                .andExpect(jsonPath("$.message").value("게시글 생성 성공"))
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.title").value("Test"));

        verify(boardService, times(1)).createBoard(any(CreateBoardVo.class));
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
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.message").value("전체 게시글 조회 성공"))
                .andExpect(jsonPath("$.data.size()").value(2))
                .andExpect(jsonPath("$.data[0].id").value(1L))
                .andExpect(jsonPath("$.data[0].title").value("Test1"))
                .andExpect(jsonPath("$.data[1].id").value(2L))
                .andExpect(jsonPath("$.data[1].title").value("Test2"));


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
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.message").value("게시글 조회 성공"))
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.title").value("Test"));


        verify(boardService, times(1)).getBoardById(1L);
    }

    @Test
    void 게시글_수정_테스트() throws Exception {
        // Given
        BoardRequestDto requestDto = new BoardRequestDto("Update");
        BoardResponseDto responseDto = new BoardResponseDto(1L, "Update");

        when(boardService.updateBoard(eq(1L), any(CreateBoardVo.class))).thenReturn(responseDto);

        // When & Then
        mockMvc.perform(put("/api/boards/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk()) // HTTP 200 응답
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.message").value("게시글 수정 성공"))
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.title").value("Update"));

        verify(boardService, times(1)).updateBoard(eq(1L), any(CreateBoardVo.class));
    }

    @Test
    void 게시글_삭제_테스트() throws Exception {
        // Given
        BoardResponseDto responseDto = new BoardResponseDto(1L, "Delete");

        when(boardService.deleteBoard(1L)).thenReturn(responseDto);

        // When & Then
        mockMvc.perform(delete("/api/boards/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // HTTP 200 응답
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.message").value("게시글 삭제 성공"))
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.title").value("Delete"));

        verify(boardService, times(1)).deleteBoard(1L);
    }


}