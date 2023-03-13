package com.example.checkers.checkers.models.entities;
import com.example.checkers.checkers.bussiness.Board;
import com.example.checkers.checkers.bussiness.Piece;

import javax.persistence.*;
import java.util.Objects;
import javax.persistence.Table;

@Entity
@Table(name = "states")
public class BoardState extends Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Piece[][] currentState;

    public BoardState() {
    }

    public BoardState(long id) {
        this.id = id;
    }

    public BoardState(long id, Piece[][] currentState) {
        this.id = id;
        this.currentState = currentState;
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

    public BoardState(int boardLength) {
        super(boardLength);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardState that = (BoardState) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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