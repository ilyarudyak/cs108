package gui;

import javax.swing.*;

/**
 * Created by ilyarudyak on 05/03/15.
 */
public class App {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });

    }
}
