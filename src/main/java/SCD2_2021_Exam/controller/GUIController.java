package SCD2_2021_Exam.controller;

import SCD2_2021_Exam.model.ModelFacade;
import SCD2_2021_Exam.view.DisplayOutput;
import SCD2_2021_Exam.view.EnterText;
import SCD2_2021_Exam.view.GUI;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

/** GUI Controller to delegate and control all necessary user interactions with the main GUI ("homepage"). */
public class GUIController implements Controller {
    private final ModelFacade model;
    private final GUI gui;

    /**
     * Constructs a GUIController object.
     *
     * @param model The model to make the calls for. Must not be null.
     * @param gui The GUI object to get the user inputs and interactions from. Must not be null.
     * @throws IllegalArgumentException If any of the arguments is null.
     */
    public GUIController(ModelFacade model, GUI gui) {
        if(model == null || gui == null) {
            throw new IllegalArgumentException("Arguments can not be null");
        }
        this.model = model;
        this.gui = gui;
        initController();
    }

    /**
     * Initialises the controller by setting the appropriate action listeners to the buttons.
     */
    @Override
    public void initController() {
        gui.getAvailableCharactersButton().addActionListener(e -> availableCharacters());
        gui.getAvailableQuotesButton().addActionListener(e -> availableCharacterQuotes());
        gui.getSendReportButton().addActionListener(e -> sendCharacterReport());
    }

    /**
     * If the user presses the button to list the available characters, this spawns a new SwingWorker
     * thread to perform the API call to get all the available characters through the model.
     * After executing the request, displays the result to the user.
     */
    private void availableCharacters() {
        //spawn the worker thread and call the model
        SwingWorker<String, Void> worker = new SwingWorker<>() {
            @Override
            protected String doInBackground() throws Exception {
                return model.listAvailableCharacters();
            }
            @Override
            protected void done() {
                try {
                    String output = get();
                    //no need for a sub-controller here; no interaction from user
                    new DisplayOutput("Available Characters", output);
                } catch (InterruptedException | ExecutionException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        };
        worker.execute();
    }

    /**
     * If the user presses the button to list a character's available quotes, this creates a new
     * EnterText object to get user input, as well as its own AvailableQuotesController.
     */
    private void availableCharacterQuotes() {
        //create sub-view and sub-controller here
        EnterText enterText = new EnterText("List Character's Available Quotes",
                                                new ArrayList<>(Arrays.asList("Character ID")));
        new AvailableQuotesController(model, enterText);
    }

    /**
     * If the user presses the button to send a report, this creates a new
     * EnterText object to get user input, as well as its own SendReportController.
     */
    private void sendCharacterReport() {
        EnterText enterText = new EnterText("Send Character Report",
                                                new ArrayList<>(Arrays.asList("Character ID")));
        new SendReportController(model, enterText);
    }
}
