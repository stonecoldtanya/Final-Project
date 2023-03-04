package com.example.checkers.checkers.models.entities;

import com.example.checkers.checkers.bussiness.Board;
import com.example.checkers.checkers.bussiness.Player;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name  = "contestants")
public class Contestant implements Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    private char color;


    @OneToMany
    private Set<Game> games;

    public Contestant() {
    }

    public Contestant(String name, char color) {
        this.name = name;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public Move getNextMove(Board board) {
        return null;
    }

    @Override
    public char getColour() {
        return color;
    }

    @Override
    public char getOppositeColour() {
        if (getColour() == 'b'){
            return 'w';
        }
        else {
            return 'b';
        }

    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contestant that = (Contestant) o;
        return id == that.id && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
