package com.example.checkers.checkers;

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


    /**
     * Instantiates a new Board.
     *
     * @param boardLength the dimension of the board
     */
    public Board(int boardLength) {
        this.boardLength = boardLength;
        this.board = getInitBoard();
        this.pieceBlack= boardLength / 2 * (boardLength / 2 - 1);
        this.pieceWhite = boardLength / 2 * (boardLength / 2 - 2);
        this.queenBlack = 0;
        this.queenWhite = 0;
    }

    /**
     * Instantiates a new Board.
     *
     * @param board the board
     */
    public Board(Piece[][] board) {
        this.pieceWhite = 0;
        this.pieceBlack = 0;
        this.board = new Piece[boardLength][boardLength];

        for (int i = 0; i < boardLength; i++) {
            for (int j = (i + 1) % 2; j < boardLength; j += 2) {
                Piece oldPiece = board[i][j];
                if (oldPiece != null) {
                    addQueens(oldPiece);
                    this.board[i][j] = new Piece(oldPiece.getColor());
                }
            }
        }
    }
    public Piece getPiece(int row, int col) {
        return board[row][col];
    }

    public Piece[][] copy(Piece[][] board) {
        Piece[][] result = new Piece[boardLength][boardLength];
        for (int i = 0; i < boardLength; i++) {
            for (int j = (i + 1) % 2; j < boardLength; j += 2) {
                Piece oldPiece = board[i][j];
                if (oldPiece != null) {
                    result[i][j] = new Piece(oldPiece.getColor());
                }
            }
        }
        return result;
    }

    public Board update(Move move){
        Piece[][] current = copy(board);

        return new Board(current);
    }

    private void addQueens(Piece upgraded){
        if (upgraded.getColor() == 'w'){
            this.queenWhite++;
        }
        else {
            this.queenBlack++;
        }
    }


    private void removePiece(Piece taken){
        if (taken != null){
            if (taken.getColor() == 'w'){
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
                        boardString.append(Character.toUpperCase(piece.getColor()));
                    } else {
                        boardString.append(piece.getColor());
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
}
