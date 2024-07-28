public class BlackSquare extends Square {
    public BlackSquare() {
    }

    @Override
    public String toString() {
        if (current.isEmpty()) {
            return "[_]";
        } else {
            return current.getFirst().toString();
        }
    }
}
