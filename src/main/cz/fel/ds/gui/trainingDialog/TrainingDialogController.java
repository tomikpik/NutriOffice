package cz.fel.ds.gui.trainingDialog;

import cz.fel.ds.database.model.Exercise;
import cz.fel.ds.database.model.ExerciseToTrainingProgram;
import cz.fel.ds.database.model.Patient;
import cz.fel.ds.database.model.TrainingProgram;
import cz.fel.ds.database.services.BasicService;
import cz.fel.ds.database.services.SearchService;
import cz.fel.ds.gui.GuiTool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

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


    private ObservableList<Exercise> dataExercises;
    private TrainingProgram tp;

    @FXML
    public void exerciseSearch(ActionEvent event) {
        dataExercises.clear();
        dataExercises.addAll(searchService.exerciseSearch(searchExercise.getText().toLowerCase()));
    }

    @FXML
    public void addExercise(ActionEvent event) {
        try{
            Exercise exercise = exerciseTable.getSelectionModel().getSelectedItem();
            Double dur = Double.parseDouble(duration.getText());
            if(trainingProgramName.getText().equalsIgnoreCase(""))throw new Exception();
            ExerciseToTrainingProgram ettp = new ExerciseToTrainingProgram();

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
        //send cz.fel.ds.database request and return matches to given string
    }

    @FXML
    public void saveTrainingProgram(ActionEvent event) {
        try {
            TrainingProgram f = (tp==null)?new TrainingProgram():tp;
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
        //send cz.fel.ds.database request and return matches to given string
    }

    public void init(TrainingProgram trainingProgram) {
        if(trainingProgram!=null) {
            trainingProgramName.setText(trainingProgram.getName());
            tp=trainingProgram;
            duration.setText("0");
        }

        //load exercises
        //load exerciseToTrainingProgram
        exerciseTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
        dataExercises = FXCollections.observableArrayList();
        exerciseTable.setItems(dataExercises);
        dataExercises.clear();
        dataExercises.addAll(searchService.exerciseSearch(searchExercise.getText().toLowerCase()));

    }
}
