package com.example.checkers.checkers;

/**
 * The type Bot player.
 */
public class BotPlayer {
    //algorithm will be here
    private int depth;
    private Player player;
    private Difficulty difficulty;

    /**
     * Instantiates a new Bot player.
     *
     * @param depth  the depth
     * @param player the player
     */
    public BotPlayer(int depth, Player player) {
        this.depth = depth;
        this.player = player;
    }

    /**
     * Instantiates a new Bot player.
     */
    public BotPlayer() {
        depth = difficulty.getDepthDifficulty();
        player = Player.BOT;
    }

    /**
     * Get next move move.
     *
     * @return the move
     */
    public Move getNextMove(){
        return null;
    }
}
