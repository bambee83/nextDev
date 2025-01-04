package org.example.utils;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// 외부 API 호출을 위해 사용하는 인터페이스
@FeignClient(name = "postClient", url = "https://jsonplaceholder.typicode.com")
public interface PostClient {

    @GetMapping("/posts/{id}")
    PostResponseDto getPostById(@PathVariable("id") Long id);

}
