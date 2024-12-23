package org.example.dto;

import lombok.Getter;

@Getter
public class MemberLoginResponseDto {

    private final String email;
    private final String password;

    public MemberLoginResponseDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
