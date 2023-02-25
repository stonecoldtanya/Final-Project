package com.example.checkers.checkers.bussiness;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Class representing the gaming board.
 */
public class Board {
    private int boardLength;
    private Piece[][] board;
    private Move start;
    private int pieceWhite;
    private int pieceBlack;
    private int queenWhite;
    private int queenBlack;
    private Move originMove;
    private Player player;

    private boolean isEvaluated = false;
    private Integer score = null;
    private List<Board> children = null;


    /**
     * Instantiates a new Board.
     *
     * @param boardLength the dimension of the board
     */
    public Board(int boardLength) {
        this.boardLength = boardLength;
        this.board = getInitBoard();
        this.pieceBlack= boardLength / 2 * (boardLength / 2 - 1);
        this.pieceWhite = boardLength / 2 * (boardLength / 2 - 1);
        this.queenBlack = 0;
        this.queenWhite = 0;
    }

    /**
     * Instantiates a new Board.
     *
     * @param board the board
     */
    public Board(Piece[][] board) {
        this.boardLength = board.length;
        this.board = new Piece[board.length][board.length];
        this.pieceBlack= 0;
        this.pieceWhite = 0;
        this.queenBlack = 0;
        this.queenWhite = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = (i + 1) % 2; j < board.length; j += 2) {
                Piece oldPiece = board[i][j];
                if (oldPiece != null) {
                    addQueens(oldPiece);
                    this.board[i][j] = new Piece(oldPiece.getColour());
                }
            }
            }
    }

    public Board(Board previousState, Move move) {
        this.board = deepCopy(previousState.board);

    }

    public Piece getPiece(int row, int col) {
        return board[row][col];
    }


    public Piece[][] deepCopy(Piece[][] original) {
        Piece[][] result = new Piece[boardLength][boardLength];
        for (int i = 0; i < boardLength; i++) {
            for (int j = (i+1)%2; j < boardLength; j+=2) {
                Piece oldPiece = original[i][j];
                if (oldPiece != null) {
                    result[i][j] = new Piece(oldPiece.getColour());
                }
            }
        }
        return result;
    }


    public boolean validate(Board board, Point next) {
        if (next.x > boardLength - 1 || next.x < 0 || next.y > boardLength - 1 || next.y < 0) {
            return false; // not valid if end location is outside board
        }
        return true;
    }
    public Board update(Move move){
        Piece[][] stateCopy = deepCopy(board);
        Point current = move.getCurrent();
        Point next = move.getNext();
        Piece moved = stateCopy[move.current.x][move.current.y];
        if(Math.abs(move.current.x - move.next.x) == 2 || Math.abs(move.current.y - move.next.y) == 2){
            var diffX = (move.next.x - move.current.x) / 2;
            var diffY = (move.next.y - move.current.y) / 2;
            removePiece(stateCopy[move.current.x + diffX][move.current.y + diffY]);
            stateCopy[move.current.x + diffX][move.current.y + diffY] = null;
        }
        stateCopy[move.next.x][move.next.y] = moved;
        stateCopy[move.current.x][move.current.y] = null;

        return new Board(stateCopy);
    }

    private void upgradedPieces(List<Move> moves, char colour) {
        for (Move m : moves) {
            Piece piece = getPiece(m.getCurrent().x, m.getCurrent().y);
            if (m.next.x == 0 && colour== 'b') {
                m.setQueenPiece();
                piece.setQueen(true);
                addQueens(piece);
            } else if (m.next.x == 7 && piece.getColour() == 'w') {
                m.setQueenPiece();
                piece.setQueen(true);
                addQueens(piece);
            }
        }
    }
    public List<Move> prioritiseJumpMoves(Board board, char colour){
        List<Move> jumpMoves = new ArrayList<>(getJumps(board, colour));
        List<Move> regularMoves = new ArrayList<>(getNonJumpMoves(board, colour));
        if (jumpMoves.isEmpty()){
            upgradedPieces(regularMoves, colour);
            return regularMoves;
        }
        upgradedPieces(jumpMoves, colour);
        return jumpMoves;

    }
    protected List<Move> possibleMoves(Board board, char colour){
        ArrayList<Move> posMoves = new ArrayList<>();
        for (int i = 0; i < boardLength; i++) {
            for (int j = (i + 1) % 2; j < boardLength; j += 2) {
                if (board.getPiece(i, j) != null) { // if there is a piece
                    Piece piece = board.getPiece(i, j);
                    Point origin = new Point(i, j);
                    if ((colour == 'b' && piece.getColour() == colour) || (piece.isQueen() && piece.getColour() == colour)){
                        Point nearLeft = new Point(i + 1, j - 1);
                        if (validate(board, nearLeft)) {
                            if (board.getPiece(i + 1, j - 1) != null && board.getPiece(i + 1, j - 1).getColour() != colour) {
                                Point nearLeftJump = new Point(i + 2, j - 2);
                                if (validate(board, nearLeftJump)){
                                    posMoves.add(new Move(origin, nearLeftJump, true));
                                }
                            }else {
                                posMoves.add(new Move(origin, nearLeft));
                            }
                        }
                        Point nearRight = new Point(i + 1, j + 1);
                        if (validate(board, nearRight)) {
                            if (board.getPiece(i + 1, j + 1) != null && board.getPiece(i + 1, j + 1).getColour() != colour) {
                                Point nearLeftJump = new Point(i + 2, j + 2);
                                if (validate(board, nearLeftJump)){
                                    posMoves.add(new Move(origin, nearLeftJump, true));
                                }
                            }else {
                                posMoves.add(new Move(origin, nearLeft));
                            }
                        }
                    }
                    if ((colour == 'w' && piece.getColour() == colour) || (piece.isQueen() && piece.getColour() == colour)){
                        Point nearLeft = new Point(i - 1, j + 1);
                        if (validate(board, nearLeft)) {
                            if (board.getPiece(i - 1, j + 1) != null && board.getPiece(i - 1, j + 1).getColour() != colour) {
                                Point nearLeftJump = new Point(i - 2, j + 2);
                                if (validate(board, nearLeftJump)){
                                    posMoves.add(new Move(origin, nearLeftJump, true));
                                }
                            }else {
                                posMoves.add(new Move(origin, nearLeft));
                            }
                        }
                        Point nearRight = new Point(i - 1, j - 1);
                        if (validate(board, nearRight)) {
                            if (board.getPiece(i - 1, j - 1) != null && board.getPiece(i - 1, j - 1).getColour() != colour) {
                                Point nearLeftJump = new Point(i - 2, j - 2);
                                if (validate(board, nearLeftJump)){
                                    posMoves.add(new Move(origin, nearLeftJump, true));
                                }
                            }else {
                                posMoves.add(new Move(origin, nearLeft));
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
    public List<Move> getJumps(Board board, char colour){
        List<Move> moves = possibleMoves(board, colour);
        List<Move> jumpMoves = new ArrayList<>();
        for (Move move : moves){
            if (move.isJumpMove() == true){
                jumpMoves.add(move);
            }
        }
        return jumpMoves;
    }

    public List<Move> getNonJumpMoves(Board board, char colour){
        List<Move> moves = possibleMoves(board, colour);
        List<Move> regular = new ArrayList<>();
        for (Move move : moves){
            if (move.isJumpMove() != true){
                regular.add(move);
            }
        }
        return regular;
    }
    public List<Move> doubleJump(Board board, char colour, Point next){
//        List<Move> moves = possibleMoves(board, colour);
        List<Move> jumpMoves = new ArrayList<>(getJumps(board, colour));
//        for (Move move : moves){
//            if (move.isJumpMove() == true){
//                jumpMoves.add(move);
//            }
//        }
        if (jumpMoves.size() > 0){
            jumpMoves.removeIf(e -> (e.getCurrent().x != next.x || e.getCurrent().y != next.y));
            if (jumpMoves.isEmpty()){
                return jumpMoves;
            }
        }
        return jumpMoves;
    }

    private void addQueens(Piece upgraded){
        if (upgraded.getColour() == 'w'){
            this.queenWhite++;
        }
        else {
            this.queenBlack++;
        }
    }

    private void removePiece(Piece taken){
        if (taken != null){
            if (taken.getColour() == 'w'){
                if (taken.isQueen()){
                    this.queenWhite--;
                }
                else{
                    this.pieceWhite++;
                }
            }
            else {
                if (taken.isQueen()){
                    this.queenBlack--;
                }
                else{
                    this.pieceBlack++;
                }
            }
        }
    }

    public boolean isBTPlayer() {
        if (originMove != null) {
            return !originMove.isBTPlayer();

        }
        return true; // Black pieces go first
    }

    /**
     * Method that creates the initial state of the board.
     */
    private Piece[][] getInitBoard() {
        Piece[][] newBoard = new Piece[boardLength][boardLength];
        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength / 2 - 1; j++) {
                if (i % 2 == 1 && j % 2 ==0) {
                    newBoard[j][i] = new Piece('w');
               }
                else if(i % 2 == 0 && j % 2 ==1){
                    newBoard[j][i] = new Piece('w');
                }
                }
            for (int j = boardLength - 1; j > boardLength / 2; j--) {
                if (i % 2 == 0 && j % 2 ==1) {
                    newBoard[j][i] = new Piece('b');
                }
                else if(i % 2 == 1 && j % 2 == 0){
                    newBoard[j][i] = new Piece('b');
                }
            }
        }
        return newBoard;
    }
    public boolean isFinal() {
        return (getScore() != null);
    }

    /**
     * returns the number of white pieces on the board.
     */
    public int getPieceWhite() {
        return pieceWhite;
    }

    /**
     * returns the number of black pieces on the board.
     */
    public int getPieceBlack() {
        return pieceBlack;
    }

    /**
     * returns the dimension of the board.
     */
    public int getBoardLength() {
        return boardLength;
    }

    public Piece[][] getBoard() {
        return board;
    }

    public int getQueenWhite() {
        return queenWhite;
    }

    public int getQueenBlack() {
        return queenBlack;
    }

    @Override
    public String toString() {
        StringBuilder boardString = new StringBuilder();
        boardString.append("  Let's start the game\n");
        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength; j++) {
                if (j == 0) {
                    if(i < 9){
                        boardString.append(0).append(i + 1).append("  ");
                    }else {
                        boardString.append(i + 1).append("  ");
                    }
                }
                Piece piece = board[i][j];
                if (piece == null) {
                    boardString.append('-');
                } else {
                    if (piece.isQueen()) {
                        boardString.append(Character.toUpperCase(piece.getColour()));
                    } else {
                        boardString.append(piece.getColour());
                    }
                }
                boardString.append(" ");
            }
            if(i < 9){
                boardString.append(" " + 0).append(i + 1).append("\n");
            }else {
                boardString.append(" ").append(i + 1).append("\n");
            }
        }
        boardString.append(" \n");
        return boardString.toString();
    }

    public Board move(Move move) {
        return new Board(this, move);
    }

    public Move getOriginMove() {
        return originMove;
    }

    public Integer getScore() {
        if (!isEvaluated) {
            char winColour = getWinner();
            if (winColour == '=') {
                this.score = 0;
            }
            else if(winColour == 'b'){
                this.score = 1;
            }
            else if(winColour == 'w'){
                this.score = -1;
            }
            else if (possibleMoves(this,'w').isEmpty() && (possibleMoves(this,'b').isEmpty())){
                this.score = 0;
            } else {
                this.score = null;
            }
            this.isEvaluated = true;
        }
        return this.score;
    }

    private char getWinner() {
        if (this.pieceWhite == 0 || possibleMoves(this, 'w').size() == 0){
            return 'b';
        }else if(this.pieceBlack == 0 || possibleMoves(this, 'b').size() == 0){
            return 'w';
        }
        return '=';
    }

//    public int winCheck() {
//        if (this.pieceWhite == 0 || possibleMoves(this, 'w').size() == 0){
//            return AI.NEGATIVE_INFINITY;
//        }else if(this.pieceBlack == 0 || possibleMoves(this, 'b').size() == 0){
//            return AI.NEGATIVE_INFINITY;
//        }
//        return 0;
//    }


    public Player getTurn() {
        return player;
    }

    public int getPieces() {
        return pieceWhite + pieceBlack;
    }
}
