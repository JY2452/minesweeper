package components;

import javax.swing.JButton;
import java.awt.Color;

/**
 * Abstract class representing a single square on the gameboard
 *
 * @author Jason Pak
 */

public abstract class Square extends JButton {

    public Square() {
        this.setOpaque(true);
        this.setEnabled(true);
        this.setBorderPainted(false);
        this.setBackground(Color.lightGray);
    }

    public abstract boolean getClicked();

    public abstract void displayImage();

}
