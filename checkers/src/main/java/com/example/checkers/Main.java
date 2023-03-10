package com.example.checkers;

import com.example.checkers.checkers.bussiness.*;

public class Main {
    public static void main(String[] args) {
        Piece[][] bor = {{null, null, null, null, null, null, null, null},
                        {null,null, null, null, null, null, null, null},
                        {null, null, null,null, null, null, null, null},
                        { null, null, null, null, new Piece('b', true), null,null, null},
                        {null, null,null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null},
                        {null, null, new Piece('b', true), null,new Piece('w', true), null, null, null}};

        Board board= new Board(bor);
        GamePlay game = new GamePlay(board.getBoard());
        game.setPlayer1(new BotPlayer(Difficulty.HARD, 'b'));
        game.setPlayer2(new ConsoleGamer("Tanya", 'w'));

        game.play(-1);
    }
}
