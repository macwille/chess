import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private final Board board;
    private final List<PlayedMove> moves;
    private final List<Piece> captures;
    private final WhitePlayer white;
    private final BlackPlayer black;

    public Game() {
        this.board = new Board();
        this.white = new WhitePlayer();
        this.black = new BlackPlayer();
        this.moves = new ArrayList<>();
        this.captures = new ArrayList<>();
    }

    public Game(WhitePlayer white, BlackPlayer black) {
        this.board = new Board();
        this.white = white;
        this.black = black;
        this.moves = new ArrayList<>();
        this.captures = new ArrayList<>();
    }

    public Player toPlay() {
        return (moves.isEmpty() || moves.size() % 2 == 0) ? white : black;
    }

    public Board board() {
        return board;
    }
    public void start() {
        board.setUp(white, black);
        Scanner scanner = new Scanner(System.in);
        System.out.print(">");
        while (scanner.hasNext()) {
            String input = scanner.next();
            if ("exit".equals(input)) {
                break;
            } else if ("print".equals(input)) {
                System.out.println("to play:" + toPlay());
                System.out.println(board);
            } else {
                String[] result = input.split(",");
                if (result.length == 2) {
                    String fromFile = result[0].substring(0, 1);
                    String fromCol = result[0].substring(1, 2);
                    String toFile = result[1].substring(0, 1);
                    String toCol = result[1].substring(1, 2);
                    String formated = String.format("(%s%s) -> (%s%s)", fromFile, fromCol, toFile, toCol);
                    System.out.println(formated);
                    Player player = toPlay();
                    Move move = new Move(player, new Notation(fromFile, fromCol), new Notation(toFile, toCol), board);
                    PlayedMove played = board.play(move);
                    moves.add(played);
                    captures.addAll(played.captured());

                } else {
                    System.out.println("Error with Input, start and end coordinates as csv. e.g. A1,B2");
                }
            }
        }
        System.out.print(">");
    }
}

