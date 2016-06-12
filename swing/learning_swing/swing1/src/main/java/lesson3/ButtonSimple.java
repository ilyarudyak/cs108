package lesson3;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ilyarudyak on 6/12/16.
 */
public class ButtonSimple {

    public static void main(String[] argv) {
        SwingUtilities.invokeLater( () -> new ButtonSimple().startup() );
    }

    public void startup() {

        JButton button1 = new JButton("Button One");
        JButton button2 = new JButton("Button Two");
        JButton button3 = new JButton("Button Three");

//        button1.setPreferredSize(button3.getPreferredSize());
//        button2.setPreferredSize(button3.getPreferredSize());
//
//        button1.setMnemonic('o');
//        button1.setDisplayedMnemonicIndex(7);
//        button2.setMnemonic('t');
//        button2.setDisplayedMnemonicIndex(7);
//        button3.setMnemonic('h');

        JFrame frame = new JFrame("Pressing Matters");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getRootPane().setDefaultButton(button3);
        frame.getContentPane().setLayout(new FlowLayout());

        // add buttons to frame
        frame.getContentPane().add(button1);
        frame.getContentPane().add(button2);
        frame.getContentPane().add(button3);

        frame.setSize(180, 180);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}
