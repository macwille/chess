package com.github.macwille.chess;

import com.github.macwille.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class PlayedMove {
    private final Move move;
    private final List<Piece> capture;

    public PlayedMove(Move move) {
        this.move = move;
        this.capture = new ArrayList<>(1);
    }

    public PlayedMove(Move move, Piece capture) {
        this.move = move;
        this.capture = new ArrayList<>(Collections.singletonList(capture));
    }

    public List<Piece> captured() {
        return capture;
    }

    @Override
    public String toString() {
        return captured().isEmpty() ? move.toString() : String.format("'%s' capturing %s", move, captured().getFirst());
    }
}
