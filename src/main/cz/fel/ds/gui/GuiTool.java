package cz.fel.ds.gui;

import javafx.scene.control.Control;
import javafx.stage.Stage;

/**
 * Created by Tom on 16. 5. 2015.
 */
public class GuiTool {
    public static void closeDialog(Control c) {
        Stage stage = (Stage) c.getScene().getWindow();
        stage.close();
    }
}
