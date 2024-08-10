package com.github.macwille.chess;

import com.github.macwille.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public final class Game {
    private final Board board;
    private final List<PlayedMove> moves;
    private final List<Piece> captures;
    private final Player white;
    private final Player black;

    public Game() {
        this.board = new Board();
        this.moves = new ArrayList<>();
        this.captures = new ArrayList<>();
        this.white = new WhitePlayer();
        this.black = new BlackPlayer();
    }

    public Game(WhitePlayer white, BlackPlayer black) {
        this.board = new Board();
        this.moves = new ArrayList<>();
        this.captures = new ArrayList<>();
        this.white = white;
        this.black = black;
    }

    public Player toPlay() {
        return moves.isEmpty() || moves.size() % 2 == 0 ? white : black;
    }

    public void start() {
        board.setUp(white, black);
    }

    public void play(Notation from, Notation to) {
        Move move = new LegalMove(board, toPlay(), from, to);
        if (move.illegal()) {
            throw new IllegalArgumentException("Move was illegal");
        }
        PlayedMove played = move.play();
        moves.add(played);
    }

    public List<PlayedMove> moves() {
        return moves;
    }

    public Board board() {
        return board;
    }

    public FinishedGame draw() {
        return new FinishedGame(this);
    }

    public FinishedGame resign(boolean white) {
        return white ?
                new FinishedGame(this, this.black, this.white)
                :
                new FinishedGame(this, this.white, this.black);
    }

    @Override
    public String toString() {
        return String.format("Move %s - '%s' to play \n %s", moves.size() - 1, toPlay(), board);
    }
}

