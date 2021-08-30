package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * JPanel for displaying the actual Minesweeper Game
 *
 * @author Jason Pak
 */

public class MinesweeperPanel extends JPanel {
    private MinesBoard minesboard;
    private TimerBoard timerboard;
    private Timer timer;
    private int level;
    private GameBoard gameboard;
    private JFrame frameToClose;

    public MinesweeperPanel(int lvl, JFrame frameName) {
        level = lvl;
        frameName.setVisible(false);
        frameToClose = frameName;
        setLayout(new BorderLayout());

        JPanel subPanel = new JPanel();
        subPanel.setLayout(new BorderLayout());
        minesboard = new MinesBoard(level * 8);
        subPanel.add(minesboard, BorderLayout.WEST);

        JButton resetBtn = new JButton("MINESWEEPER (Click to Restart)");
        makeButton(resetBtn, new ResetListener(), Color.gray);
        subPanel.add(resetBtn, BorderLayout.CENTER);

        timerboard = new TimerBoard();
        subPanel.add(timerboard, BorderLayout.EAST);
        add(subPanel, BorderLayout.NORTH);

        gameboard = new GameBoard(level * 8, frameToClose);
        add(gameboard, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new GridLayout(1, 2));
        add(bottom, BorderLayout.SOUTH);

        JButton quitBtn = new JButton("Quit");
        makeButton(quitBtn, new QuitListener(), Color.BLACK);

        JButton backBtn = new JButton("Back");
        makeButton(backBtn, new BackListener(), Color.gray);

        bottom.add(backBtn);
        bottom.add(quitBtn);

        timer = new Timer(1000, new Listener());
        timer.start();
    }

    /**
     * Creates a navigation button
     *
     * @param btn            the button to display on the button
     * @param actionListener an ActionListener for when the button gets clicked
     * @param c              background Color of button
     */
    private void makeButton(JButton btn, ActionListener actionListener, Color c) {
        btn.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
        btn.setBackground(c);
        btn.setForeground(Color.WHITE);
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.addActionListener(actionListener);
    }

    public int getLevel() {
        return level;
    }

    public void highScore() {
        String name = gameboard.getName();
        int time = timerboard.getTime();

        try {
            FileWriter fw = new FileWriter("src/scores/lvl" + getLevel() + ".txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("\n" + time + " " + name);
            bw.close();
        } catch (IOException e) {
            System.out.println("failed to store high score");
            System.exit(0);
        }
    }

    private class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (gameboard.gameOver()) timer.stop();
            else {
                timerboard.update();
                minesboard.update(gameboard.getFlags());
            }

            if (gameboard.isWinner()) {
                timer.stop();
                highScore();
            }
        }
    }

    /**
     * Individual ActionListeners for when each button is pushed
     */

    private class ResetListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFrame frame = new JFrame("Minesweeper");
            frame.setContentPane(new MinesweeperPanel(level, frame));
            HomePanel.displayFrame(frame, frameToClose);
        }
    }

    private class QuitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    /**
     * ActionListener for when back button is pushed
     */
    private class BackListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFrame frame = new JFrame("Minesweeper");
            frame.setContentPane(new LevelPanel(frame));
            HomePanel.displayFrame(frame, frameToClose);
        }
    }
}



