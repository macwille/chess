package com.github.macwille.chess;

public interface Move {
    PlayedMove play();

    boolean illegal();

}
