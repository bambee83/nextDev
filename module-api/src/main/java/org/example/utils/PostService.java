package org.example.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostClient postClient;

    public PostResponseDto getPostById(Long id) {
        return postClient.getPostById(id);
    }
}
