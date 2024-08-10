package com.github.macwille.chess;

import com.github.macwille.chess.pieces.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class BoardTest {
    Player white;
    Player black;
    Board board;

    @BeforeEach
    public void setupBoard() {
        this.white = new WhitePlayer();
        this.black = new BlackPlayer();
        this.board = new Board();
        board.setUp(white, black);
    }

    @Test
    public void testInit() {
        Square a1 = board.square(new ClassicNotation("A", "1"));
        Square b1 = board.square(new ClassicNotation("B", "1"));
        Square a8 = board.square(new ClassicNotation("A", "8"));
        Square b8 = board.square(new ClassicNotation("B", "8"));
        Square a3 = board.square(new ClassicNotation("A", "3"));
        Square h6 = board.square(new ClassicNotation("H", "6"));
        Assertions.assertTrue(a3.isEmpty());
        Assertions.assertTrue(h6.isEmpty());
        Assertions.assertFalse(a1.isWhite());
        Assertions.assertTrue(b1.isWhite());
        Assertions.assertTrue(a8.isWhite());
        Assertions.assertFalse(b8.isWhite());
    }

    @Test
    public void testWhiteMajorPieces() {
        Square a = board.square(new ClassicNotation("A", "1"));
        Square b = board.square(new ClassicNotation("B", "1"));
        Square c = board.square(new ClassicNotation("C", "1"));
        Square d = board.square(new ClassicNotation("D", "1"));
        Square e = board.square(new ClassicNotation("E", "1"));
        Square f = board.square(new ClassicNotation("F", "1"));
        Square g = board.square(new ClassicNotation("G", "1"));
        Square h = board.square(new ClassicNotation("H", "1"));
        Square[] squares = {a, b, c, d, e, f, g, h};
        Arrays.stream(squares).forEach(s -> Assertions.assertTrue(s.pick().isPresent()));
        Arrays.stream(squares).forEach(s -> {
            Assertions.assertTrue(s.pick().isPresent());
            Assertions.assertEquals(white, s.pick().get().owner());
        });
        Assertions.assertTrue(a.pick().isPresent());
        Assertions.assertEquals(Rook.class, a.pick().get().getClass());
        Assertions.assertTrue(h.pick().isPresent());
        Assertions.assertEquals(Rook.class, h.pick().get().getClass());
        Assertions.assertTrue(b.pick().isPresent());
        Assertions.assertEquals(Knight.class, b.pick().get().getClass());
        Assertions.assertTrue(g.pick().isPresent());
        Assertions.assertEquals(Knight.class, g.pick().get().getClass());
        Assertions.assertTrue(c.pick().isPresent());
        Assertions.assertEquals(Bishop.class, c.pick().get().getClass());
        Assertions.assertTrue(f.pick().isPresent());
        Assertions.assertEquals(Bishop.class, f.pick().get().getClass());
        Assertions.assertTrue(d.pick().isPresent());
        Assertions.assertEquals(Queen.class, d.pick().get().getClass());
        Assertions.assertTrue(e.pick().isPresent());
        Assertions.assertEquals(King.class, e.pick().get().getClass());
    }

    @Test
    public void testBlackMajorPieces() {
        Square a = board.square(new ClassicNotation("A", "8"));
        Square b = board.square(new ClassicNotation("B", "8"));
        Square c = board.square(new ClassicNotation("C", "8"));
        Square d = board.square(new ClassicNotation("D", "8"));
        Square e = board.square(new ClassicNotation("E", "8"));
        Square f = board.square(new ClassicNotation("F", "8"));
        Square g = board.square(new ClassicNotation("G", "8"));
        Square h = board.square(new ClassicNotation("H", "8"));
        Square[] squares = {a, b, c, d, e, f, g, h};
        Arrays.stream(squares).forEach(s -> Assertions.assertTrue(s.pick().isPresent()));
        Arrays.stream(squares).forEach(s -> {
                    Assertions.assertTrue(s.pick().isPresent());
                    Assertions.assertEquals(black, s.pick().get().owner());
                }
        );
        Assertions.assertTrue(a.pick().isPresent());
        Assertions.assertEquals(Rook.class, a.pick().get().getClass());
        Assertions.assertTrue(h.pick().isPresent());
        Assertions.assertEquals(Rook.class, h.pick().get().getClass());
        Assertions.assertTrue(b.pick().isPresent());
        Assertions.assertEquals(Knight.class, b.pick().get().getClass());
        Assertions.assertTrue(g.pick().isPresent());
        Assertions.assertEquals(Knight.class, g.pick().get().getClass());
        Assertions.assertTrue(c.pick().isPresent());
        Assertions.assertEquals(Bishop.class, c.pick().get().getClass());
        Assertions.assertTrue(f.pick().isPresent());
        Assertions.assertEquals(Bishop.class, f.pick().get().getClass());
        Assertions.assertTrue(d.pick().isPresent());
        Assertions.assertEquals(Queen.class, d.pick().get().getClass());
        Assertions.assertTrue(e.pick().isPresent());
        Assertions.assertEquals(King.class, e.pick().get().getClass());
    }

    @Test
    public void testPawns() {
        String[] files = {"A", "B", "C", "D", "E", "F", "G", "H"};
        for (int i = 0; i < 8; i++) {
            Square whiteSquare = board.square(new ClassicNotation(files[i], "2"));
            Square blackSquare = board.square(new ClassicNotation(files[i], "7"));
            Assertions.assertTrue(whiteSquare.pick().isPresent());
            Assertions.assertTrue(blackSquare.pick().isPresent());
            Assertions.assertEquals(white, whiteSquare.pick().get().owner());
            Assertions.assertEquals(Pawn.class, whiteSquare.pick().get().getClass());
            Assertions.assertEquals(black, blackSquare.pick().get().owner());
            Assertions.assertEquals(Pawn.class, blackSquare.pick().get().getClass());
        }
    }

    @Test
    public void testRank() {
        Assertions.assertTrue(board.rank(new ClassicNotation("A", "1")).stream().allMatch(s -> s.pick().isPresent()));
        boolean allWhite = board.rank(new ClassicNotation("A", "2")).stream().allMatch(s -> {
            Assertions.assertTrue(s.pick().isPresent());
            return s.pick().get().owner().isWhite();
        });
        boolean allBlack = board.rank(new ClassicNotation("A", "7")).stream().noneMatch(s -> {
            Assertions.assertTrue(s.pick().isPresent());
            return s.pick().get().owner().isWhite();
        });
        Assertions.assertTrue(allWhite);
        Assertions.assertTrue(allBlack);
        Assertions.assertEquals(8, board.rank(new ClassicNotation("A", "1")).size());
        Assertions.assertTrue(board.rank(new ClassicNotation("A", "3")).stream().allMatch(s -> s.pick().isEmpty()));
        Square square = board.rank(new ClassicNotation("A", "1")).get(3);
        Assertions.assertTrue(square.pick().isPresent());
        Assertions.assertEquals(square.pick().get().getClass(), Queen.class);
    }

    @Test
    public void testFile() {
        Square first = board.file(new ClassicNotation("A", "1")).getFirst();
        Assertions.assertTrue(first.pick().isPresent());
        Assertions.assertTrue(first.pick().get().owner().isWhite());
        List<Square> file = board.file(new ClassicNotation("A", "1"));
        Assertions.assertTrue(file.get(0).pick().isPresent());
        Assertions.assertTrue(file.get(0).pick().get().owner().isWhite());
        Assertions.assertTrue(file.get(3).pick().isEmpty());
        Assertions.assertTrue(file.get(file.size() - 3).pick().isEmpty());
        Assertions.assertTrue(file.getLast().pick().isPresent());
        Assertions.assertFalse(file.getLast().pick().get().owner().isWhite());
    }
}
