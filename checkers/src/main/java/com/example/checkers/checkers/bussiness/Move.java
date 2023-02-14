package com.example.checkers.checkers;

import org.springframework.data.annotation.Immutable;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

@Immutable
public class Move {
    private Board board;
    private boolean isBTPlayer ;
    final Point current;
    final Point next;
    private boolean jumpMove;

    public Move(Point current, Point next) {
        this.current = current;
        this.next = next;

        if(current.getX() + 1 == next.getX() || current.getX() - 1 == next.getX()){
            this.jumpMove = false;
        }
        else if(current.getX() + 2 == next.getX() || current.getX() - 2 == next.getX()){
            this.jumpMove = true;
        }
    }

    public Point getCurrent() {
        return current;
    }

    public Point getNext() {
        return next;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(!(obj instanceof  Move)){
            return false;
        }
        Move checkMove = (Move) obj;
        if(this.current.getX() == checkMove.current.getX() && this.current.getY() == checkMove.current.getY()
                && this.next.getY() == checkMove.next.getY()  && this.next.getX() == checkMove.next.getX()){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getCurrent(), this.getNext());
    }

    public String toString() {
        String format = String.format("%s moves to [%d, %d]", isBTPlayer ? "Black Tile Player" : "White Tile Player", next.getX(), next.getY());
        return format;
    }
}


