package com.example.checkers.checkers.bussiness;

import java.awt.*;
import java.util.Locale;
import java.io.Serializable;

public class Piece implements Serializable{
    private boolean isQueen;
    private char colour;
    private Point position = new Point(0, 0);

    public Piece() {
    }

    public Piece(char colour) {
        this.colour = colour;
        this.isQueen = false;
    }

    public Piece(char colour, Point position){
        this.colour = colour;
        this.position = position;
        this.isQueen = false;
    }

    public Piece(char colour, boolean isQueen){
        this.colour = Character.toUpperCase(colour);
        this.isQueen = isQueen;
    }

    public boolean isQueen() {
        return isQueen;
    }

    public void setQueen(boolean queen) {
        isQueen = queen;
    }

    public char getColour() {
        return colour;
    }

    public void setColour(char colour) {
        this.colour = colour;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }


    @Override
    public String toString() {
        return "[Piece color=" + colour + ", position=" + position + ", isQueen="
                + isQueen + "]";
    }
}
