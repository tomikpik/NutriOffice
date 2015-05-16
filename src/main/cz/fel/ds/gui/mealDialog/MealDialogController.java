package cz.fel.ds.gui.mealDialog;

import cz.fel.ds.database.model.Exercise;
import cz.fel.ds.database.model.ExerciseToTrainingProgram;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Created by Tom on 14. 5. 2015.
 */
public class MealDialogController {
    @FXML
    private TableView<ExerciseToTrainingProgram> mealFoodsTable;
    @FXML
    private TableView<Exercise> foodTable;
    @FXML
    private TextField trainingProgramName;
    @FXML
    private TextField searchExercise;
    @FXML
    private TextField duration;

    @FXML
    public void exerciseSearch(ActionEvent event) {
        //send cz.fel.ds.database request and return matches to given string
    }

    @FXML
    public void addExercise(ActionEvent event) {
        //send cz.fel.ds.database request and return matches to given string
    }

    @FXML
    public void removeExercise(ActionEvent event) {
        //send cz.fel.ds.database request and return matches to given string
    }

    @FXML
    public void saveTrainingProgram(ActionEvent event) {
        //send cz.fel.ds.database request and return matches to given string
    }

    @FXML
    public void deleteTrainingProgram(ActionEvent event) {
        //send cz.fel.ds.database request and return matches to given string
    }

}
