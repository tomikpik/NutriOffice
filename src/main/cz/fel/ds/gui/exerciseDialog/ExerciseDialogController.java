package cz.fel.ds.gui.exerciseDialog;

import cz.fel.ds.database.model.Exercise;
import cz.fel.ds.gui.GuiTool;
import cz.fel.ds.database.services.BasicService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Created by Tom on 14. 5. 2015.
 */
public class ExerciseDialogController {
    private Exercise exercise;
    private BasicService basicService =new BasicService();

    @FXML
    private TextField name;
    @FXML
    private TextField energy;
    @FXML
    private Button delete;

    public void setExcercise(Exercise exercise){
        this.exercise=exercise;
        if(exercise!=null){
            name.setText(exercise.getName());
            energy.setText(String.valueOf(exercise.getKjkgmin()));
        } else {
            delete.setDisable(true);
        }

    }

    @FXML
    public void saveExcercise(ActionEvent event){
        try {
            Exercise f = (exercise==null)?new Exercise():exercise;
            f.setName(name.getText());
            Float ev = Float.parseFloat(energy.getText());
            f.setKjkgmin(ev);
            if(basicService.saveExcercise(f)){
                GuiTool.closeDialog(delete);
            }else{
                System.out.println("excercise save failed - service");
            }
        } catch(Exception e){
            System.out.println("excercise saving failed - exception");
        }
    }

    @FXML
    public void deleteExcercise(ActionEvent event){
        if(exercise!=null){
            basicService.deleteExcercise(exercise);
            GuiTool.closeDialog(delete);
        }
    }

}
