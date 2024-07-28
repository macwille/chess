public class Piece implements Movable {
    private final Player player;
    private boolean moved;
    public Piece(Player player) {
        this.player = player;
        this.moved = false;
    }

    public void move(Square to) {
        System.out.printf("Moving %s to %s%n", this, to);
        to.place(this);
        moved = true;
    }

    public boolean hasMoved() {
        return moved;
    }

    public Player owner() {
        return player;
    }
}
