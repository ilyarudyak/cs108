package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ilyarudyak on 05/03/15.
 */
public class MainFrame extends JFrame{

    private InputTextPanel inputTextPanel;
    private FormPanel formPanel;
    private TablePanel tablePanel;

    public MainFrame() {
        super("Metropolis Viewer");

        inputTextPanel = new InputTextPanel();
        formPanel = new FormPanel();
        tablePanel = new TablePanel();

        add(inputTextPanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.WEST);
        add(tablePanel, BorderLayout.CENTER);

        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
