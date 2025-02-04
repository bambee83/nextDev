package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.MemberLoginRequestDto;
import org.example.dto.MemberLoginResponseDto;
import org.example.dto.MemberSignupRequestDto;
import org.example.dto.MemberSignupResponseDto;
import org.example.entity.Member;
import org.example.entity.Role;
import org.example.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder; // 비밀번호 암호화를 위해 사용

    // 회원가입
    @Transactional
    public MemberSignupResponseDto signup(MemberSignupRequestDto requestDto) {
        // 이메일 중복 체크
        if (memberRepository.existsByEmail(requestDto.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        // Member 엔티티 생성
        Member member = new Member(
                requestDto.getEmail(),
                passwordEncoder.encode(requestDto.getPassword()),
                requestDto.getName(),
                Role.USER // 기본 회원 권한 설정
        );
        return new MemberSignupResponseDto(memberRepository.saveAndFlush(member));
    }


    // 로그인
    @Transactional
    public MemberLoginResponseDto login(MemberLoginRequestDto requestDto, HttpSession session) {
        // 이메일로 사용자 조회
        Member member = memberRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        // 비밀번호 비교
        if (!passwordEncoder.matches(requestDto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        // 세션에 사용자 정보 저장
        // 세션에 사용자 이메일 저장
        session.setAttribute("email", member.getEmail()); // 이메일을 세션에 추가
        session.setMaxInactiveInterval(3600); // 세션 만료 시간 설정 (1시간)
        return new MemberLoginResponseDto(member);
    }

    // 로그아웃
    public void logout(HttpSession session) {
        // 세션에서 이메일 정보 가져오기
        String email = (String) session.getAttribute("email");

        if (email != null) {
            // 해당 사용자의 세션 정보 삭제
            session.removeAttribute("email"); // 세션에서 이메일 정보 삭제
            log.info("User {} has been logged out successfully", email); // 로그 출력
        } else {
//            throw new CustomException(CustomErrorCode.AUTH_FAIL);
        }
    }
}
