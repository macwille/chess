package com.github.macwille.chess;

import com.github.macwille.chess.pieces.*;

import java.util.*;
import java.util.stream.Collectors;

public final class Board {
    private final Square[][] board;

    public Board() {
        this.board = new Square[8][8];
    }

    public Square square(Notation notation) {
        int x = notation.fileInt() - 1;
        int y = notation.rankInt() - 1;
        if (x < 0 || x > 8 || y < 0 || y > 8) {
            throw new IllegalArgumentException("Coordinates need to be between 1 and 8");
        }
        return board[7 - y][x];
    }

    private void create() {
        boolean whiteSquare = false;
        for (int i = 0; i < 8; i++) {
            whiteSquare = !whiteSquare;
            for (int j = 0; j < 8; j++) {
                if (whiteSquare) {
                    board[i][j] = new Square();
                } else {
                    board[i][j] = new Square(false);
                }
                whiteSquare = !whiteSquare;
            }
        }
    }

    public List<Square> rank(Notation notation) {
        int i = notation.rankInt();
        i--;
        if (!notation.isValid()) {
            throw new IllegalArgumentException("Rank value outside of board");
        }
        return Arrays.stream(board[7 - i]).collect(Collectors.toList());
    }

    public List<Square> file(Notation notation) {
        int i = notation.fileInt();
        i--;
        if (!notation.isValid()) {
            throw new IllegalArgumentException("File value outside of board");
        }
        List<Square> squares = new ArrayList<>();
        for (Square[] value : board) {
            squares.add(value[i]);
        }
        return squares.reversed();
    }

    public void setUp(Player white, Player black) {
        create();
        for (int i = 0; i < 8; i++) {
            // major pieces
            if (i == 0 || i == 7) {
                Player player = i == 0 ? black : white;
                for (int j = 0; j < 8; j++) {
                    if (j == 0 || j == 7) {
                        board[i][j].place((new Rook(player)));
                    }
                    if (j == 1 || j == 6) {
                        board[i][j].place((new Knight(player)));
                    }
                    if (j == 2 || j == 5) {
                        board[i][j].place((new Bishop(player)));
                    }
                    if (j == 4) {
                        board[i][j].place((new King(player)));
                    }
                    if (j == 3) {
                        board[i][j].place((new Queen(player)));
                    }
                }
            }
            // pawns
            if (i == 1 || i == 6) {
                Player player = i == 1 ? black : white;
                for (int j = 0; j < 8; j++) {
                    board[i][j].place(new Pawn(player));
                }
            }
        }
    }

    @Override
    public String toString() {
        final Queue<String> queue = new ArrayDeque<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H"));
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (j == 0) {
                    sb.append(String.format(" %s ", 8 - i));
                }
                sb.append(board[i][j]);
                if (j == 7) {
                    sb.append("\n");
                }
            }
        }
        for (int i = 0; i <= 8; i++) {
            if (i > 0) {
                sb.append(" ").append(queue.poll()).append(" ");
            } else {
                sb.append("   ");
            }
        }
        return sb.toString();
    }
}
