package SCD2_2021_Exam.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A simple class to display some output to the user.
 * */
public class DisplayOutput extends DefaultView implements BlinkingScreen {

    /** The output to display. */
    private final String output;

    /**
     * Constructs a DisplayOutput object.
     *
     * @param title The title of the window.
     * @param output The output to be displayed. Must not be null.
     * @throws IllegalArgumentException If the given output is null.
     */
    public DisplayOutput(String title, String output) throws IllegalArgumentException {
        super(title);
        if(output == null) {
            throw new IllegalArgumentException("Arguments can not be null");
        }
        this.output = output;
        initialize();
    }

    /**
     * Initializes the area to display the text and a scroll panel.
     */
    @Override
    protected void initialize() {
        JTextArea textArea = new JTextArea(output);
        textArea.setEditable(false); //Don't allow the user to edit what's displayed
        JScrollPane scroll = new JScrollPane(textArea);
        frame.add(scroll);
        frame.setVisible(true);
    }

    /**
     * Makes the screen blink by changing colour from red to light-pink a couple of times
     */
    @Override
    public void blink() {
        JPanel blinkingScreen = new JPanel();
        blinkingScreen.setBorder(panel.getBorder());
        int delay = 500;

        Timer blinkTimer = new Timer(delay, new ActionListener() {
            private int count = 0;
            private boolean on = false;

            public void actionPerformed(ActionEvent e) {
                int maxTime = 2000;
                blinkingScreen.setBackground(on ? Color.RED : new Color(255, 203, 203));
                on = !on;
                count++;
                if (count * delay >= maxTime) {
                    blinkingScreen.setBackground(null);
                    blinkingScreen.setVisible(false);
                    frame.getContentPane().remove(blinkingScreen);
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        blinkTimer.start();
        frame.getContentPane().add(blinkingScreen);
    }
}
