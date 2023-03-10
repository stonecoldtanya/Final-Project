package com.example.checkers.checkers.models.dto;

import com.example.checkers.checkers.bussiness.Difficulty;
import com.example.checkers.checkers.models.entities.BoardState;
import com.example.checkers.checkers.models.entities.Contestant;
import com.example.checkers.checkers.models.entities.Game;
import org.springframework.lang.NonNull;

public class GameDTO {
    private Long id;

    @NonNull
    private Contestant contestant;

    @NonNull
    private Difficulty difficulty;

    private BoardState current;

    public GameDTO() {
    }

    public GameDTO(Long id, Contestant contestant, Difficulty difficulty, BoardState current) {
        this.id = id;
        this.contestant = contestant;
        this.difficulty = difficulty;
        this.current = current;
    }

    public static GameDTO fromEntity(Game entity) {
        return new GameDTO(entity.getId(), entity.getContestant(), entity.getDifficulty(), entity.getCurrent());
    }

    public static Game updateEntity(Game game, GameDTO dto) {
        game.setContestant(dto.getContestant());
        game.setDifficulty(dto.getDifficulty());
        game.setCurrent(dto.getCurrent());
        return game;
    }


    public BoardState getCurrent() {
        return current;
    }

    public void setCurrent(BoardState current) {
        this.current = current;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contestant getContestant() {
        return contestant;
    }

    public void setContestant(Contestant contestant) {
        this.contestant = contestant;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

//    @Override
//    public String toString() {
//        return "GameDTO{" +
//                "id=" + id +
//                ", contestant=" + contestant +
//                ", difficulty=" + difficulty +
//                '}';
//    }
}
