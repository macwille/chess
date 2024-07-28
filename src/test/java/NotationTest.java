import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class NotationTest {

    public NotationTest() {

    }

    @Test
    public void testValid() {
        Notation first = new Notation("A","1");
        Notation second = new Notation("H","8");
        Notation third = new Notation("A","8");
        Notation fourth = new Notation("H","1");
        Assertions.assertTrue(first.isValid());
        Assertions.assertTrue(second.isValid());
        Assertions.assertTrue(third.isValid());
        Assertions.assertTrue(fourth.isValid());
    }

    @Test
    public void testInvalidFile() {
        Notation first = new Notation("J", "1");
        Notation second = new Notation("1", "1");
        Assertions.assertThrows(IllegalArgumentException.class, first::isValid);
        Assertions.assertThrows(IllegalArgumentException.class, second::isValid);
    }

    @Test
    public void testInvalidColumn() {
        Notation first = new Notation("A", "0");
        Notation second = new Notation("H", "9");
        Assertions.assertThrows(IllegalArgumentException.class, first::isValid);
        Assertions.assertThrows(IllegalArgumentException.class, second::isValid);
    }

    @Test
    public void testFileInt() {
        Notation first = new Notation("A", "1");
        Notation second = new Notation("H", "1");
        Assertions.assertEquals(1,first.fileInt());
        Assertions.assertEquals(8,second.fileInt());
    }

    @Test
    public void testColumnInt() {
        Notation first = new Notation("A", "1");
        Notation second = new Notation("H", "8");
        Assertions.assertEquals(1,first.columnInt());
        Assertions.assertEquals(8,second.columnInt());
    }

    @Test
    public void testOnBoard() {
        Board board = new Board();
        board.setUp(new WhitePlayer(), new BlackPlayer());
        Notation a1 = new Notation("A", "1");
        Notation d4 = new Notation("D", "4");
        Square a1Square = board.square(a1);
        Square d4Square = board.square(d4);
        Assertions.assertFalse(a1Square.isEmpty());
        Assertions.assertTrue(d4Square.isEmpty());
    }

    @Test
    public void testPrint() {
        Notation notation = new Notation("A", "1");
        Assertions.assertEquals("(A, 1)", notation.toString());
    }
}