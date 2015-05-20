package cz.fel.ds.gui.dietToPacientDialog;

import cz.fel.ds.database.model.*;
import cz.fel.ds.database.services.BasicService;
import cz.fel.ds.database.services.SearchService;
import cz.fel.ds.gui.GuiTool;
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
public class DietToPatientController
{
    private SearchService searchService = new SearchService();
    private BasicService basicService =new BasicService();
    private Patient p;

    @FXML
    private TextField dayField;
    @FXML
    private TableView<MealScheduleChange> mealScheduleTable;
    @FXML
    private TableView<Meal> mealsTable;
    @FXML
    private TableColumn<Meal,String> mtName;
    @FXML
    private TableColumn<MealScheduleChange, String> mtMealTypeName;
    @FXML
    private Button delete;
    @FXML
    private Button save;
    @FXML
    private ComboBox<String> mealTypes;
    @FXML
    private ComboBox<String> diets;

    private ObservableList<Meal> dataMeals;
    private ObservableList<MealScheduleChange> dataMealSchedulesChanges;

    @FXML
    public void addMealChange(ActionEvent event) //SIPKA DOLEVA
    {
        /*try
        {
            if(mealsTable.getSelectionModel().getSelectedItem() == null)throw new Exception();
            MealSchedule tp = new MealSchedule(p, mealsTable.getSelectionModel().getSelectedItem());
            tp.setDay(Integer.parseInt(dayField.getText()));
            basicService.saveMealScheduleChanges(tp);
        }
        catch (Exception e)
        {
            System.out.println("neni zvoleny training program");
        }
        refreshTable();*/
    }

    @FXML
    public void deleteMealChange(ActionEvent event)  //SIPKA DOPRAVA
    {
        try
        {
            /*MealSchedule tp = mealScheduleTable.getSelectionModel().getSelectedItem();
            if(tp==null)throw new Exception();
            basicService.deleteMealScheduleChanges(tp);
            refreshTable();*/
        }
        catch(Exception e)
        {
            System.out.println("neni zvoleny training program to patient");
        }
    }

    @FXML
    public void saveMealScheduleChanges(ActionEvent event)
    {
        try
        {
            GuiTool.closeDialog(save);
        }
        catch (Exception e)
        {
            System.out.println("error input parameters");
        }
    }
    @FXML
    public void deleteMealScheduleChanges(ActionEvent event)
    {
        try
        {
            for (int i = 0; i < dataMealSchedulesChanges.size(); i++)
            {
               basicService.deleteMealScheduleChanges(dataMealSchedulesChanges.get(i));
            }
            GuiTool.closeDialog(delete);
        }
        catch (Exception e)
        {
            System.out.println("error input parameters");
        }
    }

    public void init(Patient p)
    {
        this.p=p;
        //FILL COMBOBOX MEALTYPES
        ObservableList<MealType> g = searchService.mealTypesAll();
        ObservableList<String> temp = FXCollections.observableArrayList();
        for (int i = 0; i < g.size(); i++)temp.add(g.get(i).getMealTypeName());
        mealTypes.setItems(temp);
        //FILL COMBOBOX DIETS
        ObservableList<Diet> d = searchService.dietsAll();
        ObservableList<String> temp2 = FXCollections.observableArrayList();
        for (int i = 0; i < d.size(); i++)temp2.add(d.get(i).getName());
        diets.setItems(temp);


        dataMeals = searchService.mealsAll();
        dataMealSchedulesChanges = searchService.mealScheduleChangesToDiet(p);
        mealsTable.setItems(dataMeals);
        mealScheduleTable.setItems(dataMealSchedulesChanges);

        mealsTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("mealName"));
        ///kj/100g
        mealScheduleTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("order"));
        /*mtMealTypeName.setCellValueFactory(cellValue -> {
            return new SimpleStringProperty(cellValue.getValue().getMealType().getMealTypeName());
        });*/
        mtName.setCellValueFactory(cellValue -> new SimpleStringProperty(cellValue.getValue().getMealName()));
        refreshTable();
    }

    public void refreshTable()
    {
        dataMealSchedulesChanges.clear();
        dataMealSchedulesChanges.addAll(searchService.mealScheduleChangesToDiet(p));
        dataMeals.clear();
        dataMeals.addAll(searchService.mealsAll());
    }
}
