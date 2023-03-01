package com.example.checkers.checkers.models.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private int result;

    @ManyToOne
    private Contestant contestant;
//
//    @Column(nullable = false)
//    private Player bot;

    @OneToMany
    private Set<BoardState> states;

    public Game(int result, Contestant contestant) {
        this.result = result;
        this.contestant = contestant;
    }

    public Game() {
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

    public Contestant getContestant() {
        return contestant;
    }

    public void setContestant(Contestant contestant) {
        this.contestant = contestant;
    }

    public Set<BoardState> getStates() {
        return states;
    }

    public void setStates(Set<BoardState> states) {
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