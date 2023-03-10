package com.example.checkers.checkers.models.dto;

import org.springframework.lang.NonNull;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class MoveDTO {
    @NonNull
    @Min(0)
    @Max(7)
    //@Max(9)
    //@Max(11)
    private Integer currentX;

    @NonNull
    @Min(0)
    @Max(7)
    //@Max(9)
    //@Max(11)
    private Integer currentY;

    @NonNull
    @Min(0)
    @Max(7)
    //@Max(9)
    //@Max(11)
    private Integer nextX;

    @NonNull
    @Min(0)
    @Max(7)
    //@Max(9)
    //@Max(11)
    private Integer nextY;

    public MoveDTO() {
    }

    @NonNull
    public Integer getCurrentX() {
        return currentX;
    }

    public void setCurrentX(@NonNull Integer currentX) {
        this.currentX = currentX;
    }

    @NonNull
    public Integer getCurrentY() {
        return currentY;
    }

    public void setCurrentY(@NonNull Integer currentY) {
        this.currentY = currentY;
    }

    @NonNull
    public Integer getNextX() {
        return nextX;
    }

    public void setNextX(@NonNull Integer nextX) {
        this.nextX = nextX;
    }

    @NonNull
    public Integer getNextY() {
        return nextY;
    }

    public void setNextY(@NonNull Integer nextY) {
        this.nextY = nextY;
    }
}
