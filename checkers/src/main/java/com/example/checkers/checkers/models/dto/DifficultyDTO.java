package com.example.checkers.checkers.models.dto;

import com.example.checkers.checkers.bussiness.Difficulty;
import javax.validation.constraints.NotEmpty;

public class DifficultyDTO {
    private String difficulty;  //game difficulty

    @NotEmpty
    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

}
