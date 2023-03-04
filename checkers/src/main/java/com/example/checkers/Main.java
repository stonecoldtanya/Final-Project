package com.example.checkers;

import com.example.checkers.checkers.bussiness.*;
import com.example.checkers.checkers.bussiness.services.ContestantService;

public class Main {
    public static void main(String[] args) {
        Board b = new Board(8);
        GamePlay game = new GamePlay(b.getBoard());


        game.play(6);
    }
}
