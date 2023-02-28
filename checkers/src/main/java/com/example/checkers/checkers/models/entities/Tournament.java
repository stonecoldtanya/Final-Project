package com.example.checkers.checkers.models.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tournaments")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "tournament_name")
    private String name;

    @OneToMany
    private List<Game> games;

    @ManyToMany
    private List<Contestant> contestant;

    //private List<Score> scoreBoard;

    public Tournament() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public List<Contestant> getContestant() {
        return contestant;
    }

    public void setContestant(List<Contestant> contestant) {
        this.contestant = contestant;
    }
}
