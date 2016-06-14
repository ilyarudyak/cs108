import gui.MainFrame;

import javax.swing.*;

/**
 * Created by ilyarudyak on 6/13/16.
 */
public class App {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
            }
        });
    }

}
