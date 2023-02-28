package com.example.checkers.checkers.bussiness;

public enum MoveComments {
    OUT_OF_BOUNDS("This board is too small for you, isn't it?"),
    NOT_DIAGONAL ("Are you sure? Only diagonal moves, please!"),
    DOUBLE_JUMP ("Forced jump! DOUBLE TROUBLE!"),
    NO_FREE_SPACE ("That space is not free, lad."),
    NORMAL_PIECE ("Oh, silly, you are not a queen piece!");

    private final String name;

    MoveComments(String s) {
        name = s;
    }

    public String toString() {
        return this.name;
    }
}
