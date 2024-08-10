package com.github.macwille.chess.pieces;

import com.github.macwille.chess.Board;
import com.github.macwille.chess.Notation;
import com.github.macwille.chess.Player;
import com.github.macwille.chess.Square;

import java.util.List;

public interface Piece {

    void move(Square to);

    boolean hasMoved();

    Player owner();

    List<Square> legalMoves(Notation notation, Board board);
}
