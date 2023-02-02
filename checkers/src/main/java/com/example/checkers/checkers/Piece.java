package com.example.checkers.checkers;

import java.awt.*;

public class Piece {
    private Player player;
    private boolean isQueen;
    private char color;
    private Point position = new Point(0, 0);

    public Piece(Player player){
        this.player = player;
        this.isQueen = false;
    }

    public Piece(boolean isQueen, char color) {
        this.isQueen = isQueen;
        this.color = color;
    }

    public Piece(char color) {
        this.color = color;
        this.player = getPlayer();
        this.isQueen = false;
    }

    public Player getPlayer() {
        return player;
    }

    boolean isQueen() {
        return isQueen;
    }

    void setQueen() {
        isQueen = true;
    }

    public char getColor() {
        return color;
    }

    public void setColor(char color) {
        this.color = color;
    }
}
