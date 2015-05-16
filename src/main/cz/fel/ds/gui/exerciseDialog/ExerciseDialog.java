package cz.fel.ds.gui.exerciseDialog;

import cz.fel.ds.database.model.Exercise;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Tom on 16. 5. 2015.
 */
public class ExerciseDialog {
    public void showExerciseDialog(Exercise exercise){
        try{
            String title = (exercise==null)?"New excercise":"Excercise";
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("excercise_dialog.fxml"));
            Parent root=loader.load();
            stage.setTitle(title);
            stage.setResizable(false);
            stage.setScene(new Scene(root,300,150));
            stage.show();
            ExerciseDialogController c = loader.getController();
            c.setExcercise(exercise);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
