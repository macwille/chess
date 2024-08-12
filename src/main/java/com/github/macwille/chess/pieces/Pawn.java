package com.github.macwille.chess.pieces;

import com.github.macwille.chess.*;

import java.util.ArrayList;
import java.util.List;

public final class Pawn extends AbstractPiece implements Piece {

    public Pawn(Player player) {
        super(player);
    }

    private List<Square> checkCaptures(Notation notation, Board board) {
        List<Square> squares = new ArrayList<>();
        Square square = board.square(notation);
        boolean white = square.pick().get().owner().isWhite();
        int fileInt = notation.fileInt();
        int rankInt = notation.rankInt();
        Notation left = new IntegerNotation(fileInt + 1, rankInt + 1);
        Notation right = new IntegerNotation(fileInt - 1, rankInt + 1);

        if (!white) {
            System.out.println("black player");
            fileInt = 8 - fileInt;
            rankInt = 8 - rankInt;
            left = new IntegerNotation(fileInt, rankInt);
            right = new IntegerNotation(fileInt, rankInt);
        }

        if (left.isValid() && board.square(left).pick().isPresent()) {
            Square leftSquare = board.square(left);
            System.out.println("Checking " + left);
            if (leftSquare.pick().get().owner().isWhite() != white) {
                squares.add(leftSquare);
            }
        }
        if (right.isValid() && board.square(right).pick().isPresent()) {
            System.out.println("Checking " + right);
            if (board.square(right).pick().get().owner().isWhite() != white) {
                squares.add(board.square(right));
            }
        }
        return squares;
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
        moves.addAll(checkCaptures(notation, board));
        return moves;
    }

    @Override
    public String toString() {
        return "[P]";
    }
}
