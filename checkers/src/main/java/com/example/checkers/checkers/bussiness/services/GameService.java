package com.example.checkers.checkers.bussiness.services;

import com.example.checkers.checkers.bussiness.Move;
import com.example.checkers.checkers.bussiness.repositories.BoardStateRepository;
import com.example.checkers.checkers.exceptions.GameException;
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
        bsRepo.save(game.getCurrent());
        return gameRepository.save(game);
    }

    public GameDTO createGame(GameDTO game){
        Game newGame = GameDTO.updateEntity(new Game(), game);
        if (game.getCurrent().getCurrentState() == null){
            boardService.init(8, game.getCurrent());
            game.setCurrent(boardService.init(8, game.getCurrent()));
        }
        Game result = createGame(newGame);
        return GameDTO.fromEntity(result);
    }

    public List<GameDTO> getAllGames() {
        return gameRepository.findAll().stream().map(GameDTO::fromEntity).collect(Collectors.toList());
    }

    public Optional<GameDTO> findGameById(Long id) {
        Optional<Game> game = gameRepository.findById(id);
        return game.map(GameDTO::fromEntity);
    }

    public void delete(Long id) throws GameException{
        try {
            Game game = gameRepository.findById(id).orElse(null);
            if (game == null) {
                throw new GameException("There is no such game!");
            } else {
                gameRepository.deleteById(id);
            }
        } catch (GameException ex) {
            throw ex;
        }
    }

    public GameDTO update(GameDTO game, MoveRequest move) {
        Game updated = GameDTO.updateEntity(new Game(), game);
        BoardState boardState = updated.getCurrent();
        boardService.updateState(boardState, move);
        updated.setCurrent(boardState);

        Game result = createGame(updated);
        return GameDTO.fromEntity(result);
    }

}
