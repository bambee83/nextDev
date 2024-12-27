package org.example.repository;

import org.example.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Deprecated
@Repository
public interface MemberRepository extends JpaRepository <Member, Long> {
    Optional<Member> findByEmail(String email);
}
