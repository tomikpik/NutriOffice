package cz.fel.ds.gui.patientDialog;

import cz.fel.ds.database.model.Patient;
import cz.fel.ds.gui.DialogFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by Tom on 14. 5. 2015.
 */
public class PatientDialogController {
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private DatePicker birthdate;
    @FXML
    private DatePicker dietStarted;

    private Patient p;

    @FXML
    public void test(ActionEvent event) {
        LocalDate localDate = birthdate.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        System.out.println(localDate + "\n" + instant + "\n" + date);
    }

    public void loadData(Patient p){
        this.p=p;
        firstName.setText(p.getFirstName());
        lastName.setText(p.getLastName());

    }

    @FXML
    public void showMedicalRecords(ActionEvent event){
        System.out.println("show mr");
        new DialogFactory().showMedicalRecordDialog(p);
    }

}
