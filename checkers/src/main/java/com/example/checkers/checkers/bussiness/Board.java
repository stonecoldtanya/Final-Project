package com.example.checkers.checkers.bussiness;

import org.springframework.stereotype.Component;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Class representing the gaming board.
 */
@Component
public class Board {
    private int boardLength;
    private Piece[][] board;
    private int pieceWhite;
    private int pieceBlack;
    private int queenWhite;
    private int queenBlack;
    private Move originMove;
    private Player player;
    private boolean isEvaluated = false;
    private Integer score = null;


    /**
     * Instantiates a new Board.
     */
    public Board() {
    }

    /**
     * Instantiates a new Board.
     *
     * @param boardLength the dimension of the board
     */
    public Board(int boardLength) {
        this.boardLength = boardLength;
        this.board = getInitBoard();
        this.pieceBlack = boardLength / 2 * (boardLength / 2 - 1);
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
        for (int i = 0; i < board.length; i++) {
            for (int j = (i + 1) % 2; j < board.length; j += 2) {
                Piece oldPiece = board[i][j];
                if (oldPiece != null) {
                    addPiece(oldPiece);
                    this.board[i][j] = new Piece(oldPiece.getColour());
                }
            }
            }
    }

    /**
     * Instantiates a new Board.
     *
     * @param state      the state
     * @param originMove the origin move
     */
    public Board(Piece[][] state, Move originMove) {
        this.board = deepCopy(state);
        this.originMove = originMove;
        this.pieceBlack= boardLength / 2 * (boardLength / 2 - 1);
        this.pieceWhite = boardLength / 2 * (boardLength / 2 - 1);
    }

    /**
     * Instantiates a new Board.
     *
     * @param previousState the previous state
     * @param move          the move
     */
    public Board(Board previousState, Move move) {
        this.board = deepCopy(previousState.board);
        char symbol = move.isBTPlayer() ? player.getColour() : player.getOppositeColour();
        this.update(move);
        this.originMove = move;
    }

    /**
     * Move board.
     *
     * @param move the move
     * @return the board
     */
    public Board move(Move move) {
        return new Board(this, move);
    }

    /**
     * @param row the row
     * @param col the col
     * @return the piece
     */
    public Piece getPiece(int row, int col) {
        return board[row][col];
    }

    /**
     * Deep copy of the board.
     *
     * @param original state of the board
     * @return the copy
     */
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


    /**
     * Validates a move's final destination
     *
     * @param board the board
     * @param next the final coordinate of the move
     */
    public boolean validate(Board board, Point next) {
        if (next.x > boardLength - 1 || next.x < 0 || next.y > boardLength - 1 || next.y < 0) {
            return false; // not valid if end location is outside board
        }
        return true;
    }

    /**
     * Updates the board
     *
     * @param move the move that changes the current board state
     * @return the board
     */
    public Board update(Move move){
        Piece[][] stateCopy = deepCopy(board);
        Point current = move.getCurrent();
        Point next = move.getNext();
        Piece moved = stateCopy[move.current.x][move.current.y];
        moved.setPosition(next);
        if(Math.abs(move.current.x - move.next.x) == 2 || Math.abs(move.current.y - move.next.y) == 2){
            var diffX = (move.next.x - move.current.x) / 2;
            var diffY = (move.next.y - move.current.y) / 2;
            removePiece(stateCopy[move.current.x + diffX][move.current.y + diffY]);
            stateCopy[move.current.x + diffX][move.current.y + diffY] = null;
        }
        stateCopy[move.next.x][move.next.y] = moved;
        addQueens(move, moved.getColour());
        if (move.queenPiece){
            stateCopy[move.next.x][move.next.y] = new Piece(moved.getColour(), true);
        }
        stateCopy[move.current.x][move.current.y] = null;
        addQueens(move, moved.getColour());
        return new Board(stateCopy);
    }

