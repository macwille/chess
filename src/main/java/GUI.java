import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class GUI {
    private final JPanel panel = new JPanel(new BorderLayout(3, 3));

    public GUI() {
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setSize(900, 1200);
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.add(new JButton("New"));
        toolBar.add(new JButton("Resign"));
        panel.add(toolBar, BorderLayout.PAGE_START);
        panel.add(boardPanel());
    }

    public JPanel panel() {
        return panel;
    }

    private JPanel boardPanel() {
        Game game = new Game();
        Board board = game.board();
        Square[][] squares = board.squares();
        board.setUp(new WhitePlayer(), new BlackPlayer());
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(squares.length, squares[0].length, 0, 0));
        panel.setBorder(new LineBorder(Color.BLACK));
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                Square square = squares[i][j];
                String text = square.isEmpty() ? " " : square.pick().toString();
                JButton button;
                if (square.getClass().equals(BlackSquare.class)) {
                    button = squareButton(square, text, Color.BLACK, Color.WHITE);
                } else {
                    button = squareButton(square, text, Color.WHITE, Color.BLACK);
                }
                panel.add(button);
            }
        }
        panel.setPreferredSize(new Dimension(1200,1200));
        return panel;
    }

    private JButton squareButton(Square square, String text, Color background, Color foreground) {
        JButton button = new JButton(text);
        button.setBackground(background);
        button.setForeground(foreground);
        button.addActionListener(e -> System.out.println(square));
        return button;
    }
}
