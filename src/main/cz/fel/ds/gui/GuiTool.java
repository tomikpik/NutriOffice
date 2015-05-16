package cz.fel.ds.gui;

import cz.fel.ds.gui.mainPage.MainPageController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Created by Tom on 16. 5. 2015.
 */
public class GuiTool extends Application {

    public void start(){
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainPage/main_page.fxml"));
        Parent root=loader.load();
        primaryStage.setTitle("NutriOffice");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root,800,600));
        primaryStage.show();
        MainPageController c = loader.getController();
        c.init();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });
    }

    public static void closeDialog(Control c) {
        Stage stage = (Stage) c.getScene().getWindow();
        stage.close();
    }
}
