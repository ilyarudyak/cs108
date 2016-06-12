package lesson5;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ilyarudyak on 6/12/16.
 */
public class ScrollPane {

    public static void main(String[] argv) {
        SwingUtilities.invokeLater( () -> new ScrollPane().startup() );
    }

    public JPanel createButtonPanel(int count) {
        JPanel panel = new JPanel(new GridLayout(0, 1, 0, 8)); // single-column GridLayout
        for (int j=1; j <= count; j+=1) {
            panel.add(new JButton("button"+j));
        }
        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        return panel;
    }

    public void startup() {

        JPanel buttonPanel = createButtonPanel(15);

        // create scrollPane
        JScrollPane scrollPane = new JScrollPane(buttonPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // corner button
//        JButton cornerButton = new JButton(new BallIcon(8, Color.ORANGE));
//        cornerButton.addActionListener( e -> System.out.println("corner!"));
//        scrollPane.setCorner(JScrollPane.LOWER_RIGHT_CORNER, cornerButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JFrame frame = new JFrame("ScrollPane");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(mainPanel);
        frame.setSize(200, 300);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}
