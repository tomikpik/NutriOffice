package cz.fel.ds.gui.patientDialog;

import cz.fel.ds.database.model.Patient;
import cz.fel.ds.database.services.BasicService;
import cz.fel.ds.gui.DialogFactory;
import cz.fel.ds.gui.GuiTool;
import cz.fel.ds.gui.mainPage.MainPageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * Created by Tom on 14. 5. 2015.
 */
public class PatientDialogController {
    private BasicService basicService =new BasicService();

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
    private MainPageController mpc;

    public void test(ActionEvent event) {
        LocalDate localDate = birthdate.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        //Date date = Date.from(instant);
        //System.out.println(localDate + "\n" + instant + "\n" + date);
    }

    @FXML
    public void savePatient(ActionEvent event){
        try {
            Patient patient = (p==null)?new Patient():p;
            p.setFirstName(firstName.getText());
            p.setLastName(lastName.getText());
            p.setPhone(phone.getText());
            p.setEmail(email.getText());
            p.setBirthdate(GuiTool.localDateToDate(birthdate.getValue()));
            p.setDietStarted(GuiTool.localDateToDate(dietStarted.getValue()));

            if(basicService.savePatient(patient)){
                mpc.refreshAllTables();
                GuiTool.closeDialog(delete);
            }else{
                System.out.println("patient save failed - service");
            }
        } catch(Exception e){
            System.out.println("patient saving failed - exception");
        }
    }

    @FXML
    public void deletePatient(ActionEvent event){
        if(p!=null){
            basicService.deletePatient(p);
            mpc.refreshAllTables();
            GuiTool.closeDialog(delete);
        }
    }


    @FXML
    public void showMedicalRecords(ActionEvent event){
        new DialogFactory(null).showMedicalRecordDialog(p);
    }

    public void init(Patient p, MainPageController mpc){
        this.mpc=mpc;
        if(p!=null) {
            System.out.println(p);
            this.p = p;
            id.setText(String.valueOf(p.getPatientId()));
            firstName.setText(p.getFirstName());
            lastName.setText(p.getLastName());
            phone.setText(p.getPhone());
            email.setText(p.getEmail());

            birthdate.setValue(GuiTool.dateToLocalDate(p.getBirthdate()));
            dietStarted.setValue(GuiTool.dateToLocalDate(p.getDietStarted()));

        } else {
            delete.setDisable(true);
        }
    }


}
