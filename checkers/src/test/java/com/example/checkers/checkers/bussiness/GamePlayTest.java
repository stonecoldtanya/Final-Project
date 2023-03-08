package com.example.checkers.checkers.bussiness;

import com.example.checkers.checkers.models.entities.Move;
import org.junit.jupiter.api.Test;


import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class GamePlayTest {

    @Test
    public void testEasyAgainstHardDifficultyPlay() {
        Board b = new Board(8);
        GamePlay game = new GamePlay(b.getBoard());
        game.setPlayer1(new BotPlayer("Roy", Difficulty.EASY, 'b'));
        game.setPlayer2(new BotPlayer(Difficulty.HARD,'w'));
        game.play(-1);
    }


    @Test
    public void testHardAgainstHardDifficultyPlay() {
        Board b = new Board(8);
        GamePlay game = new GamePlay(b.getBoard());
        game.setPlayer1(new BotPlayer("Roy", Difficulty.HARD, 'b'));
        game.setPlayer2(new BotPlayer(Difficulty.HARD, 'w'));
        game.play(-1);
    }

    @Test
    public void testExpertAgainstHardDifficultyPlay() {
        Board b = new Board(8);
        GamePlay game = new GamePlay(b.getBoard());
        game.setPlayer1(new BotPlayer("Roy", Difficulty.BOT_EXPERT, 'b'));
        game.setPlayer2(new BotPlayer(Difficulty.HARD, 'w'));
        game.play(-1);
    }

    @Test
    public void testFindBestMove() {
        Board b = new Board(8);
        GamePlay game = new GamePlay(b.getBoard());

        game.setPlayer1(new BotPlayer(Difficulty.BOT_EXPERT, 'b'));
        game.setPlayer2(new BotPlayer(Difficulty.BOT_EXPERT, 'w'));


        game.play(1);
        Move move = game.getState().getOriginMove();
//        assertEquals(new Move(new Point(0,1), new Point(1,2), true), move);
//        assertSame(game.getState().toString());


        game.play(1);
        move = game.getState().getOriginMove();
//        assertEquals(new Move(new Point(7,6), new Point(6,7), false), move);


        game.play(1);
        move = game.getState().getOriginMove();
        assertEquals(new Move(new Point(2,1), new Point(3,2), true), move);


        game.play(1);
        move = game.getState().getOriginMove();
        assertEquals(new Move(new Point(5,0), new Point(4,1), false), move);


        game.play(1);
        move = game.getState().getOriginMove();
        assertEquals(new Move(new Point(3,2), new Point(5,0), true), move);


        game.play(1);
        move = game.getState().getOriginMove();
        assertEquals(new Move(new Point(5,2), new Point(4,3),false), move);


        game.play(1);
        move = game.getState().getOriginMove();
        assertEquals(new Move(new Point(2,5), new Point(3,4), true), move);


        game.play(1);
        move = game.getState().getOriginMove();
        assertEquals(new Move(new Point(4,3), new Point(2,5), false), move);

        game.play(1);
        move = game.getState().getOriginMove();
        assertEquals(new Move(new Point(5,0), new Point(6,1), true), move);

    }

}
