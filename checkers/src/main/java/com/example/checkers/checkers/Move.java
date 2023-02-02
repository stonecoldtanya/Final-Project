package com.example.checkers.checkers;

import java.awt.*;
import java.util.ArrayList;

public class Move {
    final Point current;
    final Point next;
    private final ArrayList<Point> remove;
    private final ArrayList<Point> moves;
    public boolean isQueen;

    public Move(Point current, Point next) {
        this.current = current;
        this.next = next;
        this.remove = null;
        this.moves = null;
        this.isQueen = false;
    }

    public Move(Point current, Point next, Point taken) {
        this.current = current;
        this.next = next;
        this.remove = new ArrayList<>();
        this.remove.add(taken);
        this.moves = null;

    }
}
