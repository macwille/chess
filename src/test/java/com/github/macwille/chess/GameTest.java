package com.github.macwille.chess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTest {

    @Test
    public void testInit() {
        Assertions.assertDoesNotThrow(() -> {
            new Game();
            new Game(new WhitePlayer(), new BlackPlayer());
        });
    }

    @Test
    public void testStart() {
        Assertions.assertDoesNotThrow(() -> {
            WhitePlayer white = new WhitePlayer();
            Game game = new Game(white, new BlackPlayer());
            Assertions.assertEquals(white, game.toPlay());
        });
    }

    @Test
    public void testToMove() {
        Game game = new Game(new WhitePlayer(), new BlackPlayer());
        Assertions.assertTrue(game.toPlay().isWhite());
        game.start();
        game.play(new ClassicNotation("A", "2"), new ClassicNotation("A", "3"));
        Assertions.assertFalse(game.toPlay().isWhite());
        Assertions.assertThrows(RuntimeException.class, () ->
                game.play(new ClassicNotation("B", "2"), new ClassicNotation("B", "3")
                )
        );
        Assertions.assertFalse(game.toPlay().isWhite());
    }

    @Test
    public void testResign() {
        Game game = new Game(new WhitePlayer(), new BlackPlayer());
        FinishedGame whiteVictory = game.resign(false);
        FinishedGame blackVictory = game.resign(true);
        Assertions.assertTrue(whiteVictory.winner().isPresent());
        Assertions.assertTrue(whiteVictory.winner().get().isWhite());
        Assertions.assertTrue(whiteVictory.loser().isPresent());
        Assertions.assertFalse(whiteVictory.loser().get().isWhite());
        Assertions.assertTrue(blackVictory.winner().isPresent());
        Assertions.assertFalse(blackVictory.winner().get().isWhite());
        Assertions.assertTrue(blackVictory.loser().isPresent());
        Assertions.assertTrue(blackVictory.loser().get().isWhite());
    }

    @Test
    public void testDraw() {
        Game game = new Game(new WhitePlayer("white"), new BlackPlayer("black"));
        FinishedGame finished = game.draw();
        Assertions.assertTrue(finished.wasDraw());
        Assertions.assertTrue(finished.winner().isEmpty());
        Assertions.assertTrue(finished.loser().isEmpty());
    }
}
