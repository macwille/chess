import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class SquareTest {

    public SquareTest() {

    }

    @Test
    public void testInit() {
        Assertions.assertDoesNotThrow(() -> {
            Square empty = new Square();
            Square withPiece = new Square(new Piece(new WhitePlayer()));
            Assertions.assertTrue(empty.isEmpty());
            Assertions.assertFalse(withPiece.isEmpty());
        });
    }

    @Test
    public void testPlace() {
        Square square = new Square();
        Assertions.assertTrue(square.isEmpty());
        square.place(new Piece(new WhitePlayer()));
        Assertions.assertFalse(square.isEmpty());
    }

    @Test
    public void testPick() {
        Piece pawn = new Pawn(new WhitePlayer());
        Square square = new Square(pawn);
        Assertions.assertEquals(pawn, square.pick());
        Assertions.assertFalse(square.isEmpty());
    }

    @Test
    public void testClear() {
        Piece pawn = new Pawn(new WhitePlayer());
        Square square = new Square(pawn);
        square.clear();
        Assertions.assertTrue(square.isEmpty());
    }
}