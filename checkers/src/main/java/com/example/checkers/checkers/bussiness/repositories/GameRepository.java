package com.example.checkers.checkers.bussiness.repositories;

import com.example.checkers.checkers.models.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
