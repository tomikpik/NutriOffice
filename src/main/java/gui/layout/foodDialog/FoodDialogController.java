package gui.layout.foodDialog;

import database.model.Food;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Created by Tom on 14. 5. 2015.
 */
public class FoodDialogController {
    private Food food;

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
        }
    }

    @FXML
    public void saveFood(ActionEvent event){

        try {
            Food f=new Food();
            f.setFoodName(name.getText());
            Float ev = Float.parseFloat(energy.getText());
            f.setEnergyValue(ev);
            System.out.println("save "+f);

        } catch(Exception e){
            System.out.println("ooops chybas");
        }

    }

    @FXML
    public void deleteFood(ActionEvent event){
        if(food!=null){
            System.out.println("somehow delete current food");
        } else {
            System.out.println("cannot delete non existing food");
        }

    }
}
