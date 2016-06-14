package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ilyarudyak on 6/14/16.
 */
public class FormPanel extends JPanel {

    public FormPanel() {
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);
    }
}
