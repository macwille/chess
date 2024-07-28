public class Move {
    protected final Player player;
    protected final Notation from;
    protected final Notation to;
    private final Board board;

    public Move(Player player, Notation from, Notation to, Board board) {
        this.player = player;
        this.from = from;
        this.to = to;
        this.board = board;
    }

    public PlayedMove play() {
        if (!from.isValid() && !to.isValid()) {
            throw new RuntimeException("Move Notation was not valid");
        }
        Square fromSquare = board.square(from);
        Square toSquare = board.square(to);
        if (fromSquare.isEmpty()) {
            throw new RuntimeException("Start square was empty");
        }
        Piece piece = fromSquare.pick();
        PlayedMove played = toSquare.isEmpty() ?
                new PlayedMove(player, from, to, board, piece)
                :
                new PlayedMove(player, from, to, board, piece, toSquare.pick());
        fromSquare.clear();
        piece.move(toSquare);

        return played;
    }

    @Override
    public String toString() {
        return String.format("(%s) -> (%s)", from, to);
    }
}
