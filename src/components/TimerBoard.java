package components;

import java.awt.*;
import javax.swing.*;

/**
 * Displays the elapsed time
 *
 * @author Jason Pak
 */

public class TimerBoard extends JPanel {
    private JLabel hunds, tens, ones;
    private int count;

    public TimerBoard() {
        setLayout(new FlowLayout());
        setBackground(Color.black);

        // hundreds digit
        hunds = new JLabel("0");
        makeLabel(hunds);

        // tens digit
        tens = new JLabel("0");
        makeLabel(tens);

        // ones digit
        ones = new JLabel("0");
        makeLabel(ones);
    }

    private void makeLabel(JLabel label) {
        label.setForeground(Color.red);
        label.setFont(new Font("Courier New", Font.BOLD, 25));
        add(label);
    }

    public int getTime() {
        return count;
    }

    public void update() {
        count++;
        hunds.setText("" + count % 1000 / 100);
        tens.setText("" + count % 100 / 10);
        ones.setText("" + count % 10);
    }
}



