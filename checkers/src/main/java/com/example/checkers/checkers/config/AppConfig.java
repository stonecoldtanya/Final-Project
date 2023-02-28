package com.example.checkers.checkers.config;

import com.example.checkers.checkers.bussiness.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean("difficulty")
    public Difficulty getDifficulty() {
        return Difficulty.HARD;
    }

    @Bean("name")
    public String getPlayerName() {
        return "Tanya";
    }

//    @Bean("colour")
//    public char getColour(){
//        return 'w';
//    }

    @Bean("bot")
    public Player getBot(Difficulty difficulty, char b) {
        return new BotPlayer(difficulty, 'b');
    }

    @Bean("boarded")
    public Board getStartingState() {
        Board b = new Board(8);
        return  b;
    }

}
