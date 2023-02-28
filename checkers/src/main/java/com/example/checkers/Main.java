package com.example.checkers;

import com.example.checkers.checkers.bussiness.*;

public class Main {
    public static void main(String[] args) {
//        Board b = new Board(8);
//        GamePlay game = new GamePlay(b.getBoard());
//        game.setPlayer1(new BotPlayer(Difficulty.HARD, 'b'));
//        game.setPlayer2(new ConsoleGamer("Tanya", 'w'));
//
//        game.play(6);
        App app = new App();
        app.run();
    }
}
