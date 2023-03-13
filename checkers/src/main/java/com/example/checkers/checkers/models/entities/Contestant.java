package com.example.checkers.checkers.models.entities;

import com.example.checkers.checkers.bussiness.Board;
import com.example.checkers.checkers.bussiness.Move;
import com.example.checkers.checkers.bussiness.Player;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name  = "contestants")
public class Contestant implements Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Basic(fetch = FetchType.LAZY)
//    @Element(name = "pass", data = true)
    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @JsonProperty("color")
    private char color;


//    @OneToMany(mappedBy = "contestant")
//    private Set<Game> games = new HashSet<>();

    public Contestant() {
    }

    public Contestant(long id, String name, char color) {
        this.id = id;
        this.username = name;
        this.color = color;
    }

    public Contestant(String name, char color) {
        this.username = name;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Move getNextMove(Board board) {
        return null;
    }

    @Override
    public char getColor() {
        return this.color;
    }

    @Override
    public char getOppositeColour() {
        if (getColor() == 'b'){
            return 'w';
        }
        else {
            return 'b';
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contestant that = (Contestant) o;
        return  username.equals(that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
