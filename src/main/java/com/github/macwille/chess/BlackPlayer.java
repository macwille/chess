package com.github.macwille.chess;

public final class BlackPlayer implements Player {
    private final String name;

    public BlackPlayer() {
        this.name = "Black";
    }

    public BlackPlayer(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public boolean isWhite() {
        return false;
    }

    @Override
    public String toString() {
        return name;
    }
}
