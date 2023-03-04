package com.example.checkers.checkers.bussiness;

import com.example.checkers.checkers.models.entities.Move;

public interface Player {

    public String getName();

    public Move getNextMove(Board board);

    public char getColour();

    char getOppositeColour();
}

