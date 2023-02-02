package com.example.checkers.checkers;

/**
 * The enum Difficulty.
 */
//@AllArgsConstructor
//@Getter
public enum Difficulty {
    /**
     * Easy difficulty.
     */
    EASY( 1, "Easy"),
    /**
     * Playable difficulty.
     */
    PLAYABLE(2, "Playable"),
    /**
     * Medium difficulty.
     */
    MEDIUM(3, "Medium"),
    /**
     * Hard difficulty.
     */
    HARD(4, "Hard"),
    /**
     * The Bot expert.
     */
    BOT_EXPERT(5, "Expert bot mode");

    private final int depthDifficulty;
    private final String stringValue;

    Difficulty(int depthDifficulty, String stringValue) {
        this.depthDifficulty = depthDifficulty;
        this.stringValue = stringValue;
    }

    /**
     * Gets depth difficulty.
     *
     * @return the depth difficulty
     */
    public int getDepthDifficulty() {
        return this.depthDifficulty;
    }

    @Override
    public String toString() {
        return this.stringValue;
    }
}
