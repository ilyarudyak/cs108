package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by ilyarudyak on 05/03/15.
 */
public class FormPanel extends JPanel{

    private static final String[] populationItems =
            new String[] { "larger than", "smaller than or equal to" };
    private static final String[] matchTypeItems =
            new String[] { "exact match", "partial match" };


    private JButton addButton;
    private JButton searchButton;
    private JComboBox<String> populationSearchOptions;
    private JComboBox<String> matchTypeSearchOptions;

    public FormPanel() {

        // set preferred width
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);

        addButton = new JButton("Add");
        searchButton = new JButton("Search");
        populationSearchOptions = new JComboBox<>(populationItems);
        matchTypeSearchOptions = new JComboBox<>(matchTypeItems);

        // create and set titled border
        Border border = BorderFactory.createTitledBorder("Search Options");
        JPanel searchOptionsPanel = new JPanel();
        searchOptionsPanel.setLayout(new BorderLayout());
        searchOptionsPanel.add(populationSearchOptions, BorderLayout.NORTH);
        searchOptionsPanel.add(matchTypeSearchOptions, BorderLayout.SOUTH);
        searchOptionsPanel.setBorder(border);

        // set border for the entire panel
        Border innerBorder = BorderFactory.createTitledBorder("Work with database");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        add(addButton);
        add(searchButton);
        add(searchOptionsPanel);
    }
}















