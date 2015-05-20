package cz.fel.ds.gui.dietToPacientDialog;

import cz.fel.ds.database.model.*;
import cz.fel.ds.database.services.BasicService;
import cz.fel.ds.database.services.SearchService;
import cz.fel.ds.gui.GuiTool;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created by Barush on 17. 5. 2015.
 */
public class DietToPatientController {
    private SearchService searchService = new SearchService();
    private BasicService basicService = new BasicService();
    private Patient p;

    @FXML
    private TextField dayField;
    @FXML
    private TableView<MealSchedule> mealScheduleTable;
    @FXML
    private TableView<Meal> mealsTable;
    @FXML
    private TableColumn<MealSchedule, String> mtName;
    @FXML
    private TableColumn<MealSchedule, String> mtMealTypeName;
    @FXML
    private Button delete;
    @FXML
    private Button save;
    @FXML
    private ComboBox<String> mealTypes;
    @FXML
    private ComboBox<Diet> diets;

    private ObservableList<MealSchedule> dataDietMeals;
    private ObservableList<MealScheduleChange> dataMealSchedulesChanges;
    private ObservableList<Diet> dietList;
    private ObservableList<Meal> dataMealsList;


    @FXML
    public void addMealChange(ActionEvent event) {
        System.err.println("add");
    }

    @FXML
    public void deleteMealChange(ActionEvent event) {
        System.err.println("delete");
    }

    @FXML
    public void saveMealScheduleChanges(ActionEvent event) {
        try {
            basicService.addDietToPatient(p, diets.getSelectionModel().getSelectedItem());
            GuiTool.closeDialog(save);
        } catch (Exception e) {
            System.out.println("error input parameters");
        }
    }

    @FXML
    public void deleteMealScheduleChanges(ActionEvent event) {
        try {
            for (int i = 0; i < dataMealSchedulesChanges.size(); i++) {
                basicService.deleteMealScheduleChanges(dataMealSchedulesChanges.get(i));
            }
            GuiTool.closeDialog(delete);
        } catch (Exception e) {
            System.out.println("error input parameters");
        }
    }

    public void init(Patient p) {
        this.p = p;
        //FILL COMBOBOX MEALTYPES
        ObservableList<MealType> g = searchService.mealTypesAll();
        ObservableList<String> temp = FXCollections.observableArrayList();
        for (int i = 0; i < g.size(); i++) temp.add(g.get(i).getMealTypeName());
        mealTypes.setItems(temp);

        //FILL COMBOBOX DIETS

        Diet d = new Diet("No diet assigned");
        d.setDietId(-666);

        dietList = FXCollections.observableArrayList();
        dietList.add(d);
        dietList.addAll(searchService.dietsAll());
        diets.setItems(dietList);

        Diet pdiet = p.getDiet();
        System.out.println(pdiet);
        if (pdiet != null) {
            diets.getSelectionModel().select(pdiet);
        } else {
            diets.getSelectionModel().select(d);
        }

        dataDietMeals = FXCollections.observableArrayList();



        mealScheduleTable.setItems(dataDietMeals);
        mealScheduleTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("order"));
        mtMealTypeName.setCellValueFactory(cellValue -> {
            return new SimpleStringProperty(cellValue.getValue().getMealType().getMealTypeName());
        });
        mtName.setCellValueFactory(cellValue -> {
            return new SimpleStringProperty(cellValue.getValue().getMeal().getMealName());
        });

        dataMealsList = FXCollections.observableArrayList();
        mealsTable.setItems(dataMealsList);

        mealsTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("mealName"));


        refreshTable(pdiet);
    }


    public void refreshList(Event e) {
        Diet d = diets.getSelectionModel().getSelectedItem();
        refreshTable(d);
    }

    public void refreshTable(Diet d) {
        dataMealsList.clear();
        dataMealsList.addAll(searchService.mealsAll());

        dataDietMeals.clear();
        if (d != null) {
            if (d.getDietId() != -666) {
                dataDietMeals.addAll(searchService.mealSchedulesToDiet(d));
            }
        }
    }

}
