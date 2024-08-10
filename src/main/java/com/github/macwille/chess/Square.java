package com.github.macwille.chess;

import com.github.macwille.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public final class Square {
    final List<Piece> current;
    final boolean isWhite;

    public Square() {
        this.current = new ArrayList<>(1);
        isWhite = true;
    }

    public Square(boolean isWhite) {
        this.current = new ArrayList<>(1);
        this.isWhite = isWhite;
    }

    public Square(Piece piece) {
        this.current = new ArrayList<>(Collections.singletonList(piece));
        isWhite = true;
    }

    public Square(Piece piece, boolean isWhite) {
        this.current = new ArrayList<>(Collections.singletonList(piece));
        this.isWhite = isWhite;
    }

    public void place(Piece piece) {
        if (!current.isEmpty()) {
            current.clear();
        }
        current.add(piece);
    }

    public Optional<Piece> pick() {
        return current.isEmpty() ?
                Optional.empty()
                :
                Optional.of(current.getFirst());
    }

    public void clear() {
        if (!current.isEmpty()) {
            current.clear();
        }
    }

    public boolean isEmpty() {
        return current.isEmpty();
    }

    public boolean isWhite() {
        return isWhite;
    }

    @Override
    public String toString() {
        if (current.isEmpty()) {
            return "[ ]";
        } else {
            return current.getFirst().toString();
        }
    }
}
