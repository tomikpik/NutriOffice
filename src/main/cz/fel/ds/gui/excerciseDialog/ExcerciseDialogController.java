package cz.fel.ds.gui.excerciseDialog;

import cz.fel.ds.database.model.Excercise;
import cz.fel.ds.database.model.Food;
import cz.fel.ds.gui.GuiTool;
import cz.fel.ds.service.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by Tom on 14. 5. 2015.
 */
public class ExcerciseDialogController {
    private Excercise excercise;
    private Service service=new Service();

    @FXML
    private TextField name;
    @FXML
    private TextField energy;
    @FXML
    private Button delete;

    public void setExcercise(Excercise excercise){
        this.excercise=excercise;
        if(excercise!=null){
            name.setText(excercise.getName());
            energy.setText(String.valueOf(excercise.getKjkgmin()));
        } else {
            delete.setDisable(true);
        }

    }

    @FXML
    public void saveExcercise(ActionEvent event){
        try {
            Excercise f = (excercise==null)?new Excercise():excercise;
            f.setName(name.getText());
            Float ev = Float.parseFloat(energy.getText());
            f.setKjkgmin(ev);
            if(service.saveExcercise(f)){
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
        if(excercise!=null){
            service.deleteExcercise(excercise);
            GuiTool.closeDialog(delete);
        }
    }

}
