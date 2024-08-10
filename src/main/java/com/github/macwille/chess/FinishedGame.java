package com.github.macwille.chess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public final class FinishedGame {
    private final List<PlayedMove> moves;
    private final Board board;
    private final List<Player> winner;
    private final List<Player> loser;

    public FinishedGame(Game game) {
        this.moves = game.moves();
        this.board = game.board();
        this.winner = new ArrayList<>();
        this.loser = new ArrayList<>();
    }

    public FinishedGame(Game game, Player winner, Player loser) {
        this.moves = game.moves();
        this.board = game.board();
        this.winner = new ArrayList<>(Collections.singletonList(winner));
        this.loser = new ArrayList<>(Collections.singletonList(loser));
    }

    public boolean wasDraw() {
        return this.winner.isEmpty() && loser.isEmpty();
    }

    public Optional<Player> winner() {
        return winner.isEmpty() ? Optional.empty() : Optional.of(winner.getFirst());
    }

    public Optional<Player> loser() {
        return loser.isEmpty() ? Optional.empty() : Optional.of(loser.getFirst());
    }

    @Override
    public String toString() {
        return (wasDraw()) ?
                String.format("%s defeated %s on move number %s \n %s", winner, loser, moves.size() - 1, board)
                :
                String.format("Draw on move number %s \n %s", moves.size() - 1, board);
    }
}
