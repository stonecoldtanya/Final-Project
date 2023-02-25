package com.example.checkers.checkers.bussiness;

/**
 * The enum Difficulty.
 */
//@AllArgsConstructor
//@Getter
public enum Difficulty {
    /**
     * Easy difficulty.
     */
    EASY( 3, "Easy"),
    /**
     * Normal difficulty.
     */
    PLAYABLE(5, "Playable"),

    /**
     * Hard difficulty.
     */
    HARD(9, "Hard"),
    /**
     * The Bot expert.
     */
    BOT_EXPERT(100, "Expert bot mode");

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
