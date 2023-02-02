package com.example.checkers.checkers;

public enum Player {
    BOT, GAMER;

    public Player getOpposite() {
        Player result = null;
        if (this == BOT) {
            result = GAMER;
        }
        else if (this == GAMER) {
            result = BOT;
        }
        if (result == null){
            throw new RuntimeException("The sad little bot has no one to play with");
        }
        return result;
    }
}
