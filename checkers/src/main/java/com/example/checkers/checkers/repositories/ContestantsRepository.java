package com.example.checkers.checkers.repositories;

import com.example.checkers.checkers.models.entities.Contestant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ContestantsRepository extends JpaRepository<Contestant, Long> {
    Optional<Contestant> findByEmail(String email);

    Optional<Contestant>findByUsername(String username);
}
