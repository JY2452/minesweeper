package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;

/**
 * Home screen JPanel for Minesweeper Game
 *
 * @author Jason Pak
 */

public class HomePanel extends JPanel {
    private JFrame frameToClose;

    public HomePanel(JFrame frameName) {
        setLayout(new GridLayout(2, 1));
        setBackground(Color.WHITE);
        frameToClose = frameName;

        // display title of game
        JLabel title = new JLabel("MINESWEEPER", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 45));
        title.setForeground(Color.WHITE);
        title.setOpaque(true);
        title.setBackground(Color.BLACK);
        add(title);

        // display buttons
        JPanel center = new JPanel(new GridLayout(1, 3));
        add(center);

        // add left side gif animation
        JLabel left = new JLabel(new ImageIcon("assets/face.gif"));
        center.add(left);

        // make panel to store buttons
        JPanel buttons = new JPanel(new GridLayout(4, 1, 0, 5));
        buttons.setBackground(Color.lightGray);
        center.add(buttons);

        // create each navigation button
        makeButton("Start", new StartListener(), buttons);
        makeButton("High Scores", new HighScoreListener(), buttons);
        makeButton("Instructions", new RulesListener(), buttons);
        makeButton("Quit", new QuitListener(), buttons);

        // add right side gif animation
        JLabel right = new JLabel(new ImageIcon("assets/face.gif"));
        center.add(right);

    }

    /**
     * Creates a navigation button
     *
     * @param name           the text to display on the button
     * @param actionListener an ActionListener for when the button gets clicked
     * @param panel          the JPanel to add the button onto
     */
    private void makeButton(String name, ActionListener actionListener, JPanel panel) {
        JButton btn = new JButton(name);
        btn.setFont(new Font("Arial", Font.BOLD, 25));
        btn.setForeground(Color.WHITE);
        btn.setOpaque(true);
        btn.setBackground(Color.BLACK);
        btn.setBorderPainted(false);
        btn.addActionListener(actionListener);
        panel.add(btn);
    }

    /**
     * Individual ActionListeners for when each button is pushed
     */

    private class QuitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    private class StartListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFrame frame = new JFrame("Minesweeper");
            frame.setContentPane(new LevelPanel(frame));
            displayFrame(frame, frameToClose);
        }
    }

    private class HighScoreListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFrame frame = new JFrame("High Scores");
            frame.setContentPane(new HighScorePanel(frame));
            displayFrame(frame, frameToClose);
        }
    }

    private class RulesListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFrame frame = new JFrame("Rules");
            frame.setContentPane(new RulesPanel(frame));
            displayFrame(frame, frameToClose);
        }
    }

    /**
     * Displays the desired frame while closing the old one
     *
     * @param frame        the JFrame to open
     * @param frameToClose the JFrame to close
     */
    public static void displayFrame(JFrame frame, JFrame frameToClose) {
        frame.setSize(700, 600);
        frame.setLocation(100, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frameToClose.setVisible(false);
        frame.setResizable(false);
    }
}




