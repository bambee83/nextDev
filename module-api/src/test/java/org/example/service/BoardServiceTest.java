package org.example.service;

import org.example.dto.BoardRequestDto;
import org.example.dto.BoardResponseDto;
import org.example.entity.Board;
import org.example.exception.CustomErrorCode;
import org.example.exception.CustomException;
import org.example.repository.BoardRepository;
import org.example.vo.CreateBoardVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BoardServiceTest {
    @InjectMocks
    private BoardService boardService; // 테스트 대상
    @Mock
    private BoardRepository boardRepository; // Mock 객체 생성

    private Board board1; //  더미 객체(Mock Data)

    @BeforeEach // 테스트 환경 초기화
    void setUp() {
        board1 = new Board(1L, "Test1");
    }

    @Test
    void 게시글_생성_성공() {
        // Given
        BoardRequestDto requestDto = new BoardRequestDto("Test1");
        CreateBoardVo createBoardVo = new CreateBoardVo(requestDto.getTitle());
        when(boardRepository.save(any(Board.class))).thenReturn(board1);

        // When
        BoardResponseDto responseDto = boardService.createBoard(createBoardVo);

        // Then
        assertEquals("Test1", responseDto.getTitle());
        verify(boardRepository, times(1)).save(any(Board.class));
    }

    @Test
    void 게시글_전체_조회_성공() {
        // Given
        Board board2 = new Board(2L, "Test2");
        when(boardRepository.findAll()).thenReturn(Arrays.asList(board1, board2));

        // When
        List<BoardResponseDto> boardResponseDtoList = boardService.getAllBoards();

        // Then
        assertEquals(2, boardResponseDtoList.size());
        assertEquals("Test1", boardResponseDtoList.get(0).getTitle());
        assertEquals("Test2", boardResponseDtoList.get(1).getTitle());
        verify(boardRepository, times(1)).findAll();
    }

    @Test
    void 게시글_단건_조회_성공() {
        // Given
        when(boardRepository.findById(1L)).thenReturn(Optional.of(board1));

        // When
        BoardResponseDto responseDto = boardService.getBoardById(1L);

        // Then
        assertEquals("Test1", responseDto.getTitle());
        verify(boardRepository, times(1)).findById(1L);
    }

    @Test
    @Transactional
    void 게시글_수정_테스트_성공1() {
        // Given
        BoardRequestDto requestDto = new BoardRequestDto("Update");
        CreateBoardVo createBoardVo = new CreateBoardVo(requestDto.getTitle());

        when(boardRepository.findById(1L)).thenReturn(Optional.of(board1));
        when(boardRepository.save(any(Board.class))).thenReturn(board1);


        // When
        BoardResponseDto responseDto = boardService.updateBoard(1L, createBoardVo);

        // Then
        assertEquals("Update", responseDto.getTitle());
        verify(boardRepository, times(1)).findById(1L);
        verify(boardRepository, times(1)).save(any(Board.class));
    }

    @Test
    void 게시글_수정_테스트_성공2() {
        // Given
        BoardRequestDto requestDto = new BoardRequestDto("Update");
        CreateBoardVo createBoardVo = new CreateBoardVo(requestDto.getTitle());
        Board board2 = new Board(2L, "Original"); // 새로운 테스트 데이터

        when(boardRepository.findById(2L)).thenReturn(Optional.of(board2));
        when(boardRepository.save(any(Board.class))).thenReturn(board2);

        // When
        BoardResponseDto responseDto = boardService.updateBoard(2L, createBoardVo);

        // Then
        assertEquals("Update", responseDto.getTitle());
        verify(boardRepository, times(1)).findById(2L); // findById 호출 검증
        verify(boardRepository, times(1)).save(any(Board.class)); // save 호출 안됨
    }

    @Test
    void 게시글_삭제_테스트_성공() {
        // Given
        when(boardRepository.findById(1L)).thenReturn(Optional.of(board1));
        doNothing().when(boardRepository).deleteById(1L);

        // When
        BoardResponseDto responseDto = boardService.deleteBoard(1L);

        // Then
        assertEquals("Test1", responseDto.getTitle());
        verify(boardRepository, times(1)).findById(1L);
        verify(boardRepository, times(1)).deleteById(1L);
    }

    @Test
    void 게시글_삭제_실패() {
        // Given
        Long invalidId = 123L;
        when(boardRepository.findById(invalidId)).thenReturn(Optional.empty());

        // When & Then
        CustomException exception = assertThrows(CustomException.class, () -> {
            boardService.deleteBoard(invalidId);
        });

        // 예외의 에러 코드와 메시지를 검증
        assertThat(exception.getCustomErrorCode()).isEqualTo(CustomErrorCode. BOARD_NOT_FOUND);
        assertThat(exception.getCustomErrorCode().getMessage()).isEqualTo("해당 게시글을 찾을 수 없습니다.");

        // findById 호출 횟수 검증
        verify(boardRepository, times(1)).findById(invalidId);
 }


}