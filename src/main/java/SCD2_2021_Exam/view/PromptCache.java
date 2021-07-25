package SCD2_2021_Exam.view;

import javax.swing.*;
import java.awt.*;

/**
 * A simple class to prompt the user to choose between cache or API.
 * */
public class PromptCache extends DefaultView {

    /** The button to choose API */
    private JButton api;

    /** The button to choose cache */
    private JButton cache;

    /**
     * Constructs a PromptCache object.
     */
    public PromptCache() {
        super("Character's Available Quotes");
        initialize();
    }

    /**
     * Initializes the two buttons, adds the prompt question.
     */
    @Override
    protected void initialize() {
        JTextField f = new JTextField("Would you like to use the cached version or grab a fresh copy from the API?");
        f.setEditable(false);
        f.setBounds(10, 140, 600, 25);
        f.setBackground(null);
        f.setBorder(null);
        f.setDisabledTextColor(Color.black);
        panel.add(f);

        cache = addButton("Cache", 130, 190, 100, 60);
        api = addButton("API", 240, 190, 100, 60);

        frame.setVisible(true);
    }

    public JButton getApiButton() {
        return api;
    }

    public JButton getCacheButton() {
        return cache;
    }
}
