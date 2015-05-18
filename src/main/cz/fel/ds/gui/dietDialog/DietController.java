package cz.fel.ds.gui.dietDialog;

import cz.fel.ds.database.model.Diet;
import cz.fel.ds.database.model.Meal;
import cz.fel.ds.database.model.MealSchedule;
import cz.fel.ds.database.model.MealType;
import cz.fel.ds.database.services.BasicService;
import cz.fel.ds.database.services.SearchService;
import cz.fel.ds.gui.GuiTool;
import cz.fel.ds.gui.mainPage.MainPageController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created by Barush on 17. 5. 2015.
 */
public class DietController
{
    private MainPageController mpc;
    private SearchService searchService = new SearchService();
    private BasicService basicService =new BasicService();
    private Diet d;

    @FXML
    private TextField dayField;
    @FXML
    private TextField nameOfDietField;
    @FXML
    private TableView<MealSchedule> mealScheduleTable;
    @FXML
    private TableView<Meal> mealsTable;
    @FXML
    private TableColumn<MealSchedule,String> mstName;
    @FXML
    private TableColumn<MealSchedule,String> mstMealTypeName;
    @FXML
    private Button delete;
    @FXML
    private Button save;
    @FXML
    private ComboBox<String> mealTypes;

    private ObservableList<Meal> dataMeals;
    private ObservableList<MealSchedule> dataMealSchedules;

    private ObservableList<MealType> g;

    @FXML
    public void addMealToMealSchedule(ActionEvent event) //SIPKA DOLEVA
    {
        try
        {
            if(mealsTable.getSelectionModel().getSelectedItem() == null ||
                    mealTypes.getValue() == null ||
                    dayField == null)throw new Exception();

            MealType mt = g.parallelStream().filter(x -> x.getMealTypeName() == mealTypes.getValue()).findFirst().get();
            MealSchedule ms = new MealSchedule(Integer.parseInt(dayField.getText()), mt, mealsTable.getSelectionModel().getSelectedItem(),d);
            basicService.saveMealSchedule(ms);
            refreshTable();
        }
        catch (Exception e)
        {
            System.out.println("neni zvoleny training program");
        }
        refreshTable();
    }

    @FXML
    public void deleteMealFromMealSchedule(ActionEvent event)  //SIPKA DOPRAVA
    {
        try
        {
            MealSchedule ms = mealScheduleTable.getSelectionModel().getSelectedItem();
            if(ms==null)throw new Exception();
            basicService.deleteMealSchedule(ms);
            refreshTable();
        }
        catch(Exception e)
        {
            System.out.println("neni zvoleny training program to patient");
        }
    }

    @FXML
    public void saveDiet(ActionEvent event)
    {
        try
        {
            if(d == null)
            {
                d = new Diet();
                d.setName(nameOfDietField.getText());
                basicService.dietAdd(d);
            }
            else
            {
                d.setName(nameOfDietField.getText());
                basicService.updateDiet(d);
            }
            mpc.refreshAllTables();
            GuiTool.closeDialog(save);

        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("error input parameters");
        }
    }
    @FXML
    public void deleteDiet(ActionEvent event)
    {
        try
        {
            if(d != null)basicService.deleteDiet(d);
            mpc.refreshAllTables();
            GuiTool.closeDialog(delete);

        }
        catch (Exception e)
        {
            System.out.println("error input parameters");
        }
    }

    public void init(Diet d,MainPageController mpc)
    {
        this.mpc=mpc;
        this.d=d;
        //FILL COMBOBOX
        if(d != null)nameOfDietField.setText(d.getName());
        else
        {
            d = new Diet();
            basicService.dietAdd(d);
        }

        g = searchService.mealTypesAll();
        ObservableList<String> temp = FXCollections.observableArrayList();
        for (int i = 0; i < g.size(); i++)temp.add(g.get(i).getMealTypeName());
        mealTypes.setItems(temp);

        dataMeals = FXCollections.observableArrayList();
        dataMealSchedules = FXCollections.observableArrayList();
        mealsTable.setItems(dataMeals);
        mealScheduleTable.setItems(dataMealSchedules);

        mealsTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("mealName"));
        ///kj/100g
        mealScheduleTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("order"));
        mstMealTypeName.setCellValueFactory(cellValue->{
            return new SimpleStringProperty(cellValue.getValue().getMealType().getMealTypeName());
        });
        //mealScheduleTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("mealType"));
        mstName.setCellValueFactory(cellValue -> new SimpleStringProperty(cellValue.getValue().getMeal().getMealName()));
        refreshTable();
    }

    public void refreshTable()
    {
        dataMealSchedules.clear();
        dataMealSchedules.addAll(searchService.mealSchedulesToDiet(d));
        dataMeals.clear();
        dataMeals.addAll(searchService.mealsAll());
    }
}
