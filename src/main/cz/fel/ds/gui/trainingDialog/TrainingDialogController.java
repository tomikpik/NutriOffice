package cz.fel.ds.gui.trainingDialog;

import cz.fel.ds.database.model.Exercise;
import cz.fel.ds.database.model.ExerciseToTrainingProgram;
import cz.fel.ds.database.model.Patient;
import cz.fel.ds.database.model.TrainingProgram;
import cz.fel.ds.database.services.BasicService;
import cz.fel.ds.database.services.SearchService;
import cz.fel.ds.gui.GuiTool;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * Created by Tom on 14. 5. 2015.
 */
public class TrainingDialogController {
    private SearchService searchService = new SearchService();
    private BasicService basicService =new BasicService();

    @FXML
    private TableView<ExerciseToTrainingProgram> trainingProgramExercisesTable;
    @FXML
    private TableView<Exercise> exerciseTable;
    @FXML
    private TextField trainingProgramName;
    @FXML
    private TextField searchExercise;
    @FXML
    private TextField duration;
    @FXML
    private TableColumn<ExerciseToTrainingProgram,String> ettpTableName;
    @FXML
    private Button delete;

    private ObservableList<Exercise> dataExercises;
    private ObservableList<ExerciseToTrainingProgram> dataExerciseToTrainingProgram;
    private TrainingProgram tp;

    @FXML
    public void exerciseSearch(ActionEvent event) {
        dataExercises.clear();
        dataExercises.addAll(searchService.exerciseSearch(searchExercise.getText().toLowerCase()));
        dataExerciseToTrainingProgram.clear();
        dataExerciseToTrainingProgram.addAll(searchService.exerciseToTrainingSearchByTraining(tp));
    }

    @FXML
    public void addExercise(ActionEvent event) {
        try{
            Exercise exercise = exerciseTable.getSelectionModel().getSelectedItem();
            if(exercise==null)throw new Exception();
            Double dur = Double.parseDouble(duration.getText());
            if(trainingProgramName.getText().equalsIgnoreCase(""))throw new Exception();
            ExerciseToTrainingProgram ettp = new ExerciseToTrainingProgram();
            if(dur<=0)throw new Exception();

            if(tp==null){
                tp=new TrainingProgram();
            }
            tp.setName(trainingProgramName.getText());
            basicService.saveTrainingProgram(tp);

            ettp.setExercise(exercise);
            ettp.setTrainingProgram(tp);
            ettp.setDuration(dur);

            basicService.saveExerciseToTrainingProgram(ettp);

        }catch(Exception e){
            System.out.println("chyba training");
        }
    }

    @FXML
    public void removeExercise(ActionEvent event) {
        try{
            ExerciseToTrainingProgram ettp = trainingProgramExercisesTable.getSelectionModel().getSelectedItem();
            if(ettp==null)throw new Exception();
            basicService.deleteExerciseToTrainingProgram(ettp);

        }catch(Exception e){
            System.out.println("chyba training");
        }
    }

    @FXML
    public void saveTrainingProgram(ActionEvent event) {
        try {
            TrainingProgram f = (tp==null)?new TrainingProgram():tp;
            if(trainingProgramName.getText().equals(""))throw new Exception();
            f.setName(trainingProgramName.getText());
            if(basicService.saveTrainingProgram(f)){
                GuiTool.closeDialog(duration);
            }else{
                System.out.println("training program save failed - service");
            }
        } catch(Exception e){
            System.out.println("training program saving failed - exception");
        }
        //send cz.fel.ds.database request and return matches to given string
    }

    @FXML
    public void deleteTrainingProgram(ActionEvent event) {
        if(tp!=null){
            basicService.deleteTrainingProgram(tp);
            GuiTool.closeDialog(delete);
        }
    }

    public void init(TrainingProgram trainingProgram) {
        if(trainingProgram!=null) {
            trainingProgramName.setText(trainingProgram.getName());
            tp=trainingProgram;
            duration.setText("0");
        } else {
            delete.setDisable(true);
        }

        //load exercises
        //load exerciseToTrainingProgram
        exerciseTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
        dataExercises = FXCollections.observableArrayList();
        exerciseTable.setItems(dataExercises);
        dataExercises.clear();
        dataExercises.addAll(searchService.exerciseSearch(searchExercise.getText().toLowerCase()));

        //table with assigned exercises
        ettpTableName.setCellValueFactory(cellValue -> new SimpleStringProperty(cellValue.getValue().getExercise().getName()));
        trainingProgramExercisesTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("duration"));
        dataExerciseToTrainingProgram = FXCollections.observableArrayList();
        trainingProgramExercisesTable.setItems(dataExerciseToTrainingProgram);
        dataExerciseToTrainingProgram.clear();
        dataExerciseToTrainingProgram.addAll(searchService.exerciseToTrainingSearchByTraining(tp));

    }
}
