package com.example.checkers.checkers.bussiness;

import com.example.checkers.checkers.bussiness.Board;
import com.example.checkers.checkers.bussiness.Move;
import org.springframework.stereotype.Component;

public interface Player {

    public String getName();

    public Move getNextMove(Board board);

    public char getColour();

}

