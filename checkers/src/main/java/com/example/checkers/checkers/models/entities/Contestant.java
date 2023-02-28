package com.example.checkers.checkers.models.entities;

import com.example.checkers.checkers.bussiness.Player;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name  = "contestants")
public class Contestant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    private boolean isWinner;

    @OneToMany
    private List<Game> games;

//    @OneToMany
//    private Tournament tournament;

    public Contestant() {
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

    public void setName(String name) {
        this.name = name;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
