package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.FeignClientResponseDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeignClientService {
    private final FeignClientInterface feignClientInterface;

    public FeignClientResponseDto getPostById(Long id) {
        return feignClientInterface.getPostById(id);
    }
}
