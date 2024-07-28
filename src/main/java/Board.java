import java.util.*;

public class Board {
    private final Square[][] board;
    private boolean isSetUp;

    public Board() {
        this.board = new Square[8][8];
        isSetUp = false;
    }

    public PlayedMove play(Move move) {
        LegalMove legal = new LegalMove(move);
        return legal.play();
    }

    public Square square(Notation notation) {
        int x = notation.fileInt() -1 ;
        int y = notation.columnInt() -1;
        if (x < 0 || x > 8 || y < 0 || y > 8) {
            throw new IllegalArgumentException("Coordinates need to be between 1 and 8");
        }
        return board[7 - y][x];
    }

    public Square[][] squares() {
        return board;
    }

    private void create() {
        boolean whiteSquare = false;
        for (int i = 0; i < 8; i++) {
            whiteSquare = !whiteSquare;
            for (int j = 0; j < 8; j++) {
                if (whiteSquare) {
                    board[i][j] = new WhiteSquare();
                } else {
                    board[i][j] = new BlackSquare();
                }
                whiteSquare = !whiteSquare;
            }
        }
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
                        board[i][j].place((new Bisphop(player)));
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
        isSetUp = true;
    }

    public boolean isSetUp() {
        return isSetUp;
    }

    private void clear() {
        for (int i = 0; i < 8; i++) {
            if (i == 1) {
                for (int j = 0; j < 8; j++) {
                    board[i][j].clear();
                }
            }
        }
    }

    @Override
    public String toString() {
        Queue<String> queue = new ArrayDeque<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H"));
        StringBuilder sb = new StringBuilder();
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
