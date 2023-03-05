package com.example.checkers.checkers.models.entities;

import com.example.checkers.checkers.bussiness.BotPlayer;
import com.example.checkers.checkers.bussiness.Difficulty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    private GameStatusEnum status;

    @Column(nullable = false)
    private int result;

    private Difficulty difficulty;

    @ManyToOne
    private Contestant contestant;

    @OneToMany
    private List<Move> moves;

    @OneToMany
    private List<BoardState> states;

    public Game(Contestant contestant, Difficulty difficulty) {
        this.contestant = contestant;
        this.difficulty = difficulty;
    }

    public Game(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Game() {
    }

    public Game(long id, int result, Difficulty difficulty, Contestant contestant, List<Move> moves, List<BoardState> states) {
        this.id = id;
        this.result = result;
        this.difficulty = difficulty;
        this.contestant = contestant;
        this.moves = moves;
        this.states = states;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Contestant getContestant() {
        return contestant;
    }

    public void setContestant(Contestant contestant) {
        this.contestant = contestant;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public List<BoardState> getStates() {
        return states;
    }

    public void setStates(List<BoardState> states) {
        this.states = states;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return id == game.id && result == game.result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, result);
    }
}