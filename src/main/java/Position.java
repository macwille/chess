import java.lang.reflect.MalformedParametersException;
import java.util.Arrays;
import java.util.LinkedList;

public class Position {
    private final Square[][] board;
    private final LinkedList<Square> squares;
    public Position(Square[][] board) {
        this.board = board;
        squares = new LinkedList<>();
    }

    private void getPosition() {
        if (squares.size() != 64) {
            throw new MalformedParametersException("Position did not include exactly 64 squares");
        }
        for (int i = 0; i < 8; i++) {
            squares.addAll(Arrays.asList(board[i]).subList(0, 8));
        }
    }
}
