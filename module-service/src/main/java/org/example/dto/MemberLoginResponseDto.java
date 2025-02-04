package org.example.dto;

import lombok.Getter;
import org.example.entity.Member;


@Getter
public class MemberLoginResponseDto {
    private final String email;

    public MemberLoginResponseDto(Member member) {
        this.email = member.getEmail();
    }
}
