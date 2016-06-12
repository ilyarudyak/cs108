package lesson2;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ilyarudyak on 6/12/16.
 */
public class ImageLabel {

    public static void main(String[] argv) {
        SwingUtilities.invokeLater( () -> new ImageLabel().startup() );
    }

    public void startup() {

//         Icon icon = new ImageIcon("src/main/resources/publicdomain_vanGogh.jpeg");

        // Icon icon = new ImageIcon("/tmp/publicdomain_vanGogh.jpeg");

        // Icon icon = new ImageIcon(new URL("http://www.host.com/image.jpg")); // must catch exception

        // Icon icon = new ImageIcon(getGreensmiliesURL());

//         Icon icon = new ImageIcon(getClass().getResource("publicdomain_vanGogh.jpeg"));

         Icon icon = new ImageIcon(ClassLoader.getSystemResource("publicdomain_vanGogh.jpeg"));

//        Icon icon = new BallIcon(64, Color.YELLOW);

        JLabel greeting = new JLabel(icon, JLabel.CENTER);

        JFrame frame = new JFrame("Labels with Images");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(greeting);
        frame.setSize(540, 400);
        frame.setVisible(true);
    }
}
