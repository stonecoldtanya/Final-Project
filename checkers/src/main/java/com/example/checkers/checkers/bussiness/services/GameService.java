package com.example.checkers.checkers.bussiness.services;

import com.example.checkers.checkers.bussiness.*;
import com.example.checkers.checkers.bussiness.repositories.BoardStateRepository;
import com.example.checkers.checkers.exceptions.IllegalMoveException;
import com.example.checkers.checkers.models.entities.*;
import com.example.checkers.checkers.models.dto.GameDTO;
import com.example.checkers.checkers.bussiness.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GameService {
    public Board board;
    GameRepository gameRepository;
    BoardStateRepository bsRepo;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.board = new Board(8);
        this.gameRepository = gameRepository;
    }

    public Game createGame(Game game) {
        Optional<Game> existingGame = gameRepository.findOne(Example.of(game));
        if (existingGame.isPresent()) {
            return existingGame.get();
        }

        return gameRepository.save(game);
    }

    public GameDTO createGame(GameDTO game){
        Game newGame = GameDTO.updateEntity(new Game(), game);
        Game result = createGame(newGame);
        return GameDTO.fromEntity(result);
}

    public void updateState(Move move, Player player) {
        if (board.getPiece((int) move.next.getX(), (int)  move.next.getY()) != null) {
            throw new IllegalMoveException(MoveComments.NO_FREE_SPACE);
        }
        board.update(move);
    }

    public List<GameDTO> getAllGames() {
        return gameRepository.findAll().stream().map(GameDTO::fromEntity).collect(Collectors.toList());
    }

    public Optional<GameDTO> findGameById(Long id) {
        Optional<Game> game = gameRepository.findById(id);
        return game.map(GameDTO::fromEntity);
    }

    public Board intBoard(int length) {
        Board b = new Board(length);
        return b;
    }

//    public Game connectToGame(Long gameId) {
//        Optional<Game> optionalGame = gameRepository.findById(gameId);
//
//        Game game = optionalGame.get();
//
//        if (game.getSecondPlayer() != null) {
//            throw new NullPointerException("u might need a bot!");
//        }
//
//        game.setSecondPlayer(new BotPlayer(game.getDifficulty()));
//        gameRepository.save(game);
//        return game;
//    }
}
