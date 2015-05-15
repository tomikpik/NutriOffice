package cz.fel.ds.gui.foodDialog;

import cz.fel.ds.database.model.Food;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Tom on 15. 5. 2015.
 */
public class FoodDialog {

    public void showFoodFialog(Food food){
        try{
            String title = (food==null)?"New food":"Food";

            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("food_dialog.fxml"));
            Parent root=loader.load();
            stage.setTitle(title);
            stage.setResizable(false);
            stage.setScene(new Scene(root,300,150));
            stage.show();
            FoodDialogController c = loader.getController();
            c.setFood(food);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
