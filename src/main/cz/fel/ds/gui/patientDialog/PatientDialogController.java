package cz.fel.ds.gui.patientDialog;

import cz.fel.ds.database.model.Patient;
import cz.fel.ds.gui.DialogFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by Tom on 14. 5. 2015.
 */
public class PatientDialogController {
    @FXML
    private Text id;
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
    @FXML
    private Button delete;

    private Patient p;

    public void test(ActionEvent event) {
        LocalDate localDate = birthdate.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        //Date date = Date.from(instant);
        //System.out.println(localDate + "\n" + instant + "\n" + date);
    }

    @FXML
    public void savePatient(ActionEvent event){

    }

    @FXML
    public void deletePatient(ActionEvent event){

    }


    @FXML
    public void showMedicalRecords(ActionEvent event){
        new DialogFactory().showMedicalRecordDialog(p);
    }

    public void init(Patient p){
        if(p!=null) {
            System.out.println(p);
            this.p = p;
            id.setText(String.valueOf(p.getPatientId()));
            firstName.setText(p.getFirstName());
            lastName.setText(p.getLastName());
            phone.setText(p.getPhone());
            email.setText(p.getEmail());

            birthdate.setValue(dateToLocalDate(p.getBirthdate()));
            dietStarted.setValue(dateToLocalDate(p.getDietStarted()));

        } else {
            delete.setDisable(true);
        }
    }

    private LocalDate dateToLocalDate(Date d){
        return new Date(d.getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private Date localDateToDate(LocalDate d){
        return Date.from(Instant.from(d.atStartOfDay(ZoneId.systemDefault())));
    }
}
