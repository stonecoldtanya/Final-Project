package com.example.checkers.checkers.bussiness;

public interface Player {
    public String getName();

    public Move getNextMove(Board board);

    public char getColour();

    char getOppositeColour();
}

