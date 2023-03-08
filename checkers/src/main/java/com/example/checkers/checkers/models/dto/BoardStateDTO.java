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

    @Override
    public String toString() {
        StringBuilder boardString = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (j == 0) {
                    if(i < 9){
                        boardString.append(0).append(i + 1).append("  ");
                    }else {
                        boardString.append(i + 1).append("  ");
                    }
                }
                Piece piece = currentState[i][j];
                if (piece == null) {
                    boardString.append('-');
                } else {
                    if (piece.isQueen()) {
                        boardString.append(Character.toUpperCase(piece.getColour()));
                    } else {
                        boardString.append(piece.getColour());
                    }
                }
                boardString.append(" ");
            }
            if(i < 9){
                boardString.append(" " + 0).append(i + 1).append("\n");
            }else {
                boardString.append(" ").append(i + 1).append("\n");
            }
        }
        boardString.append(" \n");
        return boardString.toString();
    }
}
