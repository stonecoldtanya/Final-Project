package com.example.checkers.checkers.bussiness.services;

import com.example.checkers.checkers.bussiness.repositories.BoardStateRepository;
import com.example.checkers.checkers.models.entities.*;
import com.example.checkers.checkers.models.dto.GameDTO;
import com.example.checkers.checkers.bussiness.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameService {
    private final BoardService boardService;
    private final GameRepository gameRepository;
    private final BoardStateRepository bsRepo;

    @Autowired
    public GameService(BoardService boardService, GameRepository gameRepository, BoardStateRepository bsRepo) {
        this.boardService = boardService;
        this.gameRepository = gameRepository;
        this.bsRepo = bsRepo;
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
//        if (result.getCurrent().getCurrentState() == null){
//            result.setCurrent(boardService.init(8));
//        }
        return GameDTO.fromEntity(result);
    }

    public List<GameDTO> getAllGames() {
        return gameRepository.findAll().stream().map(GameDTO::fromEntity).collect(Collectors.toList());
    }

    public Optional<GameDTO> findGameById(Long id) {
        Optional<Game> game = gameRepository.findById(id);
        return game.map(GameDTO::fromEntity);
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
