package lesson2;

import javax.swing.*;

/**
 * Created by ilyarudyak on 6/12/16.
 */
public class HelloWorld {

    public static void main(String[] args) {

        SwingUtilities.invokeLater( () -> new HelloWorld().startup() );

    }

    public void startup() {
        JLabel greeting = new JLabel("Hello my friend!");

        // configure frame
        JFrame frame = new JFrame("Important message");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(greeting);
        frame.setSize(240, 160);
        frame.setVisible(true);
    }

}
