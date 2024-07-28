public class LegalMove {
    private final Move move;
    public LegalMove(Move move) {
        this.move = move;
    }

    public PlayedMove play() {
        System.out.println("Checking if move is legal");
        return move.play();
    }
}
