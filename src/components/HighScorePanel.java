package components;

import javax.swing.*;
import java.awt.*;
import java.io.*;      //the File class
import java.util.*;    //the Scanner class
import java.awt.event.*;
import javax.swing.ImageIcon;

/**
 * High score JPanel to display fastest solving times
 *
 * @author Jason Pak
 */

public class HighScorePanel extends JPanel {
    private JFrame frameToClose;

    public HighScorePanel(JFrame frameName) {
        setLayout(new GridLayout(2, 1));
        setBackground(Color.BLACK);
        frameToClose = frameName;

        // create panel to display high scores
        JPanel scores = new JPanel(new GridLayout(7, 1));
        scores.setBackground(Color.BLACK);

        // display title of panel
        ImageIcon img = new ImageIcon(new ImageIcon("assets/highscore.gif").getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT));
        JLabel title = new JLabel(img);
        title.setDisabledIcon(new ImageIcon("assets/highscore.gif"));
        title.setBackground(Color.BLACK);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title);

        // compute and display highest score for each level
        displayWinner(1, scores, Color.RED);
        displayWinner(2, scores, Color.ORANGE);
        displayWinner(3, scores, Color.GREEN);
        add(scores);

        // create back button
        JButton back = new JButton("Back");
        back.setFont(new Font("Arial", Font.BOLD, 25));
        back.setBorderPainted(false);
        back.setForeground(Color.WHITE);
        back.setOpaque(true);
        back.setBackground(Color.darkGray);
        back.addActionListener(new BackListener());
        scores.add(back);
    }

    /**
     * Computes the high score for a given level of the Minesweeper game and
     * displays the winner onto the screen
     *
     * @param lvl   difficulty level to display the high score for
     * @param panel JPanel to display the winner onto
     * @param c     text Color
     */
    private void displayWinner(int lvl, JPanel panel, Color c) {
        // create label to display level
        JLabel levelLabel = new JLabel("LVL " + lvl);
        levelLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 23));
        levelLabel.setForeground(c);
        levelLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(levelLabel);

        // create label to display winner
        JLabel winnerLabel = new JLabel(getHighScore(lvl));
        winnerLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        winnerLabel.setForeground(Color.WHITE);
        winnerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(winnerLabel);
    }

    /**
     * Computes the fastest solving time for a given level of the Minesweeper game
     *
     * @param lvl difficulty level to obtain the high score for
     * @return string displaying the name and time of the winner
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static String getHighScore(int lvl) {
        Scanner infile = null;
        try {
            // try to read the text file storing high scores
            infile = new Scanner(new File("src/scores/lvl" + lvl + ".txt"));
        } catch (FileNotFoundException e) {
            try {
                // if not found, create the file before proceeding
                FileWriter fw = new FileWriter("src/scores/lvl" + lvl + ".txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("999 No Record Set");
                bw.close();
                infile = new Scanner(new File("src/scores/lvl" + lvl + ".txt"));
            } catch (IOException f) {
                System.out.println(f);
                System.exit(0);
            }
        }

        // look through text file data to compute highest score (best time)
        int winnerTime = 1000;
        String winnerName = "s";
        while (infile.hasNext()) {
            String s = infile.nextLine();
            int seconds = Integer.parseInt(s.substring(0, s.indexOf(" ")));
            String name = s.substring(s.indexOf(" ") + 1);
            if (seconds < winnerTime) {
                winnerTime = seconds;
                winnerName = name;
            }
        }
        infile.close();
        return winnerName + " - " + winnerTime + " seconds";
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



