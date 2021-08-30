package components;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

/**
 * Represents the panel in which the game is played on
 *
 * @author Jason Pak
 */

public class GameBoard extends JPanel {
    private Square[][] board; // stores each tile on the gameboard
    private int[][] matrix;   // -1 if a mine, otherwise stores the # of neighboring mines
    private int[][] flags;  // location of flags
    private int mySize;
    private JFrame frameToClose;
    private String name;
    private int numFlags;
    private boolean gameOver = false;

    public GameBoard(int x, JFrame frameName) {
        mySize = x;
        setLayout(new GridLayout(mySize, mySize, 10, 10));
        board = new Square[mySize][mySize];
        matrix = new int[mySize][mySize];
        flags = new int[mySize][mySize];
        numFlags = x * x * 5 / 32;
        frameToClose = frameName;

        // randomly place mines on map
        placeMines(numFlags);

        // place appropriate tile at each square
        for (int r = 0; r < mySize; r++) {
            for (int c = 0; c < mySize; c++) {
                if (matrix[r][c] != -1) board[r][c] = new Normal(matrix[r][c]);
                else board[r][c] = new Mine();

                // add listeners to each tile
                board[r][c].addMouseListener(new Mouse(r, c));
                add(board[r][c]);
            }
        }
    }

    /**
     * Getter methods
     */
    public int getFlags() {
        return numFlags;
    }

    public boolean gameOver() {
        return gameOver;
    }

    public String getName() {
        return name;
    }

    /**
     * Allocates appropriate number of mines randomly across the board
     * and also updates bordering tiles to the appropriate count value
     *
     * @param mines number of mines to allocate
     */
    private void placeMines(int mines) {
        for (int k = 1; k <= mines; k++) {
            int r, c;

            // find a tile that isn't yet a mine
            do {
                r = (int) (Math.random() * mySize);
                c = (int) (Math.random() * mySize);
            } while (matrix[r][c] == -1);

            // if found, place a mine
            matrix[r][c] = -1;

            // increase count for tiles surrounding the mine
            for (int nr = Math.max(0, r-1); nr <= Math.min(mySize-1, r+1); nr++) {
                for (int nc = Math.max(0, c-1); nc <= Math.min(mySize-1, c+1); nc++) {
                    if (matrix[nr][nc] != -1)
                        matrix[nr][nc]++;
                }
            }
        }
    }

    /**
     * Recursive method to continuously open up empty tiles
     *
     * @param r row of current tile
     * @param r column of current tile
     */
    private void expandZero(int r, int c) {
        if (r < 0 || r > mySize - 1 || c < 0 || c > mySize - 1 || board[r][c].getClicked()) {
            return;
        }
        else if (matrix[r][c] > 0) {
            board[r][c].displayImage();
            return;
        // expand to neighbors if current tile is 0
        } else if (matrix[r][c] == 0) {
            board[r][c].displayImage();
            for (int nr = Math.max(0, r-1); nr <= Math.min(mySize-1, r+1); nr++) {
                for (int nc = Math.max(0, c-1); nc <= Math.min(mySize-1, c+1); nc++) {
                    if (r != nr || c != nc)
                        expandZero(nr, nc);
                }
            }
        }
    }

    /**
     * Checks gameboard on whether or not the user has won the game
     *
     * @return whether the user has won the game
     */
    public boolean isWinner() {
        // count how many tiles have been revealed
        int count = 0;
        for (int r = 0; r < mySize; r++)
            for (int c = 0; c < mySize; c++) {
                if (!board[r][c].isEnabled())
                    count++;
            }

        // check whether there are any remaining tiles, if not, game has finished
        if (count == mySize * mySize - (mySize * mySize * 5 / 32)) {
            SoundEffect.CHEER.play();
            name = JOptionPane.showInputDialog("Congratulations! What is your name?");
            return true;
        }
        return false;
    }

    /**
     * Handles mouse events
     */
    public class Mouse extends MouseAdapter {
        private int myRow, myCol;

        public Mouse(int r, int c) {
            myRow = r;
            myCol = c;
        }

        public void mousePressed(MouseEvent e) {

            // on a right click
            if (SwingUtilities.isRightMouseButton(e)) {
                // if no flag on square, display a flag
                if (flags[myRow][myCol] == 0) {
                    ImageIcon a = new ImageIcon("assets/flag.png");
                    numFlags--;
                    int offset = board[myRow][myCol].getInsets().left;
                    board[myRow][myCol].setIcon(resizeIcon(a, board[myRow][myCol].getWidth() - offset, board[myRow][myCol].getHeight() - offset));
                    flags[myRow][myCol] = 1;
                    // if a flag already exists at the square, remove the flag
                } else if (flags[myRow][myCol] == 1) {
                    numFlags++;
                    board[myRow][myCol].setIcon(null);
                    flags[myRow][myCol] = 0;
                }
            // on a left click
            } else if (SwingUtilities.isLeftMouseButton(e)) {
                // if mine, end the game
                if (matrix[myRow][myCol] == -1) {
                    for (int r = 0; r < matrix.length; r++)
                        for (int c = 0; c < matrix[0].length; c++) {
                            if (matrix[r][c] == -1) {
                                board[r][c].displayImage();
                            }

                            board[r][c].setEnabled(false);
                            board[myRow][myCol].setBackground(Color.RED);
                        }

                    gameOver = true;
                    JOptionPane.showMessageDialog(null, "Sorry, Try Again.");
                    SoundEffect.EXPLODE.play();
                    JFrame frame = new JFrame("Minesweeper");
                    frame.setContentPane(new LevelPanel(frame));
                    HomePanel.displayFrame(frame, frameToClose);

                // if a normal tile, continue the game
                } else {
                    SoundEffect.DING.play();

                    if (matrix[myRow][myCol] == 0)
                        expandZero(myRow, myCol);
                    else
                        board[myRow][myCol].displayImage();
                }
            }
        }
    }

    /**
     * Resizes a given image into a new width and height
     *
     * @param icon          ImageIcon to resize
     * @param resizedWidth  new width
     * @param resizedHeight new height
     */
    private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }





}







