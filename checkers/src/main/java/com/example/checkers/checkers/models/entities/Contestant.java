package com.example.checkers.checkers.models.entities;

import com.example.checkers.checkers.bussiness.Board;
import com.example.checkers.checkers.bussiness.Player;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name  = "contestants")
public class Contestant implements Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private char color;

//    @OneToMany(mappedBy = "contestant")
//    private Set<Game> games = new HashSet<>();

    public Contestant() {
    }

    public Contestant(long id, String name, char color) {
        this.id = id;
        this.name = name;
        this.color = color;
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
        return this.color;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contestant that = (Contestant) o;
        return  name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
