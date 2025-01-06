package org.example.dto;

import lombok.Getter;

@Getter
public class FeignClientResponseDto {
    private Long userId;
    private Long id;
    private String title;
    private String body;
}
