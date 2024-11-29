package org.example.nexthw.repository;

import org.example.nexthw.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository <Board, Long> {
}
