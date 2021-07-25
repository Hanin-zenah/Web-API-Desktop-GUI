package SCD2_2021_Exam.controller;

import SCD2_2021_Exam.model.ModelFacade;
import SCD2_2021_Exam.view.DisplayOutput;
import SCD2_2021_Exam.view.PromptCache;

import javax.swing.*;
import java.util.concurrent.ExecutionException;

public class PromptCacheController implements Controller {
    private final ModelFacade model;
    private final PromptCache view;
    private final String characterId;

    /**
     * Constructs a PromptCacheController object.
     *
     * @param model The model that contains the APIs and database. Must not be null.
     * @param view The view to get the user interaction from. Must not be null.
     * @param characterId The specified character ID to get the quotes for. Must not be null.
     * @throws IllegalArgumentException If any of the arguments is null.
     */
    public PromptCacheController(ModelFacade model, PromptCache view, String characterId) {
        if(model == null || view == null || characterId == null) {
            throw new IllegalArgumentException("Arguments can not be null");
        }
        this.model = model;
        this.view = view;
        this.characterId = characterId;
        initController();
    }

    /**
     * Initialises the controller by setting the appropriate action listeners to the buttons.
     */
    @Override
    public void initController() {
        view.getCacheButton().addActionListener(e -> getQuotes(true));
        view.getApiButton().addActionListener(e -> getQuotes(false));
    }

    /**
     * Spawns a new SwingWorker thread to perform the API or database call,
     * depending on the nutton pressed, to get all the available character's quotes through the model.
     * After executing the request, displays the result to the user by creating a new DisplayOutput object.<p>
     *
     * If the number of fetched quotes is more than the threshold set by the user, the displayed screen will blink for a couple of times
     */
    private void getQuotes(boolean cache) {
        SwingWorker<String, Void> worker = new SwingWorker<>() {
            @Override
            protected String doInBackground() throws Exception {
                view.getCacheButton().setEnabled(false);
                view.getApiButton().setEnabled(false);
                if(cache) {
                    return model.getCachedQuote(characterId);
                } else {
                    String result = model.listAvailableQuotes(characterId);
                    model.insertIntoDb(characterId, result);
                    return result;
                }
            }
            @Override
            protected void done() {
                try {
                    String output = get();
                    DisplayOutput display = new DisplayOutput( "Character's Available Quotes", output);
                    if(model.getThreshold() != -1 && model.countQuotes(output) > model.getThreshold()) {
                        display.blink();
                    }
                    view.disposeFrame();
                } catch (InterruptedException | ExecutionException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        };
        worker.execute();
    }
}
