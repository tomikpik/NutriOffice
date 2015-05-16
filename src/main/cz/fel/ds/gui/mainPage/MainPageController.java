package cz.fel.ds.gui.mainPage;

import cz.fel.ds.database.model.Patient;
import cz.fel.ds.gui.DialogFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

/**
 * Created by Tom on 14. 5. 2015.
 */
public class MainPageController {
    private DialogFactory dialogFactory = new DialogFactory();


    @FXML
    private TextField pacientSearch;
    @FXML
    private RadioButton sortByNamesRadioButt;
    @FXML
    private TextField dietSearch;
    @FXML
    private TextField mealSearch;
    @FXML
    private TextField foodSearch;
    @FXML
    private TextField trainingSearch;
    @FXML
    private TextField excerciseSearch;
    @FXML
    private TableView<Patient> patientsTable;
    @FXML
    private TableView<Patient> dietTable;
    @FXML
    private TableView<Patient> mealTable;
    @FXML
    private TableView<Patient> foodTable;
    @FXML
    private TableView<Patient> trainingProgramTable;
    @FXML
    private TableView<Patient> exerciseTable;

/*
    private ObservableList<Patient> data = FXCollections.observableArrayList(
            new Patient(1, "Tomas", "Pikous"),
            new Patient(2, "Happy", "Empeor"),
            new Patient(3, "Barush", "Plagiatorka :D")
    );
*/

    private ObservableList<Patient> data;

    @FXML
    public void patientsSearch(ActionEvent event1) {
        System.out.println(pacientSearch.getText());
        Patient p = new Patient();
        p.setFirstName("Tomáš");
        p.setLastName("Pikous");
        data.add(p);
    }


    @FXML
    public void dietSearch(ActionEvent event) {
        //send cz.fel.ds.database request and return matches to given string
        System.out.println("search diet "+dietSearch.getText());
    }

    @FXML
    public void mealSearch(ActionEvent event) {
        //send cz.fel.ds.database request and return matches to given string
        System.out.println("search meal "+mealSearch.getText());
    }

    @FXML
    public void trainingSearch(ActionEvent event) {
        //send cz.fel.ds.database request and return matches to given string
        System.out.println("search training "+trainingSearch.getText());
    }

    @FXML
    public void excerciseSearch(ActionEvent event) {
        //send cz.fel.ds.database request and return matches to given string
        System.out.println("search excercise "+excerciseSearch.getText());
    }

    @FXML
    public void foodSearch(ActionEvent event) {
        //send cz.fel.ds.database request and return matches to given string
        System.out.println("search food "+foodSearch.getText());
    }

    @FXML
    public void trainingAdd(ActionEvent event) {
        //open new training dialog
        dialogFactory.showTrainingProgramDialog(null);
    }

    @FXML
    public void excerciseAdd(ActionEvent event) {
        //open new excercise dialog
        dialogFactory.showExerciseDialog(null);
    }

    @FXML
    public void foodAdd(ActionEvent event) {
        //open new food dialog
        dialogFactory.showFoodFialog(null);
    }

    @FXML
    public void mealAdd(ActionEvent event) {
        //open new meal dialog
        dialogFactory.showMealDialog(null);
    }

    @FXML
    public void dietAdd(ActionEvent event) {
        //open new diet dialog
        System.out.println("add diet");
    }

    public void init(){
        patientsTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("patientID"));
        patientsTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("firstName"));
        patientsTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("lastName"));

        //patientsTable.setItems(data);
        patientsTable.setRowFactory(tv -> {
            TableRow<Patient> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    dialogFactory.showPatientDialog(row.getItem());
                }
            });
            return row;
        });

        data = FXCollections.observableArrayList();

        patientsTable.setItems(data);



    }
}
