package cz.fel.ds.gui.patientDialog;

import cz.fel.ds.database.model.Patient;
import cz.fel.ds.database.services.BasicService;
import cz.fel.ds.gui.DialogFactory;
import cz.fel.ds.gui.GuiTool;
import cz.fel.ds.gui.mainPage.MainPageController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    @FXML
    private ComboBox<String> gender;


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
            System.err.println("\n"+p+"\n");

            Patient patient = (p==null)?new Patient():p;
            patient.setFirstName(firstName.getText());
            patient.setLastName(lastName.getText());
            patient.setPhone(phone.getText());
            patient.setEmail(email.getText());
            patient.setBirthdate(GuiTool.localDateToDate(birthdate.getValue()));
            patient.setDietStarted(GuiTool.localDateToDate(dietStarted.getValue()));
            patient.setGender((gender.getValue().equalsIgnoreCase("Female"))?"F":"M");

            System.err.println("\n"+p+"\n");


            if(basicService.savePatient(patient)){
                mpc.refreshAllTables();
                GuiTool.closeDialog(delete);
            }else{
                System.out.println("patient save failed - service");
            }
        } catch(Exception e){
            //e.printStackTrace();
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

    @FXML
    public void showTrainingProgram(ActionEvent event){
        new DialogFactory(null).showTrainingProgramToPatientDialog(p);
    }

    @FXML
    public void showDiet(ActionEvent event)
    {
        new DialogFactory(null).showDietToPatient(p);
    }

    public void init(Patient p, MainPageController mpc){
        this.mpc=mpc;
        ObservableList<String> g = FXCollections.observableArrayList();
        g.add("Male");
        g.add("Female");
        gender.setItems(g);
        if(p!=null) {
            System.out.println(p);
            this.p = p;
            id.setText(String.valueOf(p.getPatientId()));
            firstName.setText(p.getFirstName());
            lastName.setText(p.getLastName());
            phone.setText(p.getPhone());
            email.setText(p.getEmail());
            gender.setValue((p.getGender().equalsIgnoreCase("M")) ?"Male":"Female");

            birthdate.setValue(GuiTool.dateToLocalDate(p.getBirthdate()));
            dietStarted.setValue(GuiTool.dateToLocalDate(p.getDietStarted()));

        } else {
            gender.setValue("Male");
            delete.setDisable(true);
        }

    }


}
