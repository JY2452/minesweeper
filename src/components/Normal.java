package components;

import java.awt.*;
import javax.swing.*;

/**
 * Class representing a normal square tile in the game
 *
 * @author Jason Pak
 */

public class Normal extends Square {
    private int number;
    private boolean clicked = false;

    public Normal(int n) {
        super();
        number = n;
    }

    public boolean getClicked() {
        return clicked;
    }

    /**
     * Displays the appropriate image on the normal square tile
     *
     */
    public void displayImage() {
        // if not yet clicked
        if (!this.clicked) {
            // disable button and display appropriate number tile
            this.clicked = true;
            setEnabled(false);
            setBackground(Color.gray);
            ImageIcon a = new ImageIcon("assets/" + number + ".png");
            int offset = getInsets().left;
            setIcon(resizeIcon(a, getWidth() - offset, getHeight() - offset));
            setDisabledIcon(resizeIcon(a, getWidth() - offset, getHeight() - offset));
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


