package cz.fel.ds.gui.foodDialog;

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
public class FoodDialogController {
    private Food food;
    private Service service=new Service();

    @FXML
    private TextField name;
    @FXML
    private TextField energy;
    @FXML
    private Button delete;

    public void setFood(Food food){
        this.food=food;
        if(food!=null){
            name.setText(food.getFoodName());
            energy.setText(String.valueOf(food.getEnergyValue()));
        }else{
            delete.setDisable(true);
        }
    }

    @FXML
    public void saveFood(ActionEvent event){
        try {
            Food f = (food==null)?new Food():food;
            f.setFoodName(name.getText());
            Float ev = Float.parseFloat(energy.getText());
            f.setEnergyValue(ev);
            if(service.saveFood(f)){
                GuiTool.closeDialog(delete);
            }else{
                System.out.println("food save failed - service");
            }
        } catch(Exception e){
            System.out.println("food saving failed - exception");
        }

    }

    @FXML
    public void deleteFood(ActionEvent event){
        if(food!=null){
            service.deleteFood(food);
            GuiTool.closeDialog(delete);
        }
    }

}
