package com.example.checkers.checkers.models.entities;


import javax.persistence.*;
import java.awt.*;
import java.util.Objects;

@Entity
@Table(name = "moves")
public class Move {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public boolean isBTPlayer;
    public Point current;
    public Point next;

    public boolean queenPiece;
    private boolean jumpMove;

    public Move(Point current, Point next, boolean isBTPlayer) {
        this.isBTPlayer = isBTPlayer;
        this.current = current;
        this.next = next;
    }


    public Move(Point current, Point next) {
        this.current = current;
        this.next = next;
        this.jumpMove = false;
//        if(current.getX() + 1 == next.getX() || current.getX() - 1 == next.getX()){
//            this.jumpMove = false;
//        }
//        else if(current.getX() + 2 == next.getX() || current.getX() - 2 == next.getX()){
//            this.jumpMove = true;
//        }
    }

    public Move(Point current, Point next, boolean jumpMove, boolean isBTPlayer) {
        this.current = current;
        this.next = next;
        this.jumpMove = jumpMove;
        this.isBTPlayer = isBTPlayer;
    }

    public Point getCurrent() {
        return current;
    }

    public Point getNext() {
        return next;
    }

    public void setQueenPiece() {
        this.queenPiece = true;
    }

    public boolean isJumpMove() {
        return jumpMove;
    }

    public void setJumpMove(boolean jumpMove) {
        this.jumpMove = jumpMove;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Move)) {
            return false;
        }
        Move checkMove = (Move) obj;
        if (this.current.getX() == checkMove.current.getX() && this.current.getY() == checkMove.current.getY()
                && this.next.getY() == checkMove.next.getY() && this.next.getX() == checkMove.next.getX()) {
            return true;
        }
        return false;
    }

    public boolean isBTPlayer() {
        return isBTPlayer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getCurrent(), this.getNext());
    }

    @Override
    public String toString() {
        return "Move{ " +
                "[" + current.x + ", " + current.y +
                "], [" + next.x + ", " + next.y +
                "] }";
    }

    public boolean isValid() {
        return true;
    }
}


