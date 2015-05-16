package cz.fel.ds.gui.mainPage;

import cz.fel.ds.gui.exerciseDialog.ExerciseDialogController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Tom on 14. 5. 2015.
 */
public class MainPage extends Application{

    public void start(){
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main_page.fxml"));
        Parent root=loader.load();
        primaryStage.setTitle("NutriOffice");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root,800,600));
        primaryStage.show();
        MainPageController c = loader.getController();
        c.init();
    }
}
