package gui;

import javax.swing.*;
import javax.swing.border.Border;

/**
 * Created by ilyarudyak on 05/03/15.
 */
public class TablePanel extends JTable {

    private JTable table;

    private static final String[] headings =
            new String[] {"Metropolis", "Continent", "Population"};

    private static final Object[][] data = new Object[][] {
            { "Mumbai","Asia",20400000 },
            { "New York","North America",21295000 },
            { "San Francisco","North America",5780000 }
    };

    public TablePanel() {
        table = new JTable(data, headings);
        add(new JScrollPane(table));

        // set border for the entire panel
        Border innerBorder = BorderFactory.createEtchedBorder();
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    }
}
