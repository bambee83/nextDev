package org.example.utils;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

// 외부 API 호출을 위해 사용하는 인터페이스
@FeignClient(name = "userClient", url = "https://jsonplaceholder.typicode.com")
public interface JsonPlaceholderClient {

    @GetMapping("/posts/{id}")
    Post getPostById(@PathVariable("id") Long id);
}
