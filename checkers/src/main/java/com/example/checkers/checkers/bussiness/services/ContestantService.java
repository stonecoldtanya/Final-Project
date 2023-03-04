package com.example.checkers.checkers.bussiness.services;

import com.example.checkers.checkers.bussiness.BotPlayer;
import com.example.checkers.checkers.bussiness.Difficulty;
import com.example.checkers.checkers.bussiness.Player;
import com.example.checkers.checkers.exceptions.PlayerNotFoundException;
import com.example.checkers.checkers.models.entities.Contestant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContestantService {
    List<Player> players;
    private Player player1 = new Contestant("Lea", 'b');
    private Player player2 = new BotPlayer();

    @Autowired
    @Qualifier("consoleGamer")
    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    @Autowired
    @Qualifier("bot")
    public void setPlayer2(BotPlayer player2) {
        this.player2 = player2;
    }

    public ContestantService() {
        players = new ArrayList<>(2);
        players.add(player1);
        players.add(player2);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getPlayer(String name) {
        List<Player> collect = players.stream()
                .filter(e -> e.getName().equals(name))
                .collect(Collectors.toList());
        if (collect.size() > 0) {
            return collect.get(0);
        }
        throw new PlayerNotFoundException("Player" + getPlayer(name) + "not found");
    }
}
