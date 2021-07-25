package SCD2_2021_Exam.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * A simple class to display a clickable link to the user.
 * */
public class DisplayLink extends DefaultView {

    /** The link to open */
    private String link;

    /**
     * Constructs a DisplayLink object.
     *
     * @param title The title of the window.
     * @param link The link to be displayed. Must not be null.
     * @throws IllegalArgumentException If link is null.
     */
    public DisplayLink(String title, String link) throws IllegalArgumentException {
        super(title);
        if(link == null) {
            throw new IllegalArgumentException("Link can not be null");
        }
        this.link = link;
        initialize();
    }

    /**
     * Initializes the link to display and a clickable link.
     */
    @Override
    protected void initialize() {
        JTextField f = new JTextField();
        f.setEditable(false);
        f.setBounds(10, 20, 600, 25);
        f.setBackground(null); //this is the same as a JLabel
        f.setBorder(null);
        f.setDisabledTextColor(Color.black);
        f.setText(link);
        panel.add(f);

        JLabel label = new JLabel("open");
        label.setBounds(10, 50, 35, 25);
        label.setForeground(Color.BLUE.darker());
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));

        if(link.contains("dummyURL")) {
            link = "https://youtu.be/dQw4w9WgXcQ";
        }
        if(link.contains("https")) {
            panel.add(label);
        }

        String finalLink = link;
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(finalLink));
                } catch (IOException | URISyntaxException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        frame.setVisible(true);
    }
}
