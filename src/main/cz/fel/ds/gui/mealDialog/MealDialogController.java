package cz.fel.ds.gui.mealDialog;

import cz.fel.ds.database.model.*;
import cz.fel.ds.database.services.BasicService;
import cz.fel.ds.database.services.SearchService;
import cz.fel.ds.gui.GuiTool;
import cz.fel.ds.gui.mainPage.MainPageController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.text.DecimalFormat;

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
    @FXML
    private TableColumn<MealToFood,String> mtfTableNameTotalKj;

    private ObservableList<Food> dataFood;
    private ObservableList<MealToFood> dataMealToFood;
    private Meal meal;
    private MainPageController mpc;

    @FXML
    public void foodSearch(Event event) {
        dataFood.clear();
        dataFood.addAll(searchService.foodSearch(searchFood.getText().toLowerCase()));
    }

    @FXML
    public void addFood(Event event) {
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
            refreshTables();
        }catch(Exception e){
            System.out.println("chyba training");
        }
    }

    @FXML
    public void removeFood(Event event) {
        try{
            MealToFood mtf = mealFoodsTable.getSelectionModel().getSelectedItem();
            if(mtf==null)throw new Exception();
            basicService.deleteMealToFood(mtf);
            refreshTables();
        }catch(Exception e){
            System.out.println("chyba meal");
        }
    }

    @FXML
    public void saveMeal(Event event) {
        try {
            Meal m = (meal==null)?new Meal():meal;
            if(mealName.getText().equals(""))throw new Exception();
            m.setMealName(mealName.getText());
            if(basicService.saveMeal(m)){
                mpc.refreshAllTables();
                GuiTool.closeDialog(quantity);
            }else{
                System.out.println("meal save failed - service");
            }
        } catch(Exception e){
            System.out.println("meal saving failed - exception");
        }
    }

    @FXML
    public void deleteMeal(Event event) {
        if(meal!=null){
            basicService.deleteMeal(meal);
            mpc.refreshAllTables();
            GuiTool.closeDialog(delete);
        }
    }

    public void init(Meal meal, MainPageController mpc) {
        this.mpc=mpc;

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
        foodTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("energyValue"));
        dataFood = FXCollections.observableArrayList();
        foodTable.setItems(dataFood);


        //table with assigned exercises
        mtfTableName.setCellValueFactory(cellValue -> new SimpleStringProperty(cellValue.getValue().getFood().getFoodName()));
        mealFoodsTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("quantity"));
        mtfTableNameTotalKj.setCellValueFactory(cellValue -> {
            Double kjkg = cellValue.getValue().getQuantity()*cellValue.getValue().getFood().getEnergyValue();
            DecimalFormat df = new DecimalFormat("#.0");
            return new SimpleStringProperty(df.format(kjkg));
        });
        dataMealToFood = FXCollections.observableArrayList();
        mealFoodsTable.setItems(dataMealToFood);
        refreshTables();
    }

    private void refreshTables(){
        dataFood.clear();
        dataFood.addAll(searchService.foodSearch(searchFood.getText().toLowerCase()));
        dataMealToFood.clear();
        dataMealToFood.addAll(searchService.mealToFoodSearchByMeal(meal));
    }
}
