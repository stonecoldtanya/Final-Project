package com.example.checkers;

import com.example.checkers.checkers.bussiness.*;
import com.example.checkers.checkers.bussiness.services.ContestantService;
import com.example.checkers.checkers.models.entities.Contestant;
import com.example.checkers.checkers.models.entities.Game;

public class Main {
    public static void main(String[] args) {
//        Board b = new Board(8);
//        GamePlay game = new GamePlay(b.getBoard());
//        game.setPlayer1(new BotPlayer(Difficulty.HARD, 'b'));
//        game.setPlayer2(new ConsoleGamer("Tanya", 'w'));
//
//        game.play(-1);
//        System.out.println(b.getPieceWhite());
//        System.out.println(b.getPieceBlack());

        Piece[][] bor = {{null, null, null, null, null, null, null, null},
                        {null,null, null, null, null, null, null, null},
                        {null, null, null,null, null, null, null, null},
                        { null, null, null, null, null, null,null, null},
                        {null, null,null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null},
                        {null, null, new Piece('b', true), null,new Piece('w', true), null, null, null}};

        Board borde = new Board(bor);
        System.out.println(borde.getQueenBlack());
        System.out.println(borde.getQueenWhite());
        GamePlay game = new GamePlay(borde.getBoard());
        game.setPlayer1(new BotPlayer(Difficulty.HARD, 'b'));
        game.setPlayer2(new ConsoleGamer("Tanya", 'w'));

        game.play(-1);
        System.out.println(borde.getPieceWhite());
        System.out.println(borde.getPieceBlack());
        System.out.println(borde.getQueenBlack());
        System.out.println(borde.getQueenWhite());
    }
}
