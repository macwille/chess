package com.github.macwille.chess;

import java.io.InputStream;
import java.util.Scanner;

public class TerminalUI {
    private final Scanner scanner;
    private Game game;

    public TerminalUI(InputStream is) {
        this.scanner = new Scanner(is);
        this.game = new Game();
    }

    public void start() {
        game.start();
        printHelp();
        while (true) {
            System.out.print(">");
            String input = scanner.next().trim().toLowerCase();
            if ("exit".equals(input)) {
                printExit();
                game.draw();
                break;
            }
            if ("help".equals(input)) {
                printHelp();
            }
            if ("print".equals(input)) {
                System.out.println(game);
            }
            if ("resign".equals(input)) {
                FinishedGame result = game.resign(game.toPlay().isWhite());
                printResign(result);
            }
            if ("move".equals(input)) {
                try {
                    System.out.print("From ");
                    Notation from = inputNotation();
                    System.out.print("To ");
                    Notation to = inputNotation();
                    game.play(from, to);
                    PlayedMove lastMove = game.moves().getLast();
                    System.out.println(lastMove);
                } catch (IllegalArgumentException e) {
                    System.out.println("Illegal move:\n" + e);
                }
            }
            if ("new".equals(input)) {
                game = inputGame();
                game.start();
            }
        }
    }

    private Notation inputNotation() {
        String input = scanner.next();
        String file = input.substring(0, 1);
        String col = input.substring(1, 2);
        return new ClassicNotation(file, col);
    }

    private Game inputGame() {
        System.out.print("White player name: ");
        WhitePlayer white = new WhitePlayer(scanner.next());
        System.out.print("Black player name: ");
        BlackPlayer black = new BlackPlayer(scanner.next());
        return new Game(white, black);
    }

    private void printHelp() {
        System.out.println("Welcome to chess");
        System.out.println("Commands: \n" +
                "print - 'prints board' move - 'inputs a move\n'" +
                "new - 'starts new game'\n" +
                "help - 'available commands'\n" +
                "exit - 'exits game' ");
    }

    private void printExit() {
        System.out.println("Game finished");
        System.out.println(game.draw());
    }

    private void printResign(FinishedGame finishedGame) {
        System.out.println(finishedGame);
    }
}
