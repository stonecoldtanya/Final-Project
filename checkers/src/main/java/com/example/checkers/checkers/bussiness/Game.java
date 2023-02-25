package com.example.checkers.checkers.bussiness;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("game")
public class Game {
    private Board b;
    private Player player1;
    private Player player2;

    public Optional<Integer> getFinalScore() {
        return Optional.ofNullable(finalScore);
    }

    private Integer finalScore;

    @Autowired
    public Game(Board startingState) {
        this.b = startingState;
    }


    public Game(Piece[][] b){
        this( new Board(b));
    }


    public Game() {
    }

    @Autowired
    @Qualifier("consoleGamer")
    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    @Autowired
    @Qualifier("bot")
    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void play(int moves){
        if (player1 == null || player2 == null) {
            System.out.println("Both player should join before the game begins");
            return;
        }


        Player whoseTurn = b.isBTPlayer() ? player1 : player2;

        int movesLeft = moves;


        while (!b.isFinal() && movesLeft != 0) {

            Move move = whoseTurn.getNextMove(this.b);
            this.move(move);

            System.out.printf("%s made a move: %s \n", whoseTurn.getName(), move.toString());

            whoseTurn = (whoseTurn == player1) ? player2 : player1;
            movesLeft--;

        }

        if (b.isFinal()) {

            finalScore = b.getScore();


            System.out.printf("Game Over!\n");

            System.out.println(b.toString());

            if (finalScore == 0) {
                System.out.println("It's a draw!");
            } else {
                String wonLost = (finalScore > 0) ? "won" : "lost";
                System.out.printf("Player 1: %s %s and gets %d points!\n", player1.getName(), wonLost, b.getScore());
            }
        }
    }


    public void move(Move move) {
        this.b = this.b.move(move);

    }


    public Board getState() {
        return this.b;
    }

}
