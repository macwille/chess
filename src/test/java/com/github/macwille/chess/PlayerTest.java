package com.github.macwille.chess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    void testName() {
        Player player = new WhitePlayer("name");
        Assertions.assertEquals("name", player.name());
    }

    @Test
    void testColor() {
        Assertions.assertTrue(new WhitePlayer().isWhite());
        Assertions.assertFalse(new BlackPlayer().isWhite());
    }
}
