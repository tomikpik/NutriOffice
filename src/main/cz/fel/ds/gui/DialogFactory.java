package cz.fel.ds.gui;

import cz.fel.ds.database.model.*;
import cz.fel.ds.gui.exerciseDialog.ExerciseDialogController;
import cz.fel.ds.gui.foodDialog.FoodDialogController;
import cz.fel.ds.gui.mainPage.MainPageController;
import cz.fel.ds.gui.mealDialog.MealDialogController;
import cz.fel.ds.gui.medicalRecord.MedicalRecordController;
import cz.fel.ds.gui.patientDialog.PatientDialogController;
import cz.fel.ds.gui.trainingDialog.TrainingDialogController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Tom on 16. 5. 2015.
 */
public class DialogFactory {
    private MainPageController mpc;

    public DialogFactory(MainPageController mpc){
        this.mpc=mpc;
    }

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
            c.init(exercise,mpc);
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
            c.init(food,mpc);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void showMedicalRecordDialog(Patient p){
        if(p==null)return;
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("medicalRecord/medical_record_dialog.fxml"));

            Parent root = loader.load();
            stage.setTitle("Medical records - "+p.getFirstName()+" "+p.getLastName());
            stage.setResizable(false);
            stage.setScene(new Scene(root, 700,600));
            stage.show();
            MedicalRecordController controller = loader.getController();
            controller.init(p);
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("oops");
        }
    }

    public void showPatientDialog(Patient patient) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("patientDialog/patient_dialog.fxml"));

            Parent root=loader.load();
            stage.setTitle((patient==null)?"New Patient":patient.getFirstName()+" "+patient.getLastName());
            stage.setResizable(false);
            stage.setScene(new Scene(root,300,470));
            stage.show();
            PatientDialogController controller = loader.getController();
            controller.init(patient,mpc);
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("oops");
        }
    }

    public void showTrainingProgramDialog(TrainingProgram trainingProgram){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("trainingDialog/training_dialog.fxml"));
            Parent root=loader.load();
            stage.setTitle((trainingProgram==null)?"New training program":"Training program");
            stage.setResizable(false);
            stage.setScene(new Scene(root,600,600));
            stage.show();
            TrainingDialogController controller = loader.getController();
            controller.init(trainingProgram,mpc);

        }catch (IOException e){
            System.out.println("oops");
        }
    }

    public void showMealDialog(Meal meal){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mealDialog/meal_dialog.fxml"));
            Parent root=loader.load();
            stage.setTitle((meal==null)?"New meal":"Meal");
            stage.setResizable(false);
            stage.setScene(new Scene(root,600,600));
            stage.show();
            MealDialogController controller = loader.getController();
            controller.init(meal,mpc);

        }catch (IOException e){
            System.out.println("oops");
        }
    }

}
