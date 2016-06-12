package lesson4;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ilyarudyak on 6/12/16.
 */
public class LayoutFlowLayout {

    public static void main(String[] argv) {
        SwingUtilities.invokeLater( () -> new LayoutFlowLayout().startup() );
    }

    public void startup() {

        JButton tallerButton = new JButton("Three");
        tallerButton.setFont(new Font("sansserif", Font.PLAIN, 48));

        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        mainPanel.add(new JButton("One"));
        mainPanel.add(new JButton("Two"));
        mainPanel.add(tallerButton);
        mainPanel.add(new JButton("Four"));
        mainPanel.add(new JButton("Five"));
        mainPanel.add(new JButton("Six"));
        mainPanel.add(new JButton("Seven"));
        mainPanel.add(new JButton("Eight"));

        JFrame frame = new JFrame("FlowLayout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(mainPanel);
        frame.setSize(400, 200);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}
