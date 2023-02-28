package com.example.checkers.checkers.bussiness;

import java.awt.*;
import java.util.Locale;

public class Piece {
    private boolean isQueen;
    private String value = null;
    private char colour;
    private Point position = new Point(0, 0);

    public Piece(char colour) {
        this.colour = colour;
        this.isQueen = false;
        setValue(String.valueOf(getColour()));
        this.value = getValue();
        this.position = null;
    }

    public Piece(char colour, Point position){
        this.colour = colour;
        this.position = position;
        this.isQueen = false;
        setValue(String.valueOf(getColour()));
        this.value = getValue();
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

    public void setValue(String value) {
        if (isQueen){
            this.value = String.valueOf(getColour() + 'q').toUpperCase(Locale.ROOT);
        }
        this.value = String.valueOf(getColour()).toUpperCase(Locale.ROOT);
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "[Piece color=" + colour + ", position=" + position + ", isQueen="
                + isQueen + "]";
    }
}
