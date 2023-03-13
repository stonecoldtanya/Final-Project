package com.example.checkers.checkers.repositories;

import com.example.checkers.checkers.models.entities.BoardState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardStateRepository extends JpaRepository<BoardState, Long> {
}
