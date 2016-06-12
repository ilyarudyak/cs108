package lesson3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.ExecutionException;

/**
 * Created by ilyarudyak on 6/12/16.
 */
public class ButtonReaction {

    private JLabel statusLabel = new JLabel(" status ", JLabel.CENTER);

    public static void main(String[] argv) {
        SwingUtilities.invokeLater( () -> new ButtonReaction().startup() );
    }

    public void startup() {
        statusLabel.setFont(new Font("sansserif", Font.PLAIN, 18));
        statusLabel.setBorder(BorderFactory.createEtchedBorder());
        statusLabel.setPreferredSize(statusLabel.getPreferredSize());

        JButton buttonA = new JButton("Button A");
        buttonA.addActionListener( e -> System.out.println(e) );
        buttonA.addActionListener( e -> System.out.println("2nd listener") );

        JButton buttonB = new JButton("Button B");
        buttonB.addActionListener( e -> handleB(e) );

        // setup frame
        JFrame frame = new JFrame("Reactions");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());
        // add buttons and status label
        frame.getContentPane().add(buttonA);
        frame.getContentPane().add(statusLabel);
        frame.getContentPane().add(buttonB);

        frame.setSize(400, 180);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public void handleB1(ActionEvent e) {
        if ((e.getModifiers() & ActionEvent.CTRL_MASK) == ActionEvent.CTRL_MASK) {
            statusLabel.setForeground(Color.BLACK);
        } else {
            statusLabel.setForeground(Color.RED);
        }
    }

    public void handleB(ActionEvent e) {
        // This method will be called on the EDT when buttonB is pressed.
        statusLabel.setText("...");
        new SwingWorker<String, Void>() {
            @Override protected String doInBackground() {
                // will be called on another thread (not EDT)
                return "Go " + numberCrunch();
            }
            @Override protected void done() {
                // will called on the EDT again
                try {
                    String result = get();
                    statusLabel.setText(String.valueOf(result));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // restore interrupted status
                } catch (ExecutionException e) {
                    throw new RuntimeException(e.getCause());
                }
            }
        }.execute();
    }

    public int numberCrunch() {
        // sleep for 5 seconds to simulate a lengthy computation
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // restore interrupted status
        }
        return 42;
    }
}
