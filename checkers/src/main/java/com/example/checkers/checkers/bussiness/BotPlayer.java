package com.example.checkers.checkers.bussiness;



import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * The type Bot player.
 */
@Component("bot")
public class BotPlayer implements Player {
    private char colour;
    private int depth;
    private String player;
    private Difficulty difficulty;
    private static final int[][] weightEightXEight = {{0, 4, 0, 4, 0, 4, 0, 4},
                                                     {4, 0, 3, 0, 3, 0, 3, 0},
                                                     {0, 3, 0, 2, 0, 2, 0, 4},
                                                     {4, 0, 2, 0, 1, 0, 3, 0},
                                                     {0, 3, 0, 1, 0, 2, 0, 4},
                                                     {4, 0, 2, 0, 2, 0, 3, 0},
                                                     {0, 3, 0, 3, 0, 3, 0, 4},
                                                     {4, 0, 4, 0, 4, 0, 4, 0}};

    private static final int[][] defWeightPosTenXTen = {{0, 4, 0, 4, 0, 4, 0, 4, 0, 4},
                                                        {4, 0, 3, 0, 3, 0, 3, 0, 3, 0},
                                                        {0, 3, 0, 2, 0, 2, 0, 2, 0, 4},
                                                        {4, 0, 2, 0, 1, 0, 1, 0, 3, 0},
                                                        {0, 3, 0, 1, 0, 1, 0, 2, 0, 4},
                                                        {4, 0, 2, 0, 1, 0, 1, 0, 3, 0},
                                                        {0, 3, 0, 1, 0, 1, 0, 2, 0, 4},
                                                        {4, 0, 2, 0, 2, 0, 2, 0, 3, 0},
                                                        {0, 3, 0, 3, 0, 3, 0, 3, 0, 4},
                                                        {4, 0, 4, 0, 4, 0, 4, 0, 4, 0}};

    public BotPlayer() {
    }

    public BotPlayer(Difficulty difficulty, char b) {
    }

    /**
     * Instantiates a new Bot player.
     *
     * @param depth  the depth
     */
    public BotPlayer(int depth, char colour) {
        colour = colour;
    }

    /**
     * Instantiates a new Bot player.
     * @param difficulty
     *
     */
    public BotPlayer(Difficulty difficulty) {
        depth = this.difficulty.getDepthDifficulty();
        player = getName();
    }

    public String getName() {
        return "Otto the AI";
    }

    public Move getNextMove(Board board) {
        return null;
    }


    public char getColour() {
        return this.colour;
    }

    public Move getNextMove(Board board, List<Move> moves, char color) {
        Move bestMove;
        if (this.difficulty.getDepthDifficulty() == 100) {
            bestMove = minimaxMove(board, moves);
        }
        else if (this.difficulty.getDepthDifficulty() == 0) {
            bestMove = findRandomMove(moves);
        }
        return bestMove = minimaxMove(board, moves);
    }

    private Move minimaxMove(Board board, List<Move> moves){
        if (moves.size() == 1){
            return moves.get(0);
        }
        int bestScore = Integer.MIN_VALUE;
        List<Move> equalBests = new ArrayList<>();
        for (Move succ : moves){
            int val = minimax(board, this.depth);
            if (val > bestScore){
                bestScore = val;
                equalBests.clear();
            }
            if (val == bestScore){
                equalBests.add(succ);
            }
        }
        if(equalBests.size() > 1){
            System.out.println(player.toString() + " choosing random best move");
        }
        // choose randomly from equally scoring best moves
        return findRandomMove(equalBests);
    }

    private Move findRandomMove(List<Move> moves){
        if (moves.size() < 1){
            throw new RuntimeException("Can't randomly choose from empty list.");
        }
        Random rand = new Random();
        int i = rand.nextInt(moves.size());
        return moves.get(i);
    }
    private int minimax(Board node, int depth){
        // initialize alpha (computed as a max)
        int alpha = Integer.MIN_VALUE;
        // initialize beta (computed as a min)
        int beta = Integer.MAX_VALUE;
        // call minimax
        return minimax(node, depth, alpha, beta);
    }
    private int minimax(Board node, int depth, int alpha, int beta){
        if (depth == 0 || node.isFinal()){
            return complexHeuristic(node);
        }
        // MAX player = player
        if (node.getTurn().getName() == player){
            // player tries to maximize this value
            int v = Integer.MIN_VALUE;
            for (Move move: node.possibleMoves(node, getColour())){
                node.update(move);
                v = Math.max(v, minimax(node, depth-1, alpha, beta));
                alpha = Math.max(alpha, v);
                // prune
                if (alpha >= beta){
                    break;
                }
            }
            return v;
        }
        // MIN player = opponent
        if (node.getTurn().getName() != player){
            // opponent tries to minimize this value
            int v = Integer.MAX_VALUE;
            for (Move move: node.possibleMoves(node, getColour())){
                node.update(move);
                v = Math.min(v,minimax(node, depth-1, alpha, beta));
                beta = Math.min(beta, v);
                // prune
                if (alpha >= beta){
                    break;
                }
            }
            return v;
        }
        throw new RuntimeException("Error in minimax algorithm");
    }




    private static int heuristic(Board board) {
        // +2 for pawn, +4 for king
        int black = (board.getPieceBlack() + board.getQueenBlack()) * 2;
        int white = (board.getPieceWhite() + board.getQueenWhite()) * 2;

        return black - white;
    }

    private int complexHeuristic(Board board) {
        double queenFactor = 1.5;
        double cellFactor = 0.75;

        int blackCellWeight = 0;
        int whiteCellWeight = 0;

        int blackQueens = board.getQueenBlack();
        int whiteQueens = board.getQueenWhite();

        int blackPieces = board.getPieceBlack() - board.getQueenBlack();
        int whitePieces = board.getPieceWhite() - board.getQueenWhite();

        for (int i = 0; i < board.getBoardLength(); i++) {
            for (int j = (i + 1) % 2; j < board.getBoardLength(); j += 2) {
                if (board.getBoard()[i][j] != null) {
                    if (board.getBoard()[i][j].getColour() == 'b') {
                        if (board.getBoardLength() == 8) {
                            blackCellWeight += weightEightXEight[i][j];
                        }
                        else {
                            blackCellWeight += defWeightPosTenXTen[i][j];
                        }
                    } else {
                        if (board.getBoardLength() == 8) {
                            whiteCellWeight += weightEightXEight[i][j];
                        }
                        else {
                            whiteCellWeight += defWeightPosTenXTen[i][j];
                        }
                    }
                }
            }
        }

        int trade;
        int startingCount = (board.getBoardLength() - 2) / 2 * board.getBoardLength();
        if (board.getPieceBlack() > board.getPieceWhite()) {
            trade = startingCount - board.getPieces();
        } else {
            trade = startingCount + board.getPieces();
        }
        return (int) ((whitePieces-blackPieces) + (queenFactor * (whiteQueens-blackQueens)) + (cellFactor * (whiteCellWeight-blackCellWeight)) * 1000) + trade;

    }
    }
