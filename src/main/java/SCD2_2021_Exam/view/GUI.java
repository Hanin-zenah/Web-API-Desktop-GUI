package SCD2_2021_Exam.view;

import javax.swing.*;

/**
 * The main window to interact with the application. This acts as the "homepage".
 * */
public class GUI extends DefaultView {

    /** The button to list available characters in the-one API. */
    private JButton availableCharacters;

    /** The button to list available quotes from the-one API. */
    private JButton availableQuotes;

    /** The button to send a report to the pastebin API. */
    private JButton sendReport;

    /**
     * Constructs a GUI object.
     */
    public GUI() {
        super("SOFT3202: Exam work");
        initialize();
    }

    /**
     * Initializes all the buttons.
     */
    @Override
    protected void initialize() {
        availableCharacters = addButton("List Available Characters", 150, 90, 250, 60);
        availableQuotes = addButton("List Character's Available Quotes", 150, 160, 250, 60);
        sendReport = addButton("Send Character Report", 150, 230, 250, 60);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public JButton getAvailableCharactersButton() {
        return availableCharacters;
    }

    public JButton getAvailableQuotesButton() {
        return availableQuotes;
    }

    public JButton getSendReportButton() {
        return sendReport;
    }
}
