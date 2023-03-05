package com.example.checkers.checkers.bussiness.services;

import com.example.checkers.checkers.bussiness.Board;
import com.example.checkers.checkers.bussiness.Difficulty;
import com.example.checkers.checkers.bussiness.MoveComments;
import com.example.checkers.checkers.bussiness.repositories.BoardStateRepository;
import com.example.checkers.checkers.exceptions.IllegalMoveException;
import com.example.checkers.checkers.models.entities.*;
import com.example.checkers.checkers.models.dto.GameDTO;
import com.example.checkers.checkers.bussiness.Player;
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

    public Game createGame(Contestant player, Difficulty difficulty) {
        Game game = new Game();
        game.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
        game.setContestant(player);
        game.setDifficulty(difficulty);
//        game.setStatus(GameStatusEnum.NEW);
        gameRepository.save(game);
        return game;
    }

    public void updateState(Move move, Player player) {
        if (board.getPiece((int) move.next.getX(), (int)  move.next.getY()) != null) {
            throw new IllegalMoveException(MoveComments.NO_FREE_SPACE);
        }
        board.update(move);
    }

    public Game newGame(Contestant player, Difficulty difficulty) {
        Game game = new Game(player, difficulty);
//        game.setStatus(GameStatusEnum.NEW);
////        game.setDifficulty(game.getDifficulty());
        gameRepository.save(game);
        return game;
    }

    public Game createGame(Game game){
//    Optional<Game> existingGame = gameRepository.findOne(Example.of(game));
//        if (existingGame.isPresent()) {
//            return existingGame.get();
//         }
//        game.setContestant(game.getContestant());
//        game.setDifficulty(game.getDifficulty());

        return gameRepository.save(game);

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
}
