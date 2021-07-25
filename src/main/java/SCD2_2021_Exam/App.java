package SCD2_2021_Exam;

import SCD2_2021_Exam.controller.ThresholdController;
import SCD2_2021_Exam.model.*;
import SCD2_2021_Exam.model.database.*;
import SCD2_2021_Exam.view.EnterText;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class App {
    public static void main(String[] args) {
        if(args.length != 2) {
            System.out.println("Please provide exactly 2 arguments");
            return;
        }
        Database db = new DatabaseCache();
        ModelFacade model = new ModelFacadeImpl(args[0], args[1], db);
        SwingUtilities.invokeLater(() -> {
            EnterText getThreshold = new EnterText (
                    "Select Threshold Result Count",
                    new ArrayList<>(Collections.singletonList("Threshold value"))
            );
            new ThresholdController(model, getThreshold);
        });
    }
}
