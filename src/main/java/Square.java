import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Square {
    List<Piece> current;

    public Square() {
        this.current = new ArrayList<>();
    }

    public Square(Piece piece) {
        this.current = new ArrayList<>();
        current.add(piece);
    }

    public void place(Piece piece) {
        if (!current.isEmpty()) {
            current.clear();
        }
        current.add(piece);
    }

    public Piece pick() {
        if (current.isEmpty()) {
            throw new IllegalArgumentException("Square has no piece");
        }
        return current.getFirst();
    }

    public void clear() {
        if (!current.isEmpty()) {
            current.clear();
        }
    }
    public boolean isEmpty() {
        return current.isEmpty();
    }

    @Override
    public String toString() {
        if (current.isEmpty()) {
            return "[ ]";
        } else {
            return current.getFirst().toString();
        }
    }
}
