package com.example.checkers.checkers.controllers;

import com.example.checkers.checkers.bussiness.Player;
import com.example.checkers.checkers.bussiness.services.ContestantService;
import com.example.checkers.checkers.bussiness.services.GameService;
import com.example.checkers.checkers.models.dto.GameDTO;
import com.example.checkers.checkers.models.dto.MoveDTO;
import com.example.checkers.checkers.models.entities.Contestant;
import com.example.checkers.checkers.models.entities.Game;
import com.example.checkers.checkers.models.entities.Move;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/games")
public class GameController {
    private final GameService gameService;
    private final ContestantService playersService;

    @Autowired
    public GameController(GameService gameService, ContestantService playersService) {
        this.gameService = gameService;
        this.playersService = playersService;
    }

    @GetMapping("/players")
    public List<Player> getPlayers(@RequestParam(value = "id", defaultValue = "") String id) {
        return playersService.getPlayers();
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

    @GetMapping("/board")
    public String getInitBoard() {
        return gameService.board.toString();
    }

    @PostMapping("/board")
    public void add(@RequestParam int length){
        this.gameService.intBoard(length);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public @ResponseBody GameDTO createGame(@RequestBody GameDTO game) {
//        game.setContestant(game.getContestant());
        return gameService.createGame(game);
    }

    @PostMapping("/move")
    public List<Move> move(@Valid @RequestBody MoveDTO request, Game game){
//        MoveDTO result = game.move(request);
//        moveAi(game);
        return null;
    }

//    @PostMapping("/connect")
//    public @ResponseBody GameDTO connect(@RequestBody GameDTO game){
//        return gameService.connectToGame(game.getId());
//    }


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
