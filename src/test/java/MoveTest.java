import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MoveTest {
    private final Player white;
    private final Player black;

    public MoveTest() {
        this.white = new WhitePlayer();
        this.black = new BlackPlayer();
    }

    @Test
    public void testInit() {
        Assertions.assertDoesNotThrow(() -> {
            new Move(
                    white,
                    new Notation("A", "1"),
                    new Notation("A", "2"),
                    new Board()
            );
        });
    }

    @Test
    public void testPlay() {
        Board board = new Board();
        board.setUp(white, black);
        Assertions.assertFalse(board.square(new Notation("A","2")).isEmpty());
        Move move = new Move(white, new Notation("A", "2"), new Notation("A", "4"), board);
        PlayedMove played= move.play();
        Assertions.assertTrue(board.square(new Notation("A","2")).isEmpty());
        Assertions.assertFalse(board.square(new Notation("A","4")).isEmpty());
        Assertions.assertTrue(played.captured().isEmpty());
        Assertions.assertEquals(white, played.player);
    }

    @Test
    public void testCapture() {
        Board board = new Board();
        board.setUp(white, black);
        Assertions.assertFalse(board.square(new Notation("A","2")).isEmpty());
        Move move = new Move(white, new Notation("A", "2"), new Notation("A", "7"), board);
        PlayedMove played= move.play();
        Assertions.assertEquals(white, board.square(new Notation("A","7")).pick().owner());
        Assertions.assertEquals(white, board.square(new Notation("A","7")).pick().owner());
        Assertions.assertFalse(played.captured().isEmpty());
        Assertions.assertEquals(black, played.captured().getFirst().owner());
    }
}