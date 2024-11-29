package org.example.nexthw.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel(description = "내용 요청 정보")
public class BoardRequestDto {

    @ApiModelProperty(value = "내용", required = true, example = "내용 예시 입니다.")
    @NotBlank(message = "내용은 필수값입니다.")
    private String title;

    public String getTitle() {
        return title;
    }
}
