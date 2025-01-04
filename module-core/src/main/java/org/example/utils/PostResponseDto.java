package org.example.utils;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class PostResponseDto {
    @NotNull
    private Long id;
    private String title;
    private String body;
}
