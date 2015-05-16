package cz.fel.ds.gui.mainPage;

import cz.fel.ds.database.model.*;
import cz.fel.ds.database.services.SearchService;
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

/**
 * Created by Tom on 14. 5. 2015.
 */
public class MainPageController {
    private DialogFactory dialogFactory = new DialogFactory();
    private SearchService searchService = new SearchService();


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
    private TextField exerciseSearch;
    @FXML
    private TableView<Patient> patientsTable;
    @FXML
    private TableView<Diet> dietTable;
    @FXML
    private TableView<Meal> mealTable;
    @FXML
    private TableView<Food> foodTable;
    @FXML
    private TableView<TrainingProgram> trainingProgramTable;
    @FXML
    private TableView<Exercise> exerciseTable;

    private ObservableList<Patient> dataPacients;
    private ObservableList<Diet> dataDiets;
    private ObservableList<Meal> dataMeals;
    private ObservableList<TrainingProgram> dataTrainingPrograms;
    private ObservableList<Exercise> dataExercises;
    private ObservableList<Food> dataFood;

    @FXML
    public void patientsSearch(ActionEvent event1)
    {
        dataPacients.clear();
        dataPacients.addAll(searchService.patientsSearch(pacientSearch.getText()));
    }


    @FXML
    public void dietSearch(ActionEvent event) {
        dataDiets.clear();
        dataDiets.addAll(searchService.dietSearch(dietSearch.getText().toLowerCase()));
    }

    @FXML
    public void mealSearch(ActionEvent event) {
        dataMeals.clear();
        dataMeals.addAll(searchService.mealSearch(mealSearch.getText().toLowerCase()));
    }

    @FXML
    public void trainingSearch(ActionEvent event) {
        dataTrainingPrograms.clear();
        dataTrainingPrograms.addAll(searchService.trainingSearch(trainingSearch.getText().toLowerCase()));
    }

    @FXML
    public void exerciseSearch(ActionEvent event) {
        dataExercises.clear();
        dataExercises.addAll(searchService.exerciseSearch(exerciseSearch.getText()));
    }

    @FXML
    public void foodSearch(ActionEvent event) {
        dataFood.clear();
        dataFood.addAll(searchService.foodSearch(foodSearch.getText()));
    }

    @FXML
    public void trainingAdd(ActionEvent event) {
	dialogFactory.showTrainingProgramDialog(null);
    }

    @FXML
    public void exerciseAdd(ActionEvent event) {
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

        patientsTable.setRowFactory(tv -> {
            TableRow<Patient> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    dialogFactory.showPatientDialog(row.getItem());
                }
            });
            return row;
        });

        dataPacients = FXCollections.observableArrayList();
        patientsTable.setItems(dataPacients);


        //exercise
        exerciseTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
        exerciseTable.setRowFactory(tv -> {
            TableRow<Exercise> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    dialogFactory.showExerciseDialog(row.getItem());
                }
            });
            return row;
        });

        dataExercises = FXCollections.observableArrayList();
        exerciseTable.setItems(dataExercises);

        //training program
        trainingProgramTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
        trainingProgramTable.setRowFactory(tv -> {
            TableRow<TrainingProgram> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    dialogFactory.showTrainingProgramDialog(row.getItem());
                }
            });
            return row;
        });

        dataTrainingPrograms = FXCollections.observableArrayList();
        trainingProgramTable.setItems(dataTrainingPrograms);

        //food
        foodTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("foodName"));
        foodTable.setRowFactory(tv -> {
            TableRow<Food> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    dialogFactory.showFoodFialog(row.getItem());
                }
            });
            return row;
        });

        dataFood = FXCollections.observableArrayList();
        foodTable.setItems(dataFood);

        //meals
        mealTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("mealName"));
        mealTable.setRowFactory(tv -> {
            TableRow<Meal> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    dialogFactory.showMealDialog(row.getItem());
                }
            });
            return row;
        });

        dataMeals = FXCollections.observableArrayList();
        mealTable.setItems(dataMeals);

        dataPacients.clear();
        dataPacients.addAll(searchService.patientsSearch(pacientSearch.getText()));

        dataMeals.clear();
        dataMeals.addAll(searchService.mealSearch(mealSearch.getText()));

        dataTrainingPrograms.clear();
        dataTrainingPrograms.addAll(searchService.trainingSearch(trainingSearch.getText()));

        dataExercises.clear();
        dataExercises.addAll(searchService.exerciseSearch(exerciseSearch.getText()));

        dataFood.clear();
        dataFood.addAll(searchService.foodSearch(foodSearch.getText()));


    }
}
