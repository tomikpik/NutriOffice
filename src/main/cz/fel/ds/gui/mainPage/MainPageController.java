package cz.fel.ds.gui.mainPage;

import cz.fel.ds.database.model.Patient;
import cz.fel.ds.gui.foodDialog.FoodDialog;
import cz.fel.ds.gui.patientDialog.PatientDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

/**
 * Created by Tom on 14. 5. 2015.
 */
public class MainPageController {
    @FXML
    private TextField pacientSearch;
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

/*
    private ObservableList<Patient> data = FXCollections.observableArrayList(
            new Patient(1, "Tomas", "Pikous"),
            new Patient(2, "Happy", "Empeor"),
            new Patient(3, "Barush", "Plagiatorka :D")
    );
*/
    @FXML
    public void patientsSearch(ActionEvent event1) {
        System.out.println(pacientSearch.getText());
        patientsTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("PatientID"));
        patientsTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        patientsTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("SecondName"));

        //patientsTable.setItems(data);
        patientsTable.setRowFactory(tv -> {
            TableRow<Patient> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    PatientDialog pd = new PatientDialog(row.getItem(), false);
                    try {
                        pd.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
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
        System.out.println("add training");
    }

    @FXML
    public void excerciseAdd(ActionEvent event) {
        //open new excercise dialog
        System.out.println("add excercise");
    }

    @FXML
    public void foodAdd(ActionEvent event) {
        //open new food dialog
        System.out.println("add food");
        new FoodDialog().showFoodFialog(null);
    }

    @FXML
    public void mealAdd(ActionEvent event) {
        //open new meal dialog
        System.out.println("add meal");
    }

    @FXML
    public void dietAdd(ActionEvent event) {
        //open new diet dialog
        System.out.println("add diet");
    }
}