    /**
     * Generates all possible moves for a certain player
     *
     * @param board  the board, the current state of the game board
     * @param colour identifies the player for whom the moves will be generated
     * @return a list with all possible moves for the player with that colour pieces
     */
    public List<Move> possibleMoves(Board board, char colour){
        ArrayList<Move> posMoves = new ArrayList<>();
        for (int i = 0; i < boardLength; i++) {
            for (int j = (i + 1) % 2; j < boardLength; j += 2) {
                if (board.getPiece(i, j) != null) { // if there is a piece
                    Piece piece = board.getPiece(i, j);
                    Point origin = new Point(i, j);
                    if ((colour == 'b' && piece.getColour() == colour) || (Character.toUpperCase(colour) == piece.getColour()) || (colour == 'w' && (piece.getColour() == 'W'))){
                        Point nearLeft = new Point(i + 1, j - 1);
                        if (validate(board, nearLeft)) {
                            if (board.getPiece(i + 1, j - 1) != null && board.getPiece(i + 1, j - 1).getColour() != colour && board.getPiece(i + 1, j - 1).getColour() != Character.toUpperCase(colour)) {
                                Point nearLeftJump = new Point(i + 2, j - 2);
                                if (validate(board, nearLeftJump)){
                                    if (board.getPiece(nearLeftJump.x, nearLeftJump.y) != null){
                                        System.out.println(MoveComments.NO_FREE_SPACE);
                                    }
                                    else if((board.getPiece(nearLeftJump.x, nearLeftJump.y) == null)) {
                                        if (piece.getColour() != 'W') {
                                            posMoves.add(new Move(origin, nearLeftJump, true, !board.isBTPlayer()));
                                        }
                                        posMoves.add(new Move(origin, nearLeftJump, true, board.isBTPlayer()));
                                    }
                                }
                            }else {
                                if (board.getPiece(nearLeft.x, nearLeft.y) != null){
                                    System.out.println(MoveComments.NO_FREE_SPACE);
                                }else if(board.getPiece(nearLeft.x, nearLeft.y) == null) {
//                                posMoves.add(new Move(origin, nearLeft, board.isBTPlayer()));
                                    if (piece.getColour() == 'W') {
                                        posMoves.add(new Move(origin, nearLeft, !board.isBTPlayer()));
                                    }
                                    posMoves.add(new Move(origin, nearLeft, board.isBTPlayer()));
                                }
                            }
                        }
                        Point nearRight = new Point(i + 1, j + 1);
                        if (validate(board, nearRight)) {
                            if (board.getPiece(i + 1, j + 1) != null && board.getPiece(i + 1, j + 1).getColour() != colour) {
                                Point nearRightJump = new Point(i + 2, j + 2);
                                if (validate(board, nearRightJump)){
                                    if (board.getPiece(nearRightJump.x, nearRightJump.y) != null){
                                        System.out.println(MoveComments.NO_FREE_SPACE);
                                    }
                                    else if((board.getPiece(nearRightJump.x, nearRightJump.y) == null)) {
                                        if (piece.getColour() == 'W') {
                                            posMoves.add(new Move(origin, nearRightJump, true, !board.isBTPlayer()));
                                        }
                                        posMoves.add(new Move(origin, nearRightJump, true, board.isBTPlayer()));
                                    }
                                }
                            }else {
                                if (board.getPiece(nearRight.x, nearRight.y) != null) {
                                    System.out.println(MoveComments.NO_FREE_SPACE);
                                }
                                else if(board.getPiece(nearRight.x, nearRight.y) == null){
                                    if (piece.getColour() == 'W') {
                                        posMoves.add(new Move(origin, nearRight, !board.isBTPlayer()));
                                    }
                                    posMoves.add(new Move(origin, nearRight, board.isBTPlayer()));
                                }
                            }
                        }
                    }
                    if (((colour == 'w' && piece.getColour() == colour)) || (Character.toUpperCase(colour) == piece.getColour()) || (colour == 'b' && piece.getColour() == 'B')){
                        Point nearRight = new Point(i - 1, j + 1);
                        if (validate(board, nearRight)) {
                            if (board.getPiece(i - 1, j + 1) != null && board.getPiece(i - 1, j + 1).getColour() != colour && board.getPiece(i - 1, j +1).getColour() != Character.toUpperCase(colour)) {
                                Point nearRightJump = new Point(i - 2, j + 2);
                                if (validate(board, nearRightJump)){
                                    if (board.getPiece(nearRightJump.x, nearRightJump.y) != null){
                                        System.out.println(MoveComments.NO_FREE_SPACE);
                                    }
                                    else if(board.getPiece(nearRightJump.x, nearRightJump.y) == null) {
                                        if (piece.getColour() == 'B') {
                                            posMoves.add(new Move(origin, nearRightJump, true, board.isBTPlayer()));
                                        }
                                        posMoves.add(new Move(origin, nearRightJump, true, !board.isBTPlayer()));
                                    }
                                }
                            }else {
                                if (board.getPiece(nearRight.x, nearRight.y) != null){
                                    System.out.println(MoveComments.NO_FREE_SPACE);
                                }
                                else if((board.getPiece(nearRight.x, nearRight.y) == null)) {
                                    if (piece.getColour() == 'B') {
                                        posMoves.add(new Move(origin, nearRight, board.isBTPlayer()));
                                    }
                                    posMoves.add(new Move(origin, nearRight, !board.isBTPlayer()));
                                }
                            }
                        }
                        Point nearLeft = new Point(i - 1, j - 1);
                        if (validate(board, nearLeft)) {
                            if (board.getPiece(i - 1, j - 1) != null && board.getPiece(i - 1, j - 1).getColour() != colour && board.getPiece(i - 1, j - 1).getColour() != Character.toUpperCase(colour)){
                                Point nearLeftJump = new Point(i - 2, j - 2);
                                if (validate(board, nearLeftJump)){
                                    if (board.getPiece(nearLeftJump.x, nearLeftJump.y) != null){
                                        System.out.println(MoveComments.NO_FREE_SPACE);
                                    }
                                    else if(board.getPiece(nearLeftJump.x, nearLeftJump.y) == null) {
                                        if (piece.getColour() == 'B') {
                                            posMoves.add(new Move(origin, nearLeftJump, true, board.isBTPlayer()));
                                        }
                                        posMoves.add(new Move(origin, nearLeftJump, true, !board.isBTPlayer()));
                                    }
                                }
                            }else {
                                if (board.getPiece(nearLeft.x, nearLeft.y) != null) {
                                    System.out.println(MoveComments.NO_FREE_SPACE);
                                } else if ((board.getPiece(nearLeft.x, nearLeft.y) == null)) {
                                    if (piece.getColour() == 'B') {
                                        posMoves.add(new Move(origin, nearLeft, board.isBTPlayer()));
                                    }
                                    posMoves.add(new Move(origin, nearLeft, !board.isBTPlayer()));
                                }
                            }
                        }
                    }
                }
            }
        }
        return posMoves;
    }

