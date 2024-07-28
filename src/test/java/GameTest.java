import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTest {

    public GameTest() {
    }

    @Test
    public void testInit() {
        Assertions.assertDoesNotThrow(() -> {
            new Game();
            new Game(new WhitePlayer(), new BlackPlayer());
        });
    }
}