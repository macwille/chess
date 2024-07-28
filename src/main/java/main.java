import javax.swing.*;
import java.awt.*;

public class main {

    public static void main(String args[]) {
        GUI gui = new GUI();
        JFrame frame = new JFrame("Chess");
        frame.setPreferredSize(new Dimension(900,900));
        frame.add(gui.panel());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
