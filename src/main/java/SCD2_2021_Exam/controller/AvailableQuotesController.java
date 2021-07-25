package SCD2_2021_Exam.controller;

import SCD2_2021_Exam.model.ModelFacade;
import SCD2_2021_Exam.view.DisplayOutput;
import SCD2_2021_Exam.view.EnterText;
import SCD2_2021_Exam.view.PromptCache;

import javax.swing.*;
import java.util.concurrent.ExecutionException;

/** Controller to get the available quotes */
public class AvailableQuotesController implements Controller {
    private final ModelFacade model;
    private final EnterText view;

    /**
     * Constructs a new AvailableQuotesController object.
     *
     * @param model The model that contains the APIs. Must not be null.
     * @param view The view to get the user interaction from. Must not be null.
     * @throws IllegalArgumentException if any of the arguments is null.
     */
    public AvailableQuotesController(ModelFacade model, EnterText view) throws IllegalArgumentException {
        if(model == null || view == null) {
            throw new IllegalArgumentException("Arguments can not be null");
        }
        this.model = model;
        this.view = view;
        initController();
    }

    /**
     * Initialises the controller by setting the appropriate action listener to the enter button.
     */
    @Override
    public void initController() {
        view.getEnter().addActionListener(e -> availableQuotes());
    }

    private void availableQuotes() {
        String input = view.getField(0).getText();
        if(input.isEmpty() || input.isBlank()) {
            view.getErrorMessage().setText(
                    view.getLabel(0).getText() + " can not be empty"
            );
            return;
        }
        if(model.getCachedQuote(input) == null) {
            //row does not exist -> do not prompt the user
            //get quotes and display output normally
            SwingWorker<String, Void> worker = new SwingWorker<>() {
                @Override
                protected String doInBackground() throws Exception {
                    String output = model.listAvailableQuotes(input);
                    model.insertIntoDb(input, output);
                    return output;
                }
                @Override
                protected void done() {
                    try {
                        String output = get();
                        DisplayOutput display = new DisplayOutput( "Character's Available Quotes", output);
                        //only if the threshold is set
                        //if the number of quotes meets the threshold criteria: blink the screen
                        if(model.getThreshold() != -1 && model.countQuotes(output) > model.getThreshold()) {
                            display.blink();
                        }
                        view.disposeFrame();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            };
            worker.execute();
        } else {
            //prompt cache and new controller
            PromptCache prompt = new PromptCache();
            new PromptCacheController(model, prompt, input);
            view.disposeFrame();
        }
    }
}
