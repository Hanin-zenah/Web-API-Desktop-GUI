package SCD2_2021_Exam.controller;

import SCD2_2021_Exam.model.ModelFacade;
import SCD2_2021_Exam.view.DisplayLink;
import SCD2_2021_Exam.view.EnterText;

import javax.swing.*;
import java.util.concurrent.ExecutionException;

public class SendReportController implements Controller {
    private ModelFacade model;
    private EnterText view;

    /**
     * Constructs a SendReportController object.
     *
     * @param model The model that contains the APIs. Must not be null.
     * @param view The view to get the user interaction from. Must not be null.
     * @throws IllegalArgumentException If any of the arguments is null.
     */
    public SendReportController(ModelFacade model, EnterText view) throws IllegalArgumentException {
        if (model == null || view == null) {
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
        view.getEnter().addActionListener(e -> sendReport());
    }

    private void sendReport() {
        String input = view.getField(0).getText();
        if(input.isEmpty() || input.isBlank()) {
            view.getErrorMessage().setText(
                view.getLabel(0).getText() + " can not be empty"
            );
            return;
        }
        //new thread worker to get link
        SwingWorker<String, Void> worker = new SwingWorker<>() {
            @Override
            protected String doInBackground() throws Exception {
                view.getEnter().setEnabled(false);
                String data = model.getCharacterInfo(input);
                String output;
                if(data == null || data.contains("success: false")) {
                    output = "Something went wrong";
                } else {
                    //add asterisk if number of quotes for character is more than threshold
                    String quotes = model.listAvailableQuotes(input);
                    if(model.getThreshold() != -1 && model.countQuotes(quotes) > model.getThreshold()) {
                        data = "* " + data;
                    }
                    output = model.sendReport(data);
                }
                return output;
            }
            @Override
            protected void done() {
                try {
                    //remove extra whitespace to be able to correctly open link in browser
                    String link = get().replace('\n', ' ').strip();
                    new DisplayLink("Sending report", link);
                    view.disposeFrame();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        };
        worker.execute();
    }
}