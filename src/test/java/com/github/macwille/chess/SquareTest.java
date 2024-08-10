package com.github.macwille.chess;

import com.github.macwille.chess.pieces.Pawn;
import com.github.macwille.chess.pieces.Piece;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTest {

    @Test
    public void testInit() {
        Square empty = new Square();
        Square emptyBlack = new Square(false);
        Square withPiece = new Square(new Pawn(new WhitePlayer()));
        Square withPieceBlack = new Square(new Pawn(new WhitePlayer()), false);
        Assertions.assertTrue(empty.isEmpty());
        Assertions.assertTrue(emptyBlack.isEmpty());
        Assertions.assertFalse(withPiece.isEmpty());
        Assertions.assertFalse(withPieceBlack.isEmpty());
    }

    @Test
    public void testPlace() {
        Square square = new Square();
        Assertions.assertTrue(square.isEmpty());
        square.place(new Pawn(new WhitePlayer()));
        Assertions.assertFalse(square.isEmpty());
    }

    @Test
    public void testPick() {
        Piece pawn = new Pawn(new WhitePlayer());
        Square withPiece = new Square(pawn);
        Square withPieceBlack = new Square(pawn, false);
        Assertions.assertTrue(withPiece.pick().isPresent());
        Assertions.assertTrue(withPieceBlack.pick().isPresent());
        Assertions.assertEquals(pawn, withPiece.pick().get());
        Assertions.assertEquals(pawn, withPieceBlack.pick().get());
        Assertions.assertFalse(withPiece.isEmpty());
        Assertions.assertFalse(withPieceBlack.isEmpty());
    }

    @Test
    public void testClear() {
        Piece pawn = new Pawn(new WhitePlayer());
        Square square = new Square(pawn);
        square.clear();
        Assertions.assertTrue(square.isEmpty());
    }
}
