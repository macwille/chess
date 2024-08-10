package com.github.macwille.chess;

import com.github.macwille.chess.pieces.Piece;

public final class LegalMove implements Move {
    private final Player player;
    private final Notation from;
    private final Notation to;
    private final Board board;

    public LegalMove(Board board, Player player, Notation from, Notation to) {
        this.board = board;
        this.player = player;
        this.from = from;
        this.to = to;
    }

    public PlayedMove play() {
        Square fromSquare = board.square(from);
        Square toSquare = board.square(to);
        if (illegal() && fromSquare.pick().isEmpty()) {
            throw new RuntimeException("Illegal move");
        }
        Piece piece = fromSquare.pick().get();
        PlayedMove played = toSquare.pick().isPresent() ?
                new PlayedMove(this, toSquare.pick().get())
                :
                new PlayedMove(this);
        fromSquare.clear();
        piece.move(toSquare);
        return played;
    }

    public boolean illegal() {
        Square fromSquare = board.square(from);
        Square toSquare = board.square(to);
        boolean illegal = false;
        if (fromSquare.isEmpty()) {
            System.out.println("From square was empty");
            illegal = true;
        }
        if (fromSquare.equals(toSquare)) {
            System.out.println("Same from and to squares");
            illegal = true;
        }
        if (fromSquare.pick().isEmpty()) {
            System.out.println("No piece in square");
            illegal = true;
        }
        if (!fromSquare.pick().get().owner().equals(player)) {
            System.out.println("Not owner of piece");
            illegal = true;
        }
        if (!fromSquare.pick().get().legalMoves(from, board).contains(toSquare)) {
            System.out.println("Not legal move for piece");
            illegal = true;
        }
        return illegal;
    }

    @Override
    public String toString() {
        return String.format("'%s' played (%s) -> (%s)", player, from, to);
    }
}
