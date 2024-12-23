package org.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

@Schema(description = "내용 요청 정보")
public class BoardRequestDto {

    @Schema(description = "내용", required = true, example = "내용 예시입니다.")
    @NotBlank(message = "내용은 필수값입니다.")
    private String title;

    public String getTitle() {
        return title;
    }

    // 기본 생성자 (Spring 및 Jackson이 필요)
    public BoardRequestDto() {}

//    // testCode 용 추가 생성자 (Service 단)
    public BoardRequestDto(String title) {
        this.title = title;
    }
}
