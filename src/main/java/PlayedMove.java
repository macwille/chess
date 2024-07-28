import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayedMove extends Move {
    private final Piece piece;
    private final List<Piece> capture;

    public PlayedMove(Player player, Notation from, Notation to, Board board, Piece piece) {
        super(player, from, to, board);
        this.piece = piece;
        this.capture = new ArrayList<>();
    }

    public PlayedMove(Player player, Notation from, Notation to, Board board, Piece piece, Piece capture) {
        super(player, from, to, board);
        this.piece = piece;
        this.capture = new ArrayList<>(Collections.singletonList(capture));
    }

    public List<Piece> captured() {
        return capture;
    }

    @Override
    public String toString() {
        return capture.isEmpty() ?
                player + " moved " + piece + " from " + from + " to " + to
                :
                player + " moved " + piece + " from " + from + " to " + to + " capturing " + capture.getFirst();
    }
}
