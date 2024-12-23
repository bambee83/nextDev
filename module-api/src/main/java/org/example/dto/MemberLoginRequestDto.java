package org.example.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class MemberLoginRequestDto {
    @NotBlank(message = "이메일을 입력하세요.")
    private String email;

    @NotBlank(message = "비밀번호를 입력하세요.")
    private String password;

    public MemberLoginResponseDto login() {
        return new MemberLoginResponseDto(email, password);
    }
}
