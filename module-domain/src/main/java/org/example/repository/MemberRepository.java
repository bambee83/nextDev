package org.example.repository;

import org.example.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public  interface MemberRepository extends JpaRepository <Member, Long> {
    boolean existsByEmail(String email); // 이메일 중복 체크

    Optional<Member> findByEmail(String email); // 이메일로 회원을 조회
}
