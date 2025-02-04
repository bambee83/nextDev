package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.MemberLoginRequestDto;
import org.example.dto.MemberLoginResponseDto;
import org.example.dto.MemberSignupRequestDto;
import org.example.dto.MemberSignupResponseDto;
import org.example.exception.ResponseMessage;
import org.example.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<ResponseMessage> signup(@RequestBody @Valid MemberSignupRequestDto requestDto) {
        MemberSignupResponseDto responseDto = memberService.signup(requestDto);  // vo : value object
        return ResponseMessage.success(HttpStatus.CREATED, "회원가입 성공", responseDto);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<ResponseMessage> login(@RequestBody @Valid MemberLoginRequestDto requestDto, HttpSession session) {
        MemberLoginResponseDto responseDto = memberService.login(requestDto, session);
        return ResponseMessage.success(HttpStatus.OK, "로그인 성공", responseDto);
    }

    // 로그아읏
    @PostMapping("/logout")
    public ResponseEntity<ResponseMessage> logout(HttpSession session) {
        memberService.logout(session); // 로그아웃 서비스 호출
        return ResponseMessage.success(HttpStatus.OK, "로그아웃 성공", null);
    }


}
