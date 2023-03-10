package com.example.checkers.checkers.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "moves")
public class MoveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int currentX;

    private int currentY;

    private int nextX;

    private int nextY;

    public MoveRequest() {
    }

    public MoveRequest(int currentX, int currentY, int nextX, int nextY) {
        this.currentX = currentX;
        this.currentY = currentY;
        this.nextX = nextX;
        this.nextY = nextY;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCurrentX() {
        return currentX;
    }

    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }

    public int getNextX() {
        return nextX;
    }

    public void setNextX(int nextX) {
        this.nextX = nextX;
    }

    public int getNextY() {
        return nextY;
    }

    public void setNextY(int nextY) {
        this.nextY = nextY;
    }
}
