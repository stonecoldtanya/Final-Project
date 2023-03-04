package com.example.checkers.checkers.bussiness;



import com.example.checkers.checkers.models.entities.Move;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.List;

/**
 * The type Bot player.
 */
@Component("bot")
public class BotPlayer implements Player {
    private char colour;
    private int depth;
    private String name;
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

    public BotPlayer(String name, Difficulty difficulty, char colour) {
        this.difficulty = difficulty;
        this.colour = colour;
        this.depth = difficulty.getDepthDifficulty();
        this.name = name;
    }


    /**
     * Instantiates a new Bot player.
     * @param difficulty
     *
     */
    public BotPlayer(Difficulty difficulty) {
        depth = this.difficulty.getDepthDifficulty();
        name  = getName();
    }

    public String getName() {
        return "Otto the AI";
    }

    public Move getNextMove(Board board) {
        return getNextMove(board, board.possibleMoves(board, colour), colour);
    }


    @Override
    public char getColour() {
       return this.colour;
    }

    @Override
    public char getOppositeColour() {
        if (getColour() == 'b'){
            return 'w';
        }
        return 'b';
    }

    public void setColour(char colour) {
        this.colour = colour;
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
        int highest = Integer.MIN_VALUE;
        List<Move> equal = new ArrayList<>();
        for (Move move : moves){
            int value = minimax(board, this.depth);
            if (value > highest){
                highest = value;
                equal.clear();
            }
            if (value == highest){
                equal.add(move);
            }
        }
        if(equal.size() > 1){
            System.out.println("Random best move incoming!");
        }
        // will choose a random move from the list
        return findRandomMove(equal);
    }

    private Move findRandomMove(List<Move> moves){
        if (moves.size() < 1){
            throw new RuntimeException("Can't randomly choose from empty list.");
        }
        Random rand = new Random();
        int i = rand.nextInt(moves.size());
        return moves.get(i);
    }
    private int minimax(Board boardState, int depth){
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        
        return minimax(boardState, depth, alpha, beta);
    }
    private int minimax(Board boardState, int depth, int alpha, int beta){
        if (depth == 0 || boardState.isFinal()){
            return complexHeuristic(boardState);
        }
        // MAX player = player
        if (boardState.isBTPlayer()){
            int value = Integer.MIN_VALUE;
            for (Move move: boardState.possibleMoves(boardState, getColour())){
                boardState.update(move);
                value = Math.max(value, minimax(boardState, depth-1, alpha, beta));
                alpha = Math.max(alpha, value);
                // prune
                if (alpha >= beta){
                    break;
                }
            }
            return value;
        }
        // MIN player = opponent
        if (!boardState.isBTPlayer()){
            int value = Integer.MAX_VALUE;
            for (Move move: boardState.possibleMoves(boardState, getColour())){
                boardState.update(move);
                value = Math.min(value,minimax(boardState, depth-1, alpha, beta));
                beta = Math.min(beta, value);
                // prune
                if (alpha >= beta){
                    break;
                }
            }
            return value;
        }
        throw new RuntimeException("Error in minimax algorithm");
    }
    

    private static int heuristic(Board board) {
        // +2 for a regular piece, +4 for queens
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
