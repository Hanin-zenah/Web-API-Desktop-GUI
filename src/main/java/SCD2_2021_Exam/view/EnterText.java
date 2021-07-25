package SCD2_2021_Exam.view;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple class to get some user input from the interface.
 * */
public class EnterText extends DefaultView {

    /** The enter button to submit input. */
    private JButton enter;

    /** A label to display any error messages. */
    private JLabel errorMessage;

    /** The label objects of the needed input. */
    private final List<JLabel> labels;

    /** The fields to allow user to enter input. */
    private final List<JTextField> fields;

    /** The name of the labels for the input. */
    private final List<String> labelNames;

    /**
     * Constructs an EnterText object.
     *
     * @param title The title of the window.
     * @param labelNames The list of label names of the required input. Must not be null.
     * @throws IllegalArgumentException If labelNames is null.
     */
    public EnterText(String title, List<String> labelNames) throws IllegalArgumentException {
        super(title);
        if(labelNames == null) {
            throw new IllegalArgumentException("Arguments can not be null");
        }
        this.labelNames = labelNames;
        this.labels = new ArrayList<>();
        this.fields = new ArrayList<>();
        initialize();
    }

    /**
     * Initializes the button, error message, fields, and labels objects.
     */
    @Override
    protected void initialize() {
        for(int i = 0; i < labelNames.size(); i++) {
            JLabel label = new JLabel(labelNames.get(i));
            label.setBounds(10, 20 + (i * 30), 100, 25);
            panel.add(label);
            labels.add(label);

            JTextField field = new JTextField();
            field.setBounds(120, 20 + (i * 30), 165, 25);
            panel.add(field);
            fields.add(field);
        }

        enter = addButton("Enter", 10, 30 + (labelNames.size() * 30), 100, 40);
        enter.setFocusable(false);

        errorMessage = new JLabel("");
        errorMessage.setBounds(10, 80 + (labelNames.size() * 30), 500, 25);
        panel.add(errorMessage);

        frame.setVisible(true);
    }

    public JButton getEnter() {
        return enter;
    }

    public JTextField getField(int index) {
        if(index < 0 || index >= fields.size()) {
            throw new IllegalArgumentException("Index is invalid");
        }
        return fields.get(index);
    }

    public JLabel getErrorMessage() {
        return errorMessage;
    }

    public JLabel getLabel(int index) {
        if(index < 0 || index >= labels.size()) {
            throw new IllegalArgumentException("Index is invalid");
        }
        return labels.get(index);
    }
}
