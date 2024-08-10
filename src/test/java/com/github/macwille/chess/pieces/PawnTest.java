package com.github.macwille.chess.pieces;

import com.github.macwille.chess.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class PawnTest {
    Board board;
    WhitePlayer white;
    BlackPlayer black;

    @BeforeEach
    void setupBoard() {
        this.board = new Board();
        this.white = new WhitePlayer();
        this.black = new BlackPlayer();
        board.setUp(white, black);
    }

    @Test
    void testFirstMove() {
        Notation notation = new ClassicNotation("A", "2");
        Notation playedNotation = new ClassicNotation("A", "4");
        Square square = board.square(notation);
        Assertions.assertFalse(square.isEmpty());
        Assertions.assertTrue(square.pick().isPresent());
        Piece piece = square.pick().get();
        Assertions.assertTrue(piece.owner().isWhite());
        Assertions.assertFalse(piece.hasMoved());
        List<Square> moves = piece.legalMoves(notation, board);
        Assertions.assertEquals(2, moves.size());
        piece.move(moves.getLast());
        Assertions.assertEquals(1, piece.legalMoves(playedNotation, board).size());
    }

    @Test
    public void testBlocked() {
        Notation notation = new ClassicNotation("A", "2");
        board.square(new ClassicNotation("A", "3")).place(new Pawn(white));
        Square square = board.square(notation);
        Assertions.assertTrue(square.pick().isPresent());
        Piece piece = square.pick().get();
        Assertions.assertFalse(piece.hasMoved());
        List<Square> moves = piece.legalMoves(notation, board);
        Assertions.assertTrue(moves.isEmpty());
    }

    @Test
    public void testCapture() {
        Notation notation = new ClassicNotation("B", "2");
        board.square(new ClassicNotation("B", "3")).place(new Pawn(white));
        Square square = board.square(notation);
        Assertions.assertTrue(square.pick().isPresent());
        Piece piece = square.pick().get();
        Assertions.assertFalse(piece.hasMoved());
        List<Square> moves = piece.legalMoves(notation, board);
        Assertions.assertTrue(moves.isEmpty());

    }
}
