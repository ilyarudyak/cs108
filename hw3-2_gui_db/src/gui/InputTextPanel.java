package gui;

import javax.swing.*;
import javax.swing.border.Border;

/**
 * Created by ilyarudyak on 05/03/15.
 */
public class InputTextPanel extends JPanel {

    private JLabel metropolisLabel;
    private JTextField metropolisTextField;

    private JLabel continentLabel;
    private JTextField continentTextField;

    private JLabel populationLabel;
    private JTextField populationTextField;

    public InputTextPanel() {

        // create components
        metropolisLabel = new JLabel("Metropolis: ");
        metropolisTextField = new JTextField(10);

        continentLabel = new JLabel("Continent: ");
        continentTextField = new JTextField(10);

        populationLabel = new JLabel("Population: ");
        populationTextField = new JTextField(10);


        // add component to panel layout
        add(metropolisLabel);
        add(metropolisTextField);

        add(continentLabel);
        add(continentTextField);

        add(populationLabel);
        add(populationTextField);

        // set border for the entire panel
        Border innerBorder = BorderFactory.createEtchedBorder();
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    }


}












