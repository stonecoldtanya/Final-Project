package com.example.checkers.checkers;

public class BotPlayer {
    //algorithm will be here
    private int depth;
    private Player player;
    private Difficulty difficulty;

    public BotPlayer(int depth, Player player) {
        this.depth = depth;
        this.player = player;
    }

    public BotPlayer() {
        depth = difficulty.getDepthDifficulty();
        player = Player.BOT;
    }

    public Move getNextMove(){
        return null;
    }
}
