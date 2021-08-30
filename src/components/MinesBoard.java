package components;

import java.awt.*;
import javax.swing.*;

/**
 * Displays number of remaining mines
 *
 * @author Jason Pak
 */

public class MinesBoard extends JPanel {
    private JLabel tens, ones;
    private int count;

    public MinesBoard(int size) {
        count = size * size * 5 / 32;
        setLayout(new FlowLayout());
        setBackground(Color.black);

        // make tens digit
        tens = new JLabel("" + count % 100 / 10);
        makeLabel(tens);

        // make ones digit
        ones = new JLabel("" + count % 10);
        makeLabel(ones);
    }

    private void makeLabel(JLabel label) {
        label.setForeground(Color.red);
        label.setFont(new Font("Courier New", Font.BOLD, 25));
        add(label);
    }

    public void update(int x) {
        tens.setText("" + x % 100 / 10);
        ones.setText("" + x % 10);
    }
}




