package com.example.checkers.checkers.repositories;

import com.example.checkers.checkers.models.entities.Contestant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContestantsRepository extends JpaRepository<Contestant, Long> {
}
