package com.example.checkers.checkers.bussiness.repositories;

import com.example.checkers.checkers.models.entities.Move;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoveRepository extends JpaRepository<Move, Long> {
}
