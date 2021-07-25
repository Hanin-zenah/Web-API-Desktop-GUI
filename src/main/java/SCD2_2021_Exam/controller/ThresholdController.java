package SCD2_2021_Exam.controller;

import SCD2_2021_Exam.model.ModelFacade;
import SCD2_2021_Exam.view.EnterText;
import SCD2_2021_Exam.view.GUI;

import javax.swing.*;
import java.awt.*;

public class ThresholdController implements Controller {
    private final ModelFacade model;
    private final EnterText view;

    /**
     * Constructs a ThresholdController object.
     *
     * @param model The model that contains the APIs and database. Must not be null.
     * @param view The view to get the user interaction from. Must not be null.
     * @throws IllegalArgumentException If any of the arguments is null.
     */
    public ThresholdController(ModelFacade model, EnterText view) throws IllegalArgumentException {
        if(model == null || view == null) {
            throw new IllegalArgumentException("Arguments can not be null");
        }
        this.model = model;
        this.view = view;

        initController();
    }

    /**
     * Initialises the controller by adding a helper tooltip and by
     * setting the appropriate action listener to the enter button.
     */
    @Override
    public void initController() {
        addHelp();
        view.getEnter().addActionListener(e -> getThreshold());
    }

    /**
     * Adds a tooltip help button with instructions and information about the threshold required.
     */
    private void addHelp() {
        JButton help = view.addButton("?", 400, 20, 20, 20);
        help.setForeground(new Color(0xFF1E62EA, true));
        help.setToolTipText("<html> - Threshold result count must be an integer between "
                + model.getLowerBound() + "-" + model.getUpperBound() + ". <p>" +
                "- Any time you search for a character with more quotes than the threshold you specify, the display will blink. <p>" +
                "- Any time you request a report on a character that meets this criteria, the report will start with *. </html>");
        ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);
    }

    private int getNumber(String input) {
        int num;
        try {
            num = Integer.parseInt(input);
        } catch(NumberFormatException e) {
            num = -1;
        }
        return num;
    }

    /**
     * Gets the threshold entered by user, launches the homepage if and only if the specified threshold is valid.
     * Otherwise will print appropriate error message.
     */
    private void getThreshold() {
        String input = view.getField(0).getText();
        if(input.isEmpty() || input.isBlank()) {
            view.getErrorMessage().setText(
                    view.getLabel(0).getText() + " can not be empty"
            );
            return;
        }
        //if -1 then input is not numeric or user inputted -1; either way invalid
        int thresholdValue = getNumber(input);
        if(thresholdValue >= model.getLowerBound() && thresholdValue <= model.getUpperBound()) {
            //set model's threshold count and open the main GUI (Homepage)
            model.setThreshold(thresholdValue);
            GUI gui = new GUI();
            new GUIController(model, gui);
            view.disposeFrame();
        } else {
            view.getErrorMessage().setText(
                    view.getLabel(0).getText() + " must be an integer between " +
                            model.getLowerBound() + " and " + model.getUpperBound()
            );
        }
    }
}
