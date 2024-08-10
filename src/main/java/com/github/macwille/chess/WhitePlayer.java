package com.github.macwille.chess;

public final class WhitePlayer implements Player {
    private final String name;

    public WhitePlayer() {
        this.name = "White";
    }

    public WhitePlayer(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public boolean isWhite() {
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
}
