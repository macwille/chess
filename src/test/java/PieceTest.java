import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class PieceTest {

    public PieceTest() {

    }

    @Test
    public void testInit() {
        Assertions.assertDoesNotThrow(() -> {
            new Piece(new WhitePlayer());
        });
    }

    @Test
    public void testMove() {
        Piece piece = new Piece(new WhitePlayer());
        Square target = new Square();
        piece.move(target);

        Assertions.assertTrue(piece.hasMoved());
        Assertions.assertEquals(piece, target.pick());
    }

    @Test
    public void testCapture() {
        Piece piece = new Piece(new WhitePlayer());
        Piece captured = new Piece(new BlackPlayer());
        Square target = new Square();
        target.place(captured);
        Assertions.assertEquals(captured, target.pick());
        piece.move(target);
        Assertions.assertTrue(piece.hasMoved());
        Assertions.assertEquals(piece, target.pick());
    }
}