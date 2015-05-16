package cz.fel.ds.gui.mealDialog;

import cz.fel.ds.database.model.*;
import cz.fel.ds.database.services.BasicService;
import cz.fel.ds.database.services.SearchService;
import cz.fel.ds.gui.GuiTool;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created by Tom on 14. 5. 2015.
 */
public class MealDialogController {
    private SearchService searchService = new SearchService();
    private BasicService basicService =new BasicService();

    @FXML
    private TableView<MealToFood> mealFoodsTable;
    @FXML
    private TableView<Food> foodTable;
    @FXML
    private TextField mealName;
    @FXML
    private TextField searchFood;
    @FXML
    private TextField quantity;
    @FXML
    private Button delete;
    @FXML
    private TableColumn<MealToFood,String> mtfTableName;

    private ObservableList<Food> dataFood;
    private ObservableList<MealToFood> dataMealToFood;
    private Meal meal;

    @FXML
    public void foodSearch(ActionEvent event) {
        dataFood.clear();
        dataFood.addAll(searchService.foodSearch(searchFood.getText().toLowerCase()));
        dataMealToFood.clear();
        dataMealToFood.addAll(searchService.mealToFoodSearchByMeal(meal));
    }

    @FXML
    public void addFood(ActionEvent event) {
        try{
            Food food = foodTable.getSelectionModel().getSelectedItem();
            if(food==null)throw new Exception();
            Double q = Double.parseDouble(quantity.getText());
            if(mealName.getText().equalsIgnoreCase(""))throw new Exception();
            MealToFood mtf = new MealToFood();
            if(q<=0)throw new Exception();

            if(meal==null){
                meal=new Meal();
            }
            meal.setMealName(mealName.getText());
            basicService.saveMeal(meal);

            mtf.setFood(food);
            mtf.setMeal(meal);
            mtf.setQuantity(q);

            basicService.saveMealToFood(mtf);

        }catch(Exception e){
            System.out.println("chyba training");
        }
    }

    @FXML
    public void removeFood(ActionEvent event) {
        try{
            MealToFood mtf = mealFoodsTable.getSelectionModel().getSelectedItem();
            if(mtf==null)throw new Exception();
            basicService.deleteMealToFood(mtf);

        }catch(Exception e){
            System.out.println("chyba meal");
        }
    }

    @FXML
    public void saveMeal(ActionEvent event) {
        try {
            Meal m = (meal==null)?new Meal():meal;
            if(mealName.getText().equals(""))throw new Exception();
            m.setMealName(mealName.getText());
            if(basicService.saveMeal(m)){
                GuiTool.closeDialog(quantity);
            }else{
                System.out.println("meal save failed - service");
            }
        } catch(Exception e){
            System.out.println("meal saving failed - exception");
        }
    }

    @FXML
    public void deleteMeal(ActionEvent event) {
        if(meal!=null){
            basicService.deleteMeal(meal);
            GuiTool.closeDialog(delete);
        }
    }

    public void init(Meal meal) {


        if(meal!=null) {
            mealName.setText(meal.getMealName());
            this.meal=meal;
            quantity.setText("0");
        } else {
            delete.setDisable(true);
        }

        //load exercises
        //load exerciseToTrainingProgram
        foodTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("foodName"));
        dataFood = FXCollections.observableArrayList();
        foodTable.setItems(dataFood);
        dataFood.clear();
        dataFood.addAll(searchService.foodSearch(searchFood.getText().toLowerCase()));

        //table with assigned exercises
        mtfTableName.setCellValueFactory(cellValue -> new SimpleStringProperty(cellValue.getValue().getFood().getFoodName()));
        mealFoodsTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("quantity"));
        dataMealToFood = FXCollections.observableArrayList();
        mealFoodsTable.setItems(dataMealToFood);
        dataMealToFood.clear();
        dataMealToFood.addAll(searchService.mealToFoodSearchByMeal(meal));
    }
}
