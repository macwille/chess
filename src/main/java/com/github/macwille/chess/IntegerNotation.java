package com.github.macwille.chess;

import java.util.Map;

public final class IntegerNotation implements Notation {
    private final static Map<Integer, String> inverted = Map.ofEntries(
            Map.entry(1, "A"),
            Map.entry(2, "B"),
            Map.entry(3, "C"),
            Map.entry(4, "D"),
            Map.entry(5, "E"),
            Map.entry(6, "F"),
            Map.entry(7, "G"),
            Map.entry(8, "H")
    );
    private final int rank;
    private final int file;

    public IntegerNotation(int file, int rank) {
        this.file = file;
        this.rank = rank;
    }

    public int fileInt() {
        return file;
    }

    public int rankInt() {
        return rank;
    }

    public boolean isValid() {
        return file > 0 && file < 9 && rank > 0 && rank < 9;
    }

    @Override
    public String toString() {
        return String.format("(%s%s)", inverted.get(file), rank);
    }
}
