package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.exception.ResponseMessage;
import org.example.service.AutoCompleteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autocomplete") @RequiredArgsConstructor
@Tag(name = "AutoComplete", description = "자동완성 API")
public class AutoCompleteController {
    private final AutoCompleteService autoCompleteService;

    @Operation(summary = "자동완성 단어 추가", description = "자동완성 기능에 단어를 추가합니다.")
    @PostMapping
    public ResponseEntity<ResponseMessage> addWord(@RequestParam String word) {
        autoCompleteService.addTrie(word);
        return ResponseMessage.success(HttpStatus.CREATED, "단어 추가 성공", null);
    }

    @Operation(summary = "자동완성 단어 검색", description = "입력한 단어로 시작하는 모든 단어를 검색합니다.")
    @GetMapping
    public ResponseEntity<ResponseMessage> searchWord(@RequestParam String prefix) {
        List<String> result = autoCompleteService.autoComplete(prefix);
        return ResponseMessage.success(HttpStatus.OK, "단어 검색 성공", result);
    }

    @Operation(summary = "자동완성 단어 검색 (DB 연동)", description = "DB에서 입력한 단어로 시작하는 제목을 검색합니다.")
    @GetMapping("/db")
    public ResponseEntity<ResponseMessage> searchWordFromDB(@RequestParam String prefix) {
        List<String> result = autoCompleteService.autoComplete2(prefix);
        return ResponseMessage.success(HttpStatus.OK, "DB 단어 검색 성공", result);
    }

    @Operation(summary = "자동완성 단어 삭제", description = "자동완성 기능에서 특정 단어를 삭제합니다.")
    @DeleteMapping
    public ResponseEntity<ResponseMessage> deleteWord(@RequestParam String word) {
        autoCompleteService.deleteTrie(word);
        return ResponseMessage.success(HttpStatus.OK, "단어 삭제 성공", null);
    }
}
