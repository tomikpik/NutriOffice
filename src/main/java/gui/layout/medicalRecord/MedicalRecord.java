package gui.layout.medicalRecord;

import database.model.Patient;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Tom on 15. 5. 2015.
 */
public class MedicalRecord {

    public void show(Patient p){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("medical_record_dialog.fxml"));

            Parent root = loader.load();
            stage.setTitle("Medical records");
            stage.setResizable(false);
            stage.setScene(new Scene(root, 700,600));
            stage.show();
            MedicalRecordController controller = loader.getController();
            controller.loadData(p);
        }catch (IOException e){
            System.out.println("oops");
        }
    }
}
