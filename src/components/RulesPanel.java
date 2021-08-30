package components;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Rules screen JPanel for describing how to play the Minesweeper Game
 *
 * @author Jason Pak
 */

public class RulesPanel extends JPanel {
    private JFrame frameToClose;

    public RulesPanel(JFrame frameName) {
        frameToClose = frameName;
        setBackground(Color.BLACK);

        // display title
        JLabel title = new JLabel("Rules", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 45));
        title.setForeground(Color.ORANGE);
        title.setOpaque(true);
        title.setBackground(Color.BLACK);
        add(title);

        // create and display textarea
        TextArea textArea = new TextArea(

                "Welcome to MINESWEEPER!\n\n" +

                        "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n\n" +
                        "Gameplay: \n" +
                        "You are presented with a board of squares. Some squares contain mines (bombs), others don't. If you click on a\n" +
                        "square containing a bomb, you lose. If you manage to click all the squares (without clicking on any bombs) you\n" +
                        "win. Clicking a square which doesn't have a bomb reveals the number of neighboring squares containing bombs.\n" +
                        "Use this information plus some guess work to avoid the bombs. To open a square, simply click on the square.\n" +
                        "To mark a square you think is a bomb, point and shift-click. This will 'flag' a particular box if you think it may contain\n" +
                        "a mine. It is only for your utility, so you know not to click on that box.\n\n" +

                        "Detailed Instructions:\n" +
                        "A square's \"neighbors\" are the squares adjacent above, below, left, right, and all 4 diagonals. Squares on the sides\n" +
                        "of the board or in a corner have fewer neighbors. The board does not wrap around the edges. If you open a square\n" +
                        "with 0 neighboring bombs, all its neighbors will automatically open. This can cause a large area to automatically\n" +
                        "open. To remove a bomb marker (flag) from a square, point at it and right-click again. Click the reset button\n" +
                        "to start a new game.\n\n" +

                        "Status Information:\n" +
                        "The upper left corner contains the number of mines left to find. The number will update as you mark and unmark\n" +
                        "squares. The upper right corner contains a time counter. The timer will max out at 999 (16 minutes 39 seconds).\n" +
                        "To reset the game, simply click on the reset button at the top.\n\n" +
                        "Have Fun!",

                30, 90);

        textArea.setFont(new Font("Arial", Font.PLAIN, 12));
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);
        textArea.setEditable(false);
        add(textArea);

        // create a back button
        JButton back = new JButton("Back");
        back.setFont(new Font("Arial", Font.BOLD, 25));
        back.setForeground(Color.WHITE);
        back.setOpaque(true);
        back.setBackground(Color.BLACK);
        back.addActionListener(new BackListener());
        back.setBorderPainted(false);
        add(back);
    }

    /**
     * ActionListener for when back button is pushed
     */
    private class BackListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFrame frame = new JFrame("Minesweeper");
            frame.setContentPane(new HomePanel(frame));
            HomePanel.displayFrame(frame, frameToClose);
        }
    }
}


