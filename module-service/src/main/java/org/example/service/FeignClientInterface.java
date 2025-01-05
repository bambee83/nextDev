package org.example.service;

import org.example.config.FeignConfig;
import org.example.dto.FeignClientResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// 외부 API 호출을 위해 사용하는 인터페이스
@FeignClient(name = "feignClient", url = "https://jsonplaceholder.typicode.com", configuration = FeignConfig.class)
public interface FeignClientInterface {

    @GetMapping("/posts/{id}")
    FeignClientResponseDto getPostById(@PathVariable("id") Long id);

}
