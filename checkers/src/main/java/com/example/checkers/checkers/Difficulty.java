package com.example.checkers.checkers;

//@AllArgsConstructor
//@Getter
public enum Difficulty {
    EASY( 1, "Easy"),
    PLAYABLE(2, "Playable"),
    MEDIUM(3, "Medium"),
    HARD(4, "Hard"),
    BOT_EXPERT(5, "Expert bot mode");

    private final int depthDifficulty;
    private final String stringValue;

    Difficulty(int depthDifficulty, String stringValue) {
        this.depthDifficulty = depthDifficulty;
        this.stringValue = stringValue;
    }

    public int getDepthDifficulty() {
        return this.depthDifficulty;
    }

    @Override
    public String toString() {
        return this.stringValue;
    }
}
