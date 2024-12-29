package org.example.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final JsonPlaceholderClient jsonPlaceholderClient;

    public Post getPostById(Long id) {
        return jsonPlaceholderClient.getPostById(id);
    }
}
