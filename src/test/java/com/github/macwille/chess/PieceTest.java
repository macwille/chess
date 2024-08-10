package com.github.macwille.chess;

import com.github.macwille.chess.pieces.Pawn;
import com.github.macwille.chess.pieces.Piece;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PieceTest {

    @Test
    public void testOwner() {
        Assertions.assertTrue(new Pawn(new WhitePlayer()).isWhite());
        Assertions.assertFalse(new Pawn(new BlackPlayer()).isWhite());
    }

    @Test
    public void testHasMoved() {
        Square square = new Square();
        Pawn pawn = new Pawn(new WhitePlayer());
        Assertions.assertFalse(pawn.hasMoved());
        pawn.move(square);
        Assertions.assertTrue(pawn.hasMoved());
    }

    @Test
    public void testMove() {
        Piece piece = new Pawn(new WhitePlayer("white"));
        Square target = new Square();
        piece.move(target);
        Assertions.assertTrue(piece.hasMoved());
        Assertions.assertTrue(target.pick().isPresent());
        Assertions.assertEquals(piece, target.pick().get());
    }

    @Test
    public void testCapture() {
        Piece piece = new Pawn(new WhitePlayer("white"));
        Piece captured = new Pawn(new BlackPlayer("black"));
        Square target = new Square();
        target.place(captured);
        Assertions.assertTrue(target.pick().isPresent());
        Assertions.assertEquals(captured, target.pick().get());
        piece.move(target);
        Assertions.assertTrue(piece.hasMoved());
        Assertions.assertEquals(piece, target.pick().get());
    }
}
