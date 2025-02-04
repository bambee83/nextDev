package org.example.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class MemberSignupRequestDto {

    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    private String email; // 이메일 (유니크)

    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    private String password; // 비밀번호

    @NotBlank(message = "이름은 필수 입력 항목입니다.")
    private String name; // 이름

}
