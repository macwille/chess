package com.github.macwille.chess.pieces;

import com.github.macwille.chess.Board;
import com.github.macwille.chess.Notation;
import com.github.macwille.chess.Player;
import com.github.macwille.chess.Square;

import java.util.List;

public abstract class AbstractPiece implements Piece {
    private final Player owner;
    private boolean moved;

    public AbstractPiece(Player owner) {
        this.owner = owner;
        this.moved = false;
    }

    public void move(Square to) {
        to.place(this);
        moved = true;
    }

    public Player owner() {
        return owner;
    }

    public boolean hasMoved() {
        return moved;
    }

    public boolean isWhite() {
        return owner.isWhite();
    }

    public abstract List<Square> legalMoves(Notation notation, Board board);
}
