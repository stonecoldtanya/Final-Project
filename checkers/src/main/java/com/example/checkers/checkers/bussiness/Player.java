package com.example.checkers.checkers.bussiness;

public interface Player {
   String getName();

    Move getNextMove(Board board);

    char getColor();

    char getOppositeColour();
}

