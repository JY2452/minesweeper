package components;

import java.awt.*;
import javax.swing.*;

/**
 * Class representing a mine square tile in the game
 *
 * @author Jason Pak
 */

public class Mine extends Square {
    public Mine() {
        super();
    }

    public boolean getClicked() {
        return false;
    }

    /**
     * Displays the mine image on the mine tile
     */
    public void displayImage() {
        setEnabled(false);
        setBackground(Color.BLACK);
        ImageIcon a = new ImageIcon("assets/mine.png");
        int offset = getInsets().left;
        setIcon(resizeIcon(a, getWidth() - offset, getHeight() - offset));
        setDisabledIcon(resizeIcon(a, getWidth() - offset, getHeight() - offset));
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

