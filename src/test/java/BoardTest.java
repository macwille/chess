import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardTest {

    public BoardTest() {

    }

    @Test
    public void testInit() {
        Assertions.assertDoesNotThrow(() -> {
            Board board = new Board();
            Assertions.assertFalse(board.isSetUp());
            board.setUp(new WhitePlayer(), new BlackPlayer());
            Assertions.assertTrue(board.isSetUp());
            Square a1 = board.square(new Notation("A", "1"));
            Square b1 = board.square(new Notation("B", "1"));
            Square a8 = board.square(new Notation("A", "8"));
            Square b8 = board.square(new Notation("B", "8"));
            Square a3 = board.square(new Notation("A", "3"));
            Square h6 = board.square(new Notation("H", "6"));
            Assertions.assertTrue(a3.isEmpty());
            Assertions.assertTrue(h6.isEmpty());
            Assertions.assertEquals(BlackSquare.class, a1.getClass());
            Assertions.assertEquals(WhiteSquare.class, b1.getClass());
            Assertions.assertEquals(WhiteSquare.class, a8.getClass());
            Assertions.assertEquals(BlackSquare.class, b8.getClass());
        });
    }

    @Test
    public void testWhiteMajorPieces() {
        Player white = new WhitePlayer();
        Player black = new BlackPlayer();
        Board board = new Board();
        board.setUp(white, black);
        Square a = board.square(new Notation("A", "1"));
        Square b = board.square(new Notation("B", "1"));
        Square c = board.square(new Notation("C", "1"));
        Square d = board.square(new Notation("D", "1"));
        Square e = board.square(new Notation("E", "1"));
        Square f = board.square(new Notation("F", "1"));
        Square g = board.square(new Notation("G", "1"));
        Square h = board.square(new Notation("H", "1"));
        Assertions.assertEquals(white, a.pick().owner());
        Assertions.assertEquals(white, b.pick().owner());
        Assertions.assertEquals(white, c.pick().owner());
        Assertions.assertEquals(white, d.pick().owner());
        Assertions.assertEquals(white, e.pick().owner());
        Assertions.assertEquals(white, f.pick().owner());
        Assertions.assertEquals(white, g.pick().owner());
        Assertions.assertEquals(white, h.pick().owner());
        Assertions.assertEquals(Rook.class, a.pick().getClass());
        Assertions.assertEquals(Rook.class, h.pick().getClass());
        Assertions.assertEquals(Knight.class, b.pick().getClass());
        Assertions.assertEquals(Knight.class, g.pick().getClass());
        Assertions.assertEquals(Bisphop.class, c.pick().getClass());
        Assertions.assertEquals(Bisphop.class, f.pick().getClass());
        Assertions.assertEquals(Queen.class, d.pick().getClass());
        Assertions.assertEquals(King.class, e.pick().getClass());
    }

    @Test
    public void testBlackMajorPieces() {
        Player white = new WhitePlayer();
        Player black = new BlackPlayer();
        Board board = new Board();
        board.setUp(white, black);
        Square a = board.square(new Notation("A", "8"));
        Square b = board.square(new Notation("B", "8"));
        Square c = board.square(new Notation("C", "8"));
        Square d = board.square(new Notation("D", "8"));
        Square e = board.square(new Notation("E", "8"));
        Square f = board.square(new Notation("F", "8"));
        Square g = board.square(new Notation("G", "8"));
        Square h = board.square(new Notation("H", "8"));
        Assertions.assertEquals(black, a.pick().owner());
        Assertions.assertEquals(black, b.pick().owner());
        Assertions.assertEquals(black, c.pick().owner());
        Assertions.assertEquals(black, d.pick().owner());
        Assertions.assertEquals(black, e.pick().owner());
        Assertions.assertEquals(black, f.pick().owner());
        Assertions.assertEquals(black, g.pick().owner());
        Assertions.assertEquals(black, h.pick().owner());
        Assertions.assertEquals(Rook.class, a.pick().getClass());
        Assertions.assertEquals(Rook.class, h.pick().getClass());
        Assertions.assertEquals(Knight.class, b.pick().getClass());
        Assertions.assertEquals(Knight.class, g.pick().getClass());
        Assertions.assertEquals(Bisphop.class, c.pick().getClass());
        Assertions.assertEquals(Bisphop.class, f.pick().getClass());
        Assertions.assertEquals(Queen.class, d.pick().getClass());
        Assertions.assertEquals(King.class, e.pick().getClass());
    }

    @Test
    public void testPawns() {
        Player white = new WhitePlayer();
        Player black = new BlackPlayer();
        Board board = new Board();
        board.setUp(white, black);
        String[] files = {"A", "B", "C", "D", "E", "F", "G", "H"};
        for (int i = 0; i < 8; i++) {
            Square whiteSquare = board.square(new Notation(files[i], "2"));
            Square blackSquare = board.square(new Notation(files[i], "7"));
            Assertions.assertEquals(white, whiteSquare.pick().owner());
            Assertions.assertEquals(Pawn.class, whiteSquare.pick().getClass());
            Assertions.assertEquals(black, blackSquare.pick().owner());
            Assertions.assertEquals(Pawn.class, blackSquare.pick().getClass());
        }
    }
}