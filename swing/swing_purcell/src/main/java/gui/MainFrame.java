package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ilyarudyak on 6/13/16.
 */
public class MainFrame extends JFrame {

    private TextPanel textPanel;
    private Toolbar toolbar;
    private FormPanel formPanel;

    public MainFrame() {
        super("Hello my friend!");

        setLayout(new BorderLayout());

        textPanel = new TextPanel();
        toolbar = new Toolbar();
        formPanel = new FormPanel();

        toolbar.setStringListener( (text) -> textPanel.appendText(text) );
        formPanel.setFormListener( (e) -> textPanel.appendText(e.getName() + ": " +
                e.getOccupation() + " " + e.getAgeCategory().getId() + " " +
                e.getEmploymentCategory() + "\n") );

        add(textPanel, BorderLayout.CENTER);
        add(toolbar, BorderLayout.NORTH);
        add(formPanel, BorderLayout.WEST);

        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


}

















