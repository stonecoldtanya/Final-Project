package com.example.checkers;
import com.example.checkers.checkers.bussiness.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
@Lazy
public class App{
    private final Game game;

    @Autowired
    protected App(Game game) {
        this.game = game;
    }

    protected App() {
        this.game = null;
    }

    public void run() {
        if (this.game != null) {
            game.play(-1);
        }
    }
}
