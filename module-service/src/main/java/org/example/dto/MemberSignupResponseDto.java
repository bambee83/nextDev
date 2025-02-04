package org.example.dto;

import lombok.Getter;
import org.example.entity.Member;

@Getter
public class MemberSignupResponseDto {
    private final String email;

    public MemberSignupResponseDto(Member member) {
        this.email = member.getEmail();
    }
}
