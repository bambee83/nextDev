package org.example.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class FeignClientResponseDto {
    private Integer userId;
    private Long id;
    private String title;
    private String body;
}
