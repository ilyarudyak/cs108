package lesson2;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ilyarudyak on 6/12/16.
 */
public class LooksFeels {

    public void startup() {
        JTabbedPane tabp = new JTabbedPane();
        tabp.add("one", new JTextField("once", 16));
        tabp.add("two", new JTextField("twice", 16));

        JComboBox<String> combo = new JComboBox<String>(new String[] {"one", "two", "three"});
        JButton button = new JButton("do it");

        JScrollPane scroll = new JScrollPane(new JTextArea(UIManager.getLookAndFeel().toString(), 3, 16));

        JSlider slider = new JSlider();

        JFrame frame = new JFrame(UIManager.getLookAndFeel().getName());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 16, 16));
        frame.getContentPane().add(tabp);
        frame.getContentPane().add(combo);
        frame.getContentPane().add(button);
        frame.getContentPane().add(scroll);
        frame.getContentPane().add(slider);
        frame.setSize(240, 300);
        frame.setVisible(true);
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

        // print a list of this Java installation's supported LnFs
//        for (UIManager.LookAndFeelInfo lafi : UIManager.getInstalledLookAndFeels()) {
//            System.out.println(lafi);
//        }

        UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

        SwingUtilities.invokeLater( () -> new LooksFeels().startup() );
    }
}
