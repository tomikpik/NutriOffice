package cz.fel.ds.gui.medicalRecord;

import cz.fel.ds.database.model.MedicalRecord;
import cz.fel.ds.database.model.Patient;
import cz.fel.ds.database.model.TrainingProgram;
import cz.fel.ds.gui.DialogFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Created by Tom on 15. 5. 2015.
 */
public class MedicalRecordController {

    private Patient p;

    @FXML
    private DatePicker date;
    @FXML
    private TextField height;
    @FXML
    private TextField weight;
    @FXML
    private TextField fatPercentage;
    @FXML
    private TextField waistPerimeter;
    @FXML
    private TextField hipPerimeter;
    @FXML
    private TextField chestPerimeter;
    @FXML
    private TableView<MedicalRecord> medicalRecordTable;
    @FXML
    private Button delete;

    @FXML
    public void addMedicalRecord(ActionEvent event){
        System.out.println("add");
    }


    public void init(Patient p){
        this.p=p;
        date.getValue();

        System.out.println("data loaded");

    }



}
