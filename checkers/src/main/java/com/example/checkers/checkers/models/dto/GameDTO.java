package com.example.checkers.checkers.models.dto;

import com.example.checkers.checkers.bussiness.Difficulty;
import com.example.checkers.checkers.models.entities.BoardState;
import com.example.checkers.checkers.models.entities.Contestant;
import com.example.checkers.checkers.models.entities.Game;
import org.springframework.lang.NonNull;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class GameDTO {
    private Long id;

    @NonNull
    private Contestant contestant;

    @NotEmpty
    private Difficulty difficulty;

    private List<BoardState> states;

    public GameDTO(long id, List<BoardState> states) {
    }

    public static GameDTO fromEntity(Game entity) {
        return new GameDTO(entity.getId(), entity.getStates());
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


}
