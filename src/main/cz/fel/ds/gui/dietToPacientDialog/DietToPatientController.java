package cz.fel.ds.gui.dietToPacientDialog;

import cz.fel.ds.database.model.Meal;
import cz.fel.ds.database.model.MealSchedule;
import cz.fel.ds.database.model.MealType;
import cz.fel.ds.database.model.Patient;
import cz.fel.ds.database.services.BasicService;
import cz.fel.ds.database.services.SearchService;
import cz.fel.ds.gui.GuiTool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    private TableView<MealSchedule> mealScheduleTable;
    @FXML
    private TableView<Meal> mealsTable;
    @FXML
    private TableColumn<MealSchedule,Integer> tpptOrder;
    @FXML
    private TableColumn<Meal,String> tpptName;
    @FXML
    private Button delete;
    @FXML
    private Button save;
    @FXML
    private ComboBox<String> mealTypes;

    private ObservableList<Meal> dataMeals;
    private ObservableList<MealSchedule> dataMealSchedules;

    @FXML
    public void addTPToPatient(ActionEvent event) //SIPKA DOLEVA
    {
        /*try
        {
            if(mealsTable.getSelectionModel().getSelectedItem() == null)throw new Exception();
            MealSchedule tp = new MealSchedule(p, mealsTable.getSelectionModel().getSelectedItem());
            tp.setDay(Integer.parseInt(dayField.getText()));
            basicService.saveMealSchedule(tp);
        }
        catch (Exception e)
        {
            System.out.println("neni zvoleny training program");
        }
        refreshTable();*/
    }

    @FXML
    public void deleteTPFromPatient(ActionEvent event)  //SIPKA DOPRAVA
    {
        try
        {
            /*MealSchedule tp = mealScheduleTable.getSelectionModel().getSelectedItem();
            if(tp==null)throw new Exception();
            basicService.deleteMealSchedule(tp);
            refreshTable();*/
        }
        catch(Exception e)
        {
            System.out.println("neni zvoleny training program to patient");
        }
    }

    @FXML
    public void saveMealSchedule(ActionEvent event)
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
    public void deleteMealSchedule(ActionEvent event)
    {
        try
        {
            for (int i = 0; i < dataMealSchedules.size(); i++)
            {
               // basicService.deleteMealSchedule(dataMealSchedules.get(i));
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
        //FILL COMBOBOX
        ObservableList<MealType> g = searchService.mealTypesAll();
        ObservableList<String> temp = FXCollections.observableArrayList();
        for (int i = 0; i < g.size(); i++)temp.add(g.get(i).getMealTypeName());
        mealTypes.setItems(temp);

/*
        dataMeals = searchService.mealsAll();
        dataMealSchedules = searchService.mealToPatientsSearch(p);
        mealsTable.setItems(dataMeals);
        mealScheduleTable.setItems(dataMealSchedules);

        //mealsTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("height"));
        //mealsTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("weight"));
        refreshTable();*/
    }

   /* public void refreshTable()
    {
        dataMealSchedules.clear();
        dataMealSchedules.addAll(searchService.mealToPatientsSearch(p));
        dataMeals.clear();
        dataMeals.addAll(searchService.mealsAll());
    }*/
}
