package com.example.checkers.checkers.models.dto;

import com.example.checkers.checkers.bussiness.Piece;
import com.example.checkers.checkers.models.entities.BoardState;

public class BoardStateDTO {
    private Long id;
    private Piece[][] currentState;

    public BoardStateDTO() {
    }

    public BoardStateDTO(Long id, Piece[][] currentState) {
        this.id = id;
        this.currentState = currentState;
    }

    public static BoardStateDTO fromEntity(BoardState entity) {
        return new BoardStateDTO(entity.getId(), entity.getCurrentState());
    }

    public static BoardState updateEntity(BoardState boardState, BoardStateDTO dto) {
        // don't change the id
        boardState.setCurrentState(dto.getCurrentState());

        return boardState;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Piece[][] getCurrentState() {
        return currentState;
    }

    public void setCurrentState(Piece[][] currentState) {
        this.currentState = currentState;
    }

}
