package com.example.checkers.checkers.bussiness.repositories;

import com.example.checkers.checkers.bussiness.Move;
import com.example.checkers.checkers.models.entities.MoveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoveRepository extends JpaRepository<MoveRequest, Long> {

}
