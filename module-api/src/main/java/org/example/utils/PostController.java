package org.example.utils;

import lombok.RequiredArgsConstructor;
import org.example.exception.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage> getPostById(@PathVariable Long id) {
        return ResponseMessage.success(HttpStatus.OK, "FeignClient 조회 성공", postService.getPostById(id));
    }
}