    /**
     * Gets the possible jumps generated in the possibleMoves method for the current player
     *
     * @param board the current board state
     * @param colour the colour of the player's pieces
     * @return a list with all valid possible jumps
     */
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

    /**
     * Gets the regular moves generated in the possibleMoves method for the current player
     *
     * @param board the current board state
     * @param colour the colour of the player's pieces
     * @return a list with all valid possible normal moves
     */
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

    /**
     * Gets the double jump moves generated in the possibleMoves method for the current player, still work in progress
     *
     * @param board the current board state
     * @param colour the colour of the player's pieces
     * @return a list with all valid double jump moves
     */
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

    /**
     * Adds the pieces to our board
     *
     * @param added, the current piece that's being added
     */
    private void addPiece(Piece added){
        if (added.getColour() == 'w' || added.getColour() == 'W'){
            if (added.isQueen()){
                this.queenWhite++;
            }
            else {
                this.pieceWhite++;
            }
        }
        else {
            if (added.isQueen() || added.getColour() == 'B'){
                this.queenBlack++;
            }
            else{
                this.pieceBlack++;
            }
        }
    }

    /**
     * Removes a captured piece
     *
     * @param taken, the last captured piece
     */
    private void removePiece(Piece taken){
        if (taken != null){
            if (taken.getColour() == 'w' || taken.getColour() == 'W'){
                if (taken.isQueen()  || taken.getColour() == 'W'){
                    this.queenWhite--;
                }
                else{
                    this.pieceWhite--;
                }
            }
            else {
                if (taken.isQueen() || taken.getColour() == 'B'){
                    this.queenBlack--;
                }
                else{
                    this.pieceBlack--;
                }
            }
        }
    }

