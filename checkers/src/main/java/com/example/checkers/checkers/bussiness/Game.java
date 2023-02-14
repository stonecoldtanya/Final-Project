package com.example.checkers.checkers;

public class Game {
    private Board gameBoard;
    private Player blackTilePlayer;
    private Player whiteTilePlayer;


    public void play(int moves) {

        if (blackTilePlayer == null || whiteTilePlayer== null) {

            System.out.println("Both player should join before the game begins");

            return;

        }


        Player whoseTurn = gameBoard.is ? blackTilePlayer : whiteTilePlayer;

        int movesLeft = moves;


        while (gameBoard.getPieceBlack() != 0 || gameBoard.getPieceWhite() != 0){

            Move move = whoseTurn.getNextMove(this.gameBoard);

            this.move(move);

            System.out.printf("%s made a move: %s \n", whoseTurn.getName(), move.toString());


            whoseTurn = (whoseTurn == blackTilePlayer) ? whiteTilePlayer : blackTilePlayer;

            movesLeft--;

        }


        if (state.isFinal()) {

            finalScore = state.getScore();


            System.out.printf("Game Over!\n");

            System.out.println(state.toString());

            if (finalScore == 0) {

                System.out.println("It's a draw!");

            } else {

                String wonLost = (finalScore > 0) ? "won" : "lost";

                System.out.printf("Player 1: %s %s and gets %d points!\n", player1.getName(), wonLost, state.getScore());

            }

        }


    }


    public void move(Move move) {

        this.state = this.state.move(move);

    }


    public Node getState() {

        return this.state;

    }

}
