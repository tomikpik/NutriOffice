package cz.fel.ds.gui;

import cz.fel.ds.database.model.Exercise;
import cz.fel.ds.database.model.Food;
import cz.fel.ds.database.model.Patient;
import cz.fel.ds.database.model.TrainingProgram;
import cz.fel.ds.gui.exerciseDialog.ExerciseDialogController;
import cz.fel.ds.gui.foodDialog.FoodDialogController;
import cz.fel.ds.gui.medicalRecord.MedicalRecordController;
import cz.fel.ds.gui.patientDialog.PatientDialogController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Tom on 16. 5. 2015.
 */
public class DialogFactory {
    public void showExerciseDialog(Exercise exercise){
        try{
            String title = (exercise==null)?"New excercise":"Excercise";
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("exerciseDialog/excercise_dialog.fxml"));
            Parent root=loader.load();
            stage.setTitle(title);
            stage.setResizable(false);
            stage.setScene(new Scene(root,300,150));
            stage.show();
            ExerciseDialogController c = loader.getController();
            c.setExcercise(exercise);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void showFoodFialog(Food food){
        try{
            String title = (food==null)?"New food":"Food";

            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("foodDialog/food_dialog.fxml"));
            Parent root=loader.load();
            stage.setTitle(title);
            stage.setResizable(false);
            stage.setScene(new Scene(root,300,150));
            stage.show();
            FoodDialogController c = loader.getController();
            c.setFood(food);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void showMedicalRecordDialog(Patient p){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("medicalRecord/medical_record_dialog.fxml"));

            Parent root = loader.load();
            stage.setTitle("Medical records");
            stage.setResizable(false);
            stage.setScene(new Scene(root, 700,600));
            stage.show();
            MedicalRecordController controller = loader.getController();
            controller.loadData(p);
        }catch (IOException e){
            System.out.println("oops");
        }
    }

    public void showPatientDialog(Patient model, boolean newPacient) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("patientDialog/patient_dialog.fxml"));

            Parent root=loader.load();
            stage.setTitle((newPacient)?"New Patient":"Patient detail");
            stage.setResizable(false);
            stage.setScene(new Scene(root,433,433));
            stage.show();
            PatientDialogController controller = loader.getController();
            controller.loadData(model);
        }catch (IOException e){
            System.out.println("oops");
        }
    }

    public void showTrainingProgramDialog(TrainingProgram trainingProgram){
        try {

            throw new IOException();
        }catch (IOException e){
            System.out.println("oops");
        }
    }

}