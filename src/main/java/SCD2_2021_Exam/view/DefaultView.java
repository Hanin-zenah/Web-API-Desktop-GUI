package SCD2_2021_Exam.view;

import javax.swing.*;
import java.awt.*;

/**
 * Abstract class that defines a default view window. Used to reduce
 * repetitive code in the view classes.
 */
public abstract class DefaultView {

    /** The window frame. */
    protected JFrame frame;

    /** The window panel; the container of all components. */
    protected JPanel panel;

    /**
     * Constructs a DefaultView object.
     *
     * @param title The title to be displayed for the window. Must not be null.
     * @throws IllegalArgumentException If title is null.
     */
    public DefaultView(String title) throws IllegalArgumentException {
        if(title == null) {
            throw new IllegalArgumentException("Title can not be null");
        }
        frame = new JFrame();
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 10, 600, 1000));
        panel.setLayout(null);
        frame.add(panel, BorderLayout.CENTER);
        frame.setTitle(title);
        frame.pack();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * Disposes the current frame.
     */
    public void disposeFrame() {
        frame.dispose();
    }

    /**
     * Creates and adds a JButton object to the panel.
     *
     * @param label The label of the button.
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @param width The width of the button.
     * @param height The height of the button.
     * @return A reference to the created JButton.
     */
    public JButton addButton(String label, int x, int y, int width, int height) {
        JButton button = new JButton(label);
        button.setBounds(x, y, width, height);
        panel.add(button);
        return button;
    }

    /**
     * Initializes other necessary components of the view. <br><br>
     * To be implemented by the subclasses.
     */
    protected abstract void initialize();
}
