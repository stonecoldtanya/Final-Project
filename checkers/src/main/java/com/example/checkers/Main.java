package com.example.checkers;

import com.example.checkers.checkers.bussiness.*;
import com.example.checkers.checkers.bussiness.services.ContestantService;
import com.example.checkers.checkers.models.entities.Contestant;
import com.example.checkers.checkers.models.entities.Game;

public class Main {
    public static void main(String[] args) {
        Piece[][] bor = {{null, null, null, null, null, null, null, null},
                         {null,null, new Piece('b', false), null, null, null, null, null},
                        {null, null, null,null, null, null, null, null},
                        { null, null, null, null, null, null,null, null},
                        {null, null,null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null},
                        {null, null, new Piece('b', true), null,new Piece('w', true), null, null, null}};

        Board borde = new Board(bor);
        GamePlay game = new GamePlay(borde.getBoard());
        game.setPlayer1(new BotPlayer(Difficulty.BOT_EXPERT, 'b'));
        game.setPlayer2(new ConsoleGamer("Tanya", 'w'));

        game.play(-1);
    }
}