    /**
     * Upgrades a piece to a queen piece
     *
     * @param m is the move through which our piece gets upgraded
     * @param colour the colour of the upgraded piece
     */
    private void addQueens(Move m, char colour) {
            Piece piece = getPiece(m.getCurrent().x, m.getCurrent().y);
            if (m.next.x == 0 && colour == 'w') {
                m.setQueenPiece();
                piece.setQueen(true);
                removePiece(piece);
                addPiece(piece);
            } else if (m.next.x == 7 && colour == 'b') {
                m.setQueenPiece();
                piece.setQueen(true);
                removePiece(piece);
                addPiece(piece);
        }
    }

    /**
     * Checks if the current player is the player with black pieces.
     *
     */
    public boolean isBTPlayer() {
        if (originMove != null) {
            return !originMove.isBTPlayer();

        }
        return true; // Black pieces go first
    }

    /**
     * Method that creates the initial state of the board.
     *
     */
    public Piece[][] getInitBoard() {
        Piece[][] newBoard = new Piece[boardLength][boardLength];
        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardLength / 2 - 1; j++) {
                if (i % 2 == 1 && j % 2 ==0) {
                    newBoard[j][i] = new Piece('b');
               }
                else if(i % 2 == 0 && j % 2 ==1){
                    newBoard[j][i] = new Piece('b');
                }
                }
            for (int j = boardLength - 1; j > boardLength / 2; j--) {
                if (i % 2 == 0 && j % 2 ==1) {
                    newBoard[j][i] = new Piece('w');
                }
                else if(i % 2 == 1 && j % 2 == 0){
                    newBoard[j][i] = new Piece('w');
                }
            }
        }
        return newBoard;
    }

    /**
     * Is final boolean.
     *
     */
    public boolean isFinal() {
        return (getScore() != null);
    }

    @Override
    public String toString() {
        StringBuilder boardString = new StringBuilder();
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

    /**
     * Gets the origin move.
     *
     * @return the origin move
     */
    public Move getOriginMove() {
        return originMove;
    }

    /**
     * Evaluates the score of the game.
     */
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

    /**
     * Evaluates which players is the winner
     */
    private char getWinner() {
        if (this.pieceWhite == 0 && this.queenWhite == 0 || possibleMoves(this, 'w').size() == 0){
            return 'b';
        }else if(this.pieceBlack == 0 && this.queenBlack == 0 || possibleMoves(this, 'b').size() == 0){
            return 'w';
        }
        return '=';
    }


    /**
     * returns the number of white pieces on the board.
     *
     * @return the piece white
     */
    public int getPieceWhite() {
        return this.pieceWhite;
    }

    /**
     * returns the number of black pieces on the board.
     *
     * @return the piece black
     */
    public int getPieceBlack() {
        return pieceBlack;
    }

    /**
     * returns the dimension of the board.
     *
     * @return the board length
     */
    public int getBoardLength() {
        return boardLength;
    }

    /**.
     * @return state of the board
     */
    public Piece[][] getBoard() {
        return board;
    }

    /**
     * @return the queen white pieces
     */
    public int getQueenWhite() {
        return queenWhite;
    }

    /**
     * @return the queen black pieces
     */
    public int getQueenBlack() {
        return queenBlack;
    }

    /**
     * Gets turn.
     *
     * @return the turn
     */
    public Player getTurn() {
        return player;
    }


    /**
     * @return all pieces
     */
    public int getPieces() {
        return pieceWhite + pieceBlack + queenBlack + queenWhite;
    }
}
