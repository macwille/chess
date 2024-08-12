package com.github.macwille.chess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NotationTest {

    @Test
    public void testIntegerNotation() {
        Notation first = new IntegerNotation(1, 2);
        Notation invalid = new IntegerNotation(0, 2);
        Notation invalid2 = new IntegerNotation(1, 0);
        Assertions.assertTrue(first.isValid());
        Assertions.assertTrue(first.toString().contains("A"));
        Assertions.assertTrue(first.toString().contains("2"));
        Assertions.assertFalse(invalid.isValid());
        Assertions.assertFalse(invalid2.isValid());
    }

    @Test
    public void testValid() {
        Notation first = new ClassicNotation("A", "1");
        Notation second = new ClassicNotation("H", "8");
        Notation third = new ClassicNotation("A", "8");
        Notation fourth = new ClassicNotation("H", "1");
        Assertions.assertTrue(first.isValid());
        Assertions.assertTrue(second.isValid());
        Assertions.assertTrue(third.isValid());
        Assertions.assertTrue(fourth.isValid());
    }

    @Test
    public void testInvalidFile() {
        Notation first = new ClassicNotation("J", "1");
        Notation second = new ClassicNotation("1", "1");
        Assertions.assertFalse(first.isValid());
        Assertions.assertFalse(second.isValid());
    }

    @Test
    public void testInvalidColumn() {
        Notation first = new ClassicNotation("A", "0");
        Notation second = new ClassicNotation("H", "9");
        Assertions.assertFalse(first.isValid());
        Assertions.assertFalse(second.isValid());
    }

    @Test
    public void testRankInt() {
        Notation first = new ClassicNotation("A", "1");
        Notation second = new ClassicNotation("A", "8");
        Assertions.assertEquals(1, first.rankInt());
        Assertions.assertEquals(8, second.rankInt());
    }

    @Test
    public void testFileInt() {
        Notation first = new ClassicNotation("A", "1");
        Notation second = new ClassicNotation("H", "1");
        Assertions.assertEquals(1, first.fileInt());
        Assertions.assertEquals(8, second.fileInt());
    }

    @Test
    public void testOnBoard() {
        Board board = new Board();
        board.setUp(new WhitePlayer(), new BlackPlayer());
        Notation a1 = new ClassicNotation("A", "1");
        Notation d4 = new ClassicNotation("D", "4");
        Square a1Square = board.square(a1);
        Square d4Square = board.square(d4);
        Assertions.assertFalse(a1Square.isEmpty());
        Assertions.assertTrue(d4Square.isEmpty());
    }

    @Test
    public void testPrint() {
        Notation notation = new ClassicNotation("A", "1");
        Assertions.assertEquals("(A1)", notation.toString());
    }
}
