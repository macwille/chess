package com.github.macwille.chess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoveTest {
    WhitePlayer white;
    BlackPlayer black;
    Board board;


    @BeforeEach
    public void setupBoard() {
        this.white = new WhitePlayer();
        this.black = new BlackPlayer();
        this.board = new Board();
        board.setUp(white, black);
    }

    @Test
    public void testPlay() {
        Assertions.assertFalse(board.square(new ClassicNotation("A", "2")).isEmpty());
        PlayedMove played = new LegalMove(
                board, white, new ClassicNotation("A", "2"),
                new ClassicNotation("A", "3"))
                .play();
        Assertions.assertTrue(board.square(new ClassicNotation("A", "2")).isEmpty());
        Assertions.assertFalse(board.square(new ClassicNotation("A", "3")).isEmpty());
        Assertions.assertTrue(played.captured().isEmpty());
    }
}
