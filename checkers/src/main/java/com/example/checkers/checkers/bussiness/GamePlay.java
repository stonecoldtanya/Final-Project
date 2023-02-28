package com.example.checkers.checkers.bussiness;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

//@Component
public class GamePlay {

    private Board b;
    private Player player1;
    private Player player2;

    public Optional<Integer> getFinalScore() {
        return Optional.ofNullable(finalScore);
    }

    private Integer finalScore;

    public GamePlay(Piece[][]startingState) {
        this.b = new Board(startingState);
    }
    @Autowired
    public GamePlay(Board board) {
        this.b = board;
    }

    public GamePlay() {
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

        while (b.isFinal() && movesLeft != 0) {
            Move move = whoseTurn.getNextMove(b);
            this.move(move);

            System.out.printf("%s made a move: %s \n", whoseTurn.getName(), move.toString());

            whoseTurn = (whoseTurn == player1) ? player2 : player1;
            movesLeft--;

        }
        if (movesLeft == 0){
            System.out.printf("Game Over!\n");
            if (b.getPieceWhite() < b.getPieceBlack()){
                Player BTPlayer = b.isBTPlayer() ? player1 : player2;
                System.out.printf("Our current winner is %s! Well done! \n", BTPlayer.getName());
            }
            if (b.getPieceBlack() < b.getPieceWhite()){
                Player NotBTPlayer = !b.isBTPlayer() ? player1 : player2;
                System.out.printf("Our current winner is %s! Well done! \n", NotBTPlayer.getName());
            }
            else {
                System.out.printf("It's a draw! What a shame!");
            }
        }
        if (!b.isFinal()) {
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
        this.b = this.b.update(move);

    }

    @Autowired
    @Qualifier("boarded")
    public Board getState() {
        return this.b;
    }

}
