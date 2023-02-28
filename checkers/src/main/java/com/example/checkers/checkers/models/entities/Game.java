package com.example.checkers.checkers.models.entities;
import com.example.checkers.checkers.bussiness.BotPlayer;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
//
//@Entity
//@Table(name = "games")
//public class Game {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @Column(nullable = false)
//    private int result;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "contestant_id", referencedColumnName = "id")
//    private Contestant contestant;
//
//    private BotPlayer bot;
//
//    @OneToMany
//    private BoardState boardState;
//
//    public Game() {
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public int getResult() {
//        return result;
//    }
//
//    public void setResult(int result) {
//        this.result = result;
//    }
//
//    public Contestant getContestant() {
//        return contestant;
//    }
//
//    public void setContestant(Contestant contestant) {
//        this.contestant = contestant;
//    }
//
//    public BotPlayer getBot() {
//        return bot;
//    }
//
//    public void setBot(BotPlayer bot) {
//        this.bot = bot;
//    }
//}