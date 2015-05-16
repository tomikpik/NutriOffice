package cz.fel.ds.gui.excerciseDialog;

import cz.fel.ds.database.model.Excercise;
import cz.fel.ds.database.model.Food;
import cz.fel.ds.gui.foodDialog.FoodDialogController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Tom on 16. 5. 2015.
 */
public class ExcerciseDialog {
    public void showExcerciseDialog(Excercise excercise){
        try{
            String title = (excercise==null)?"New excercise":"Excercise";
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("excercise_dialog.fxml"));
            Parent root=loader.load();
            stage.setTitle(title);
            stage.setResizable(false);
            stage.setScene(new Scene(root,300,150));
            stage.show();
            ExcerciseDialogController c = loader.getController();
            c.setExcercise(excercise);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
