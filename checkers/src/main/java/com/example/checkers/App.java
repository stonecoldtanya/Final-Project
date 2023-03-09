package com.example.checkers;
import com.example.checkers.checkers.bussiness.GamePlay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
@Lazy
public class App{
    private final GamePlay game;

    @Autowired
    protected App(GamePlay game) {
        this.game = game;
    }

    public App() {
        this.game = null;
    }

    public void run() {
        if (this.game != null) {
            game.play(-1);
        }
    }
}
