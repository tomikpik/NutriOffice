package cz.fel.ds.gui.patientDialog;

import cz.fel.ds.database.model.Patient;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Tom on 14. 5. 2015.
 */
public class PatientDialog {
    private Patient model;
    private String title;

    public PatientDialog(Patient model, boolean newPacient) {
        this.model=model;
        title=(newPacient)?"New Patient":"Patient detail";
    }

    public void start() throws IOException {

        Stage stage = new Stage();
        //Parent root = FXMLLoader.load(getClass().getResource("layout/patient_dialog.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("patient_dialog.fxml"));

        Parent root=loader.load();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(new Scene(root,433,433));
        stage.show();
        PatientDialogController controller = loader.getController();
        controller.loadData(model);

    }

}