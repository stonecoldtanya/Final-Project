package com.example.checkers.checkers.controllers;

import com.example.checkers.checkers.bussiness.services.BoardService;
import com.example.checkers.checkers.bussiness.services.ContestantService;
import com.example.checkers.checkers.bussiness.services.GameService;
import com.example.checkers.checkers.exceptions.GameException;
import com.example.checkers.checkers.models.dto.BoardStateDTO;
import com.example.checkers.checkers.models.dto.GameDTO;
import com.example.checkers.checkers.models.entities.Contestant;
import com.example.checkers.checkers.models.entities.MoveRequest;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/games")
public class GameController {
    private final GameService gameService;
    private final ContestantService playersService;
    private final BoardService boardService;

    @Autowired
    public GameController(GameService gameService, ContestantService playersService, BoardService boardService) {
        this.gameService = gameService;
        this.playersService = playersService;
        this.boardService = boardService;
    }

    @GetMapping(value = "/{id}/player", produces = "application/json")
    public @ResponseBody Contestant getPlayer(@PathVariable @NotNull Long id) {
        Optional<GameDTO> result = this.gameService.findGameById(id);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found");
        } else {
            return result.get().getContestant();
        }
    }

    @GetMapping
    public List<GameDTO> getGames() {
        return this.gameService.getAllGames();
    }


    @GetMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody GameDTO getGameById(@PathVariable @NotNull Long id) {
        if (id == null) {
            new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request, id can not be null");
        }
        Optional<GameDTO> result = this.gameService.findGameById(id);

        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found");
        } else {
            return result.get();
        }
    }

    @GetMapping(value = "/{id}/state", produces = "application/json")
    public @ResponseBody BoardStateDTO getGameState(@PathVariable @NotNull Long id) {
        if (id == null) {
            new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request, id can not be null");
        }
        Optional<GameDTO> result = this.gameService.findGameById(id);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found");
        } else {
            return BoardStateDTO.fromEntity(result.get().getCurrent());
        }
    }

    @GetMapping(value = "/{id}/board", produces = "application/json")
    public String getBoardState(@PathVariable @NotNull Long id) {
        if (id == null) {
            new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request, id can not be null");
        }
        Optional<BoardStateDTO> result = this.boardService.findBoardById(id);

        return result.get().toString();

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public @ResponseBody GameDTO createGame(@RequestBody GameDTO game) {
        return gameService.createGame(game);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/move")
    public @ResponseBody GameDTO move(@RequestBody GameDTO game, MoveRequest request){
        return gameService.update(game, request);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable("id") Long id) {
        try {
            gameService.delete(id);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } catch (GameException ex) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//    @PostMapping("/turn")
//    public TurnResponse turn(@RequestBody @Valid TurnRequest request) {
//        if (gameService.isGameOver()) {
//            System.out.println("Game is already over!");
//            return new TurnResponse(findWinner(), gameService.board);
//        } else {
//            gameService.updateState(String.valueOf(request.getPosition()), playersService.getPlayer(request.getPlayerId()));
//            return new TurnResponse(findWinner(), gameService.board);
//        }
//    }
}
