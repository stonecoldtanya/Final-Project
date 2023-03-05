package com.example.checkers;

import com.example.checkers.checkers.bussiness.*;
import com.example.checkers.checkers.bussiness.services.ContestantService;
import com.example.checkers.checkers.models.entities.Contestant;
import com.example.checkers.checkers.models.entities.Game;

public class Main {
    public static void main(String[] args) {
        Board b = new Board(8);
        GamePlay game = new GamePlay(b.getBoard());

        Contestant contestant = new Contestant("Lea", 'b');
        Game game2 = new Game(contestant, Difficulty.HARD);
    }
}
