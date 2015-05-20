package cz.fel.ds.gui.dietToPacientDialog;

import cz.fel.ds.database.model.*;
import cz.fel.ds.database.services.BasicService;
import cz.fel.ds.database.services.SearchService;
import cz.fel.ds.gui.GuiTool;
import javafx.beans.property.SimpleIntegerProperty;
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
    private Diet d;

    @FXML
    private TextField dayField;
    @FXML
    private TableView<MealSchedule> mealScheduleTable;
    @FXML
    private TableView<Meal> mealsTable;
    @FXML
    private TableView<MealScheduleChange> changesTable;
    @FXML
    private TableColumn<MealSchedule, String> mtName;
    @FXML
    private TableColumn<MealSchedule, String> mtMealTypeName;

    @FXML
    private TableColumn<MealScheduleChange, String> col1;
    @FXML
    private TableColumn<MealScheduleChange, String> col2;
    @FXML
    private TableColumn<MealScheduleChange, String> col3;


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
        try {
            Meal m = mealsTable.getSelectionModel().getSelectedItem();
            MealSchedule ms = mealScheduleTable.getSelectionModel().getSelectedItem();
            Diet d = p.getDiet();

            if(m==null)throw new Exception();
            if(ms==null)throw new Exception();
            if(d==null)throw new Exception();


            MealScheduleChange msc = new MealScheduleChange();
            msc.setMeal(m);
            msc.setPatient(p);
            msc.setMealSchedule(ms);

            basicService.addMealScheduleChanges(msc);

            refreshTable(d);
            System.err.println("add");

        } catch (Exception e) {

        }

    }

    @FXML
    public void deleteMealChange(ActionEvent event) {
        basicService.deleteMealScheduleChanges(changesTable.getSelectionModel().getSelectedItem());
        refreshTable(d);
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
            p.setDiet(null);
            basicService.savePatient(p);


            GuiTool.closeDialog(delete);
        } catch (Exception e) {
            System.out.println("error input parameters");
        }
    }

    public void init(Patient p) {
        this.p = p;

        //FILL COMBOBOX DIETS

        Diet dd = new Diet("No diet assigned");
        dd.setDietId(-666);

        dietList = FXCollections.observableArrayList();
        dietList.add(dd);
        dietList.addAll(searchService.dietsAll());
        diets.setItems(dietList);

        d = p.getDiet();
        System.out.println(d);
        if (d != null) {
            diets.getSelectionModel().select(d);
        } else {
            diets.getSelectionModel().select(dd);
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


        col1.setCellValueFactory(cellValue -> {

            Integer order = cellValue.getValue().getMealSchedule().getOrder();

            return new SimpleStringProperty(String.valueOf(order));
        });

        col2.setCellValueFactory(cellValue -> {
            return new SimpleStringProperty(cellValue.getValue().getMealSchedule().getMealType().getMealTypeName());
        });

        col3.setCellValueFactory(cellValue -> {
            return new SimpleStringProperty(cellValue.getValue().getMeal().getMealName());
        });


        dataMealSchedulesChanges = FXCollections.observableArrayList();

        changesTable.setItems(dataMealSchedulesChanges);

        refreshTable(d);
    }


    public void refreshList(Event e) {
        Diet d = diets.getSelectionModel().getSelectedItem();
        basicService.deleteMealScheduleChanges(p, d);
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

        dataMealSchedulesChanges.clear();
        dataMealSchedulesChanges.addAll(searchService.mealScheduleChangesToDiet(p));


    }

}
