package com.example.checkers.checkers.models.dto;

import com.example.checkers.checkers.models.entities.Contestant;
import com.example.checkers.checkers.models.entities.Game;

import javax.validation.constraints.NotNull;

public class ContestantDTO {
    @NotNull
    private Long id;

    @NotNull
    private String name;

    public ContestantDTO() {
    }

    public ContestantDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ContestantDTO fromEntity(Contestant entity) {
        return new ContestantDTO(entity.getId(), entity.getName());
    }

    public static Contestant updateEntity(Contestant contestant, ContestantDTO dto) {
        contestant.setName(dto.getName());

        return contestant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
