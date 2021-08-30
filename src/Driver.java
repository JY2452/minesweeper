import components.HomePanel;
import javax.swing.JFrame;

/**
 * Driver to initiate the Minesweeper Game
 *
 * @author Jason Pak
 */

public class Driver {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Minesweeper");
        frame.setSize(700, 600);
        frame.setLocation(100, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new HomePanel(frame));
        frame.setVisible(true);
        frame.setResizable(false);
    }
}





