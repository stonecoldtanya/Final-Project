package com.example.checkers.checkers.bussiness.services;

import com.example.checkers.checkers.bussiness.*;
import com.example.checkers.checkers.models.entities.BoardState;
import com.example.checkers.checkers.models.entities.Game;
import com.example.checkers.checkers.bussiness.Move;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class BotPlayerService {
//        private GameService gameService;
//
//        private static final int[][] weightEightXEight = {{0, 4, 0, 4, 0, 4, 0, 4},
//                                                            {4, 0, 3, 0, 3, 0, 3, 0},
//                                                            {0, 3, 0, 2, 0, 2, 0, 4},
//                                                            {4, 0, 2, 0, 1, 0, 3, 0},
//                                                            {0, 3, 0, 1, 0, 2, 0, 4},
//                                                            {4, 0, 2, 0, 2, 0, 3, 0},
//                                                            {0, 3, 0, 3, 0, 3, 0, 4},
//                                                            {4, 0, 4, 0, 4, 0, 4, 0}};
//
//
//        public String getName() {
//            return "Otto the AI";
//        }
//        public char getColour(Game game){
//            return game.getContestant().getOppositeColour();
//        }
//
//        public int getDepth(Game game){
//            return game.getDifficulty().getDepthDifficulty();
//        }
//
//        public Move getNextMove(Game game) {
//            return getNextMove(game, possibleMoves(game.getCurrent(), getColour(game)), getColour(game));
//        }
//
//
//        public Move getNextMove(Game game, List<Move> moves, char color) {
//            Move bestMove;
//            if (game.getDifficulty().getDepthDifficulty() == 100) {
//                bestMove = minimaxMove(game, game.getCurrent(), moves);
//            }
//            else if (game.getDifficulty().getDepthDifficulty() == 0) {
//                bestMove = findRandomMove(moves);
//            }
//            return bestMove = minimaxMove(game, game.getCurrent(), moves);
//        }
//
//        private Move minimaxMove(Game game, BoardState board, List<Move> moves){
//            if (moves.size() == 1){
//                return moves.get(0);
//            }
//            int highest = Integer.MIN_VALUE;
//            List<Move> equal = new ArrayList<>();
//            for (Move move : moves){
//                int value = minimax(game, getDepth(game));
//                if (value > highest){
//                    highest = value;
//                    equal.clear();
//                }
//                if (value == highest){
//                    equal.add(move);
//                }
//            }
//            if(equal.size() > 1){
//                System.out.println("Random best move incoming!");
//            }
//            // will choose a random move from the list
//            return findRandomMove(equal);
//        }
//
//        private Move findRandomMove(List<Move> moves){
//            if (moves.size() < 1){
//                System.out.println("The ai has been defeated...Well done, mortal!");
//                moves.add(null);
////            throw new RuntimeException("Can't randomly choose from empty list.");
//            }
//            Random rand = new Random();
//            int i = rand.nextInt(moves.size());
//            return moves.get(i);
//        }
//
//        private int minimax(Game game, int depth){
//            int alpha = Integer.MIN_VALUE;
//            int beta = Integer.MAX_VALUE;
//
//            return minimax(game, depth, alpha, beta);
//        }
//        private int minimax(Game game, int depth, int alpha, int beta){
//            if (depth == 0){
//                return complexHeuristic(game.getCurrent());
//            }
//            // MAX player = player
//            if (game.getCurrent().isBTPlayer()){
//                int value = Integer.MIN_VALUE;
//                for (Move move: possibleMoves(game.getCurrent(), getColour(game))){
//                    game.getCurrent().update(move);
//                    value = Math.max(value, minimax(game, depth-1, alpha, beta));
//                    alpha = Math.max(alpha, value);
//                    // prune
//                    if (alpha >= beta){
//                        break;
//                    }
//                }
//                return value;
//            }
//            // MIN player = opponent
//            if (!game.getCurrent().isBTPlayer()){
//                int value = Integer.MAX_VALUE;
//                for (Move move: possibleMoves(game.getCurrent(), getColour(game))){
//                    game.getCurrent().update(move);
//                    value = Math.min(value,minimax(game, depth-1, alpha, beta));
//                    beta = Math.min(beta, value);
//                    // prune
//                    if (alpha >= beta){
//                        break;
//                    }
//                }
//                return value;
//            }
//            throw new RuntimeException("Error in minimax algorithm");
//        }
//
//
//        private static int heuristic(Board board) {
//            // +2 for a regular piece, +4 for queens
//            int black = (board.getPieceBlack() + board.getQueenBlack()) * 2;
//            int white = (board.getPieceWhite() + board.getQueenWhite()) * 2;
//
//            return black - white;
//        }
//
//        private int complexHeuristic(BoardState board){
//            double queenFactor = 1.5;
//            double cellFactor = 0.75;
//
//            int blackCellWeight = 0;
//            int whiteCellWeight = 0;
//
//            int blackQueens = board.getQueenBlack();
//            int whiteQueens = board.getQueenWhite();
//
//            int blackPieces = board.getPieceBlack() - board.getQueenBlack();
//            int whitePieces = board.getPieceWhite() - board.getQueenWhite();
//
//            for (int i = 0; i < board.getBoardLength(); i++) {
//                for (int j = (i + 1) % 2; j < board.getBoardLength(); j += 2) {
//                    if (board.getBoard()[i][j] != null) {
//                        if (board.getBoard()[i][j].getColour() == 'b') {
//                            blackCellWeight += weightEightXEight[i][j];
//
//                        } else {
//                            whiteCellWeight += weightEightXEight[i][j];
//                        }
//                    }
//                }
//            }
//
//            int trade;
//            int startingCount = (board.getBoardLength() - 2) / 2 * board.getBoardLength();
//            if (board.getPieceBlack() > board.getPieceWhite()) {
//                trade = startingCount - board.getPieces();
//            } else {
//                trade = startingCount + board.getPieces();
//            }
//            return (int) ((whitePieces-blackPieces) + (queenFactor * (whiteQueens-blackQueens)) + (cellFactor * (whiteCellWeight-blackCellWeight)) * 1000) + trade;
//        }
//
//    public boolean validate(Point next) {
//        if (next.x > 7 || next.x < 0 || next.y > 7 || next.y < 0) {
//            return false; // not valid if end location is outside board
//        }
//        return true;
//    }
//
//    public List<Move> possibleMoves(BoardState bs, char colour){
//        ArrayList<Move> posMoves = new ArrayList<>();
//        for (int i = 0; i < 8; i++) {
//            for (int j = (i + 1) % 2; j < 8; j += 2) {
//                if (bs.getPiece(i, j) != null) { // if there is a piece
//                    Piece piece = bs.getPiece(i, j);
//                    Point origin = new Point(i, j);
//                    if ((colour == 'b' && piece.getColour() == colour) || (Character.toUpperCase(colour) == piece.getColour()) || (colour == 'w' && (piece.getColour() == 'W'))){
//                        Point nearLeft = new Point(i + 1, j - 1);
//                        if (validate(nearLeft)) {
//                            if (bs.getPiece(i + 1, j - 1) != null && bs.getPiece(i + 1, j - 1).getColour() != colour && bs.getPiece(i + 1, j - 1).getColour() != Character.toUpperCase(colour)) {
//                                Point nearLeftJump = new Point(i + 2, j - 2);
//                                if (validate( nearLeftJump)){
//                                    if (bs.getPiece(nearLeftJump.x, nearLeftJump.y) != null){
//                                        System.out.println(MoveComments.NO_FREE_SPACE);
//                                    }
//                                    else if((bs.getPiece(nearLeftJump.x, nearLeftJump.y) == null)) {
//                                        if (piece.getColour() != 'W') {
//                                            posMoves.add(new Move(origin, nearLeftJump, true, !bs.isBTPlayer()));
//                                        }
//                                        posMoves.add(new Move(origin, nearLeftJump, true, bs.isBTPlayer()));
//                                    }
//                                }
//                            }else {
//                                if (bs.getPiece(nearLeft.x, nearLeft.y) != null){
//                                    System.out.println(MoveComments.NO_FREE_SPACE);
//                                }else if(bs.getPiece(nearLeft.x, nearLeft.y) == null) {
////                                posMoves.add(new Move(origin, nearLeft, board.isBTPlayer()));
//                                    if (piece.getColour() == 'W') {
//                                        posMoves.add(new Move(origin, nearLeft, !bs.isBTPlayer()));
//                                    }
//                                    posMoves.add(new Move(origin, nearLeft, bs.isBTPlayer()));
//                                }
//                            }
//                        }
//                        Point nearRight = new Point(i + 1, j + 1);
//                        if (validate(nearRight)) {
//                            if (bs.getPiece(i + 1, j + 1) != null && bs.getPiece(i + 1, j + 1).getColour() != colour) {
//                                Point nearRightJump = new Point(i + 2, j + 2);
//                                if (validate(nearRightJump)){
//                                    if (bs.getPiece(nearRightJump.x, nearRightJump.y) != null){
//                                        System.out.println(MoveComments.NO_FREE_SPACE);
//                                    }
//                                    else if((bs.getPiece(nearRightJump.x, nearRightJump.y) == null)) {
//                                        if (piece.getColour() == 'W') {
//                                            posMoves.add(new Move(origin, nearRightJump, true, !bs.isBTPlayer()));
//                                        }
//                                        posMoves.add(new Move(origin, nearRightJump, true, bs.isBTPlayer()));
//                                    }
//                                }
//                            }else {
//                                if (bs.getPiece(nearRight.x, nearRight.y) != null) {
//                                    System.out.println(MoveComments.NO_FREE_SPACE);
//                                }
//                                else if(bs.getPiece(nearRight.x, nearRight.y) == null){
//                                    if (piece.getColour() == 'W') {
//                                        posMoves.add(new Move(origin, nearRight, !bs.isBTPlayer()));
//                                    }
//                                    posMoves.add(new Move(origin, nearRight, bs.isBTPlayer()));
//                                }
//                            }
//                        }
//                    }
//                    if (((colour == 'w' && piece.getColour() == colour)) || (Character.toUpperCase(colour) == piece.getColour()) || (colour == 'b' && piece.getColour() == 'B')){
//                        Point nearRight = new Point(i - 1, j + 1);
//                        if (validate( nearRight)) {
//                            if (bs.getPiece(i - 1, j + 1) != null && bs.getPiece(i - 1, j + 1).getColour() != colour && bs.getPiece(i - 1, j +1).getColour() != Character.toUpperCase(colour)) {
//                                Point nearRightJump = new Point(i - 2, j + 2);
//                                if (validate( nearRightJump)){
//                                    if (bs.getPiece(nearRightJump.x, nearRightJump.y) != null){
//                                        System.out.println(MoveComments.NO_FREE_SPACE);
//                                    }
//                                    else if(bs.getPiece(nearRightJump.x, nearRightJump.y) == null) {
//                                        if (piece.getColour() == 'B') {
//                                            posMoves.add(new Move(origin, nearRightJump, true, bs.isBTPlayer()));
//                                        }
//                                        posMoves.add(new Move(origin, nearRightJump, true, !bs.isBTPlayer()));
//                                    }
//                                }
//                            }else {
//                                if (bs.getPiece(nearRight.x, nearRight.y) != null){
//                                    System.out.println(MoveComments.NO_FREE_SPACE);
//                                }
//                                else if((bs.getPiece(nearRight.x, nearRight.y) == null)) {
//                                    if (piece.getColour() == 'B') {
//                                        posMoves.add(new Move(origin, nearRight, bs.isBTPlayer()));
//                                    }
//                                    posMoves.add(new Move(origin, nearRight, !bs.isBTPlayer()));
//                                }
//                            }
//                        }
//                        Point nearLeft = new Point(i - 1, j - 1);
//                        if (validate(nearLeft)) {
//                            if (bs.getPiece(i - 1, j - 1) != null && bs.getPiece(i - 1, j - 1).getColour() != colour && bs.getPiece(i - 1, j - 1).getColour() != Character.toUpperCase(colour)){
//                                Point nearLeftJump = new Point(i - 2, j - 2);
//                                if (validate(nearLeftJump)){
//                                    if (bs.getPiece(nearLeftJump.x, nearLeftJump.y) != null){
//                                        System.out.println(MoveComments.NO_FREE_SPACE);
//                                    }
//                                    else if(bs.getPiece(nearLeftJump.x, nearLeftJump.y) == null) {
//                                        if (piece.getColour() == 'B') {
//                                            posMoves.add(new Move(origin, nearLeftJump, true, bs.isBTPlayer()));
//                                        }
//                                        posMoves.add(new Move(origin, nearLeftJump, true, !bs.isBTPlayer()));
//                                    }
//                                }
//                            }else {
//                                if (bs.getPiece(nearLeft.x, nearLeft.y) != null) {
//                                    System.out.println(MoveComments.NO_FREE_SPACE);
//                                } else if ((bs.getPiece(nearLeft.x, nearLeft.y) == null)) {
//                                    if (piece.getColour() == 'B') {
//                                        posMoves.add(new Move(origin, nearLeft, bs.isBTPlayer()));
//                                    }
//                                    posMoves.add(new Move(origin, nearLeft, !bs.isBTPlayer()));
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return posMoves;
//    }

    }
