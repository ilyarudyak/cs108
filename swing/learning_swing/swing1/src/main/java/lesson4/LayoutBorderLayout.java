package lesson4;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ilyarudyak on 6/12/16.
 */
public class LayoutBorderLayout {

    public static void main(String[] argv) {
        SwingUtilities.invokeLater( () -> new LayoutBorderLayout().startup() );
    }

    public void startup() {

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        southPanel.add(new JButton("South 1"));
        // southPanel.add(new JButton("South 2"));

        JPanel mainPanel = new JPanel(new BorderLayout(8, 8));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        // mainPanel.add(new JButton("East"), BorderLayout.EAST);
        mainPanel.add(new JButton("West"), BorderLayout.WEST);
        mainPanel.add(new JButton("Center"), BorderLayout.CENTER);
        // mainPanel.add(new JButton("North"), BorderLayout.NORTH);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        JFrame frame = new JFrame("BorderLayout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(mainPanel);
        frame.setSize(400, 200);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}
