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

    public Piece(char color) {
        this.color = color;
    }

    public Piece(char color, Point position){
        this.color = color;
        this.player = getPlayer();
        this.position = position;
        this.isQueen = false;
    }

    @Override
    public String toString() {
        return "[Piece color=" + color + ", position=" + position + ", isQueen="
                + isQueen + "]";
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isQueen() {
        return isQueen;
    }

    public void setQueen(boolean queen) {
        isQueen = queen;
    }

    public char getColor() {
        return color;
    }

    public void setColor(char color) {
        this.color = color;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
}
