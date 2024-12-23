package org.example.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/session")
public class SessionController {
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        // 간단한 인증 로직 (실제 서비스에선 DB 조회 필요)
        if ("user".equals(username) && "password".equals(password)) {
            session.setAttribute("user", username); // 세션에 사용자 정보 저장
            return "Login success! Session ID: " + session.getId();
        } else {
            return "Invalid credentials";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return "Logged out success!";
    }

    @GetMapping("/current")
    public String getCurrentUser(HttpSession session) {
        String user = (String) session.getAttribute("user"); // 세션에서 사용자 정보 가져오기
        if (user != null) {
            return "Current user: " + user;
        } else {
            return "No user";
        }
    }
}
