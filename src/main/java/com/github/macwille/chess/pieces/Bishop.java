package com.github.macwille.chess.pieces;

import com.github.macwille.chess.Board;
import com.github.macwille.chess.Notation;
import com.github.macwille.chess.Player;
import com.github.macwille.chess.Square;

import java.util.ArrayList;
import java.util.List;

public final class Bishop extends AbstractPiece {
    public Bishop(Player player) {
        super(player);
    }

    @Override
    public List<Square> legalMoves(Notation notation, Board board) {
        int rank = notation.rankInt();
        List<Square> file = board.file(notation);
        List<Square> moves;
        if (!isWhite()) {
            file = file.reversed();
            rank = 9 - rank;
        }
        moves = hasMoved() ?
                file.subList(rank, rank + 1)
                :
                file.subList(rank, rank + 2);
        int i = 0;
        for (Square square : moves) {
            if (!square.isEmpty()) {
                moves = moves.subList(0, i);
            }
            i++;
        }
        moves.addAll(checkCaptures(notation));
        return moves;
    }

    public List<Square> checkCaptures(Notation notation) {
        List<Square> squares = new ArrayList<>(2);
        int fileInt = notation.fileInt();
        // check
        if (fileInt > 1) {
        }
        if (fileInt < 8) {
        }
        return squares;
    }


    @Override
    public String toString() {
        return "[B]";
    }
}
