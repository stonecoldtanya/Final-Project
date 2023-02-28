package com.example.checkers.checkers.models.entities;

import com.example.checkers.checkers.bussiness.Piece;

import javax.persistence.*;

@Entity
@Table(name = "board_states")
public class BoardState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Piece[][] currentState;

    public BoardState() {
    }

    public BoardState(Piece[][] currentState) {
        this.currentState = currentState;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Piece[][] getCurrentState() {
        return currentState;
    }

    public void setCurrentState(Piece[][] currentState) {
        this.currentState = currentState;
    }
}
