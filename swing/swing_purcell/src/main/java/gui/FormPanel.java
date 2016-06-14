package gui;

import gui.events.FormEvent;
import gui.listeners.FormListener;
import model.AgeCategory;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by ilyarudyak on 6/14/16.
 */
public class FormPanel extends JPanel {

    private JLabel nameLabel;
    private JLabel occupationLabel;
    private JTextField nameField;
    private JTextField occupationField;
    private JButton okBtn;
    private JList<AgeCategory> ageList;
    private JComboBox empCombo;
    private JCheckBox citizenCheck;
    private JTextField taxField;
    private JLabel taxLabel;

    private JRadioButton maleRadio;
    private JRadioButton femaleRadio;
    private ButtonGroup genderGroup;

    private FormListener formListener;

    public FormPanel() {

        setupDims();
        setupBorder();

        setupName();
        setupOccupation();
        setupOkButton();
        setupOtherFields();

        layoutComponents();
    }
    public void setFormListener(FormListener listener) {
        this.formListener = listener;
    }

    // helper methods
    private void setupName() {
        nameLabel = new JLabel("Name: ");
        nameField = new JTextField(10);

        nameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
        nameLabel.setLabelFor(nameField);
    }
    private void setupOccupation() {
        occupationLabel = new JLabel("Occupation: ");
        occupationField = new JTextField(10);
    }
    private void setupOkButton() {
        okBtn = new JButton("OK");
        okBtn.setMnemonic(KeyEvent.VK_O);

        okBtn.addActionListener( (e) -> {
            String name = nameField.getText();
            String occupation = occupationField.getText();
            AgeCategory ageCat = ageList.getSelectedValue();
            String empCat = (String) empCombo.getSelectedItem();
            String taxId = taxField.getText();
            boolean usCitizen = citizenCheck.isSelected();
            String gender = genderGroup.getSelection().getActionCommand();

            FormEvent ev = new FormEvent(this, name, occupation, ageCat,
                    empCat, taxId, usCitizen, gender);

            if (formListener != null) {
                formListener.formEventOccurred(ev);
            }
        });
    }
    private void setupOtherFields() {
        ageList = new JList<>();
        empCombo = new JComboBox();
        citizenCheck = new JCheckBox();
        taxField = new JTextField(10);
        taxLabel = new JLabel("Tax ID: ");
        maleRadio = new JRadioButton("male");
        femaleRadio = new JRadioButton("female");

        maleRadio.setActionCommand("male");
        femaleRadio.setActionCommand("female");

        genderGroup = new ButtonGroup();

        maleRadio.setSelected(true);

        // Set up gender radios
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        // Set up tax ID
        taxLabel.setEnabled(false);
        taxField.setEnabled(false);

        // Set up list box
        DefaultListModel ageModel = new DefaultListModel();
        ageModel.addElement(new AgeCategory(0, "Under 18"));
        ageModel.addElement(new AgeCategory(1, "18 to 65"));
        ageModel.addElement(new AgeCategory(2, "65 or over"));
        ageList.setModel(ageModel);

        ageList.setPreferredSize(new Dimension(110, 70));
        ageList.setBorder(BorderFactory.createEtchedBorder());
        ageList.setSelectedIndex(1);

        // Set up combo box.
        DefaultComboBoxModel empModel = new DefaultComboBoxModel();
        empModel.addElement("employed");
        empModel.addElement("self-employed");
        empModel.addElement("unemployed");
        empCombo.setModel(empModel);
        empCombo.setSelectedIndex(0);
        empCombo.setEditable(true);
    }
    private void setupDims() {
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);
    }
    private void setupBorder() {
        Border innerBorder = BorderFactory.createTitledBorder("Add Person");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    }
    private void layoutComponents() {

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        // ////////// First row ///////////////////////////////////

        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(nameLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(nameField, gc);

        // //////////Second row ///////////////////////////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        add(occupationLabel, gc);

        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(occupationField, gc);

        // //////////Next row ///////////////////////////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Age: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(ageList, gc);

        // //////////Next row ///////////////////////////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Employment: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(empCombo, gc);

        // //////////Next row ///////////////////////////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("US Citizen: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(citizenCheck, gc);

        // //////////Next row ///////////////////////////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(taxLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(taxField, gc);

        // //////////Next row ///////////////////////////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Gender: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(maleRadio, gc);

        // //////////Next row ///////////////////////////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(femaleRadio, gc);

        // //////////Next row ///////////////////////////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 2.0;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(okBtn, gc);
    }

}
































