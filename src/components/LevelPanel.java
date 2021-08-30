package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * JPanel for choosing the difficulty level of the Minesweeper Game
 *
 * @author Jason Pak
 */

public class LevelPanel extends JPanel {
    private JFrame frameToClose;

    public LevelPanel(JFrame frameName) {
        setLayout(new GridLayout(5, 1, 20, 20));
        setBackground(Color.BLACK);
        frameToClose = frameName;

        // display question text
        JLabel question = new JLabel("Which Level?");
        question.setFont(new Font("Arial", Font.BOLD, 45));
        question.setBackground(Color.BLACK);
        question.setOpaque(true);
        question.setHorizontalAlignment(SwingConstants.CENTER);
        question.setForeground(Color.WHITE);
        add(question);

        // create each level button
        makeButton("LVL 1: 8X8", new OneListener(), Color.lightGray);
        makeButton("LVL 2: 16X16", new TwoListener(), Color.gray);
        makeButton("LVL 3: 24X24", new ThreeListener(), Color.darkGray);
        makeButton("Back", new BackListener(), Color.black);
    }

    /**
     * Creates a navigation button
     *
     * @param name           the text to display on the button
     * @param actionListener an ActionListener for when the button gets clicked
     * @param c              background Color of button
     */
    private void makeButton(String name, ActionListener actionListener, Color c) {
        JButton btn = new JButton(name);
        btn.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
        btn.setBackground(c);
        btn.setForeground(Color.WHITE);
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.addActionListener(actionListener);
        add(btn);
    }

    /**
     * Individual ActionListeners for when each button is pushed
     */

    private class OneListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFrame frame = new JFrame("Minesweeper");
            frame.setContentPane(new MinesweeperPanel(1, frame));
            HomePanel.displayFrame(frame, frameToClose);
        }
    }

    private class TwoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFrame frame = new JFrame("Minesweeper");
            frame.setContentPane(new MinesweeperPanel(2, frame));
            HomePanel.displayFrame(frame, frameToClose);
        }
    }

    private class ThreeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFrame frame = new JFrame("Minesweeper");
            frame.setContentPane(new MinesweeperPanel(3, frame));
            HomePanel.displayFrame(frame, frameToClose);
        }

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


