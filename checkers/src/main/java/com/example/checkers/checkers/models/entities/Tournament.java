package com.example.checkers.checkers.models.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tournaments")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "tournament_name")
    private String name;

    @OneToMany
    private Set<Game> games;

    @ManyToMany
    private Set<Contestant> contestant;

    //private List<Score> scoreBoard;

    public Tournament() {
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

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public Set<Contestant> getContestant() {
        return contestant;
    }

    public void setContestant(Set<Contestant> contestant) {
        this.contestant = contestant;
    }
}
