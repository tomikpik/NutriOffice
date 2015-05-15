package cz.fel.ds.gui.mainPage;

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
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("../../../layout/main_page.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("main_page.fxml"));

        primaryStage.setTitle("NutriOffice");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root,800,600));
        primaryStage.show();
    }
}
