package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

/**
 * Created by ilyarudyak on 6/13/16.
 */
public class MainFrame extends JFrame {

    private TextPanel textPanel;
    private Toolbar toolbar;
    private FormPanel formPanel;

    public MainFrame() {
        super("Hello my friend!");

        setJMenuBar(createMenuBar());

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

    private JMenuBar createMenuBar() {

        // create menu bar
        JMenuBar menuBar = new JMenuBar();

        // create file menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem exportDataItem = new JMenuItem("Export Data...");
        JMenuItem importDataItem = new JMenuItem("Import Data...");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(exportDataItem);
        fileMenu.add(importDataItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        fileMenu.setMnemonic(KeyEvent.VK_F);
        exitItem.setMnemonic(KeyEvent.VK_X);

        exitItem.addActionListener( (e) -> System.exit(0) );
        
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
                ActionEvent.CTRL_MASK));

        // create window menu with submenu
        JMenu windowMenu = new JMenu("Window");
        JMenu showMenu = new JMenu("Show");
        JMenuItem prefsItem = new JMenuItem("Preferences...");

        final JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");
        showFormItem.setSelected(true);
        showFormItem.addActionListener( (e) -> formPanel.setVisible(showFormItem.isSelected()) );
        showMenu.add(showFormItem);

        windowMenu.add(showMenu);
        windowMenu.add(prefsItem);

        menuBar.add(fileMenu);
        menuBar.add(windowMenu);



        return menuBar;
    }


}

















