package cz.fel.ds.gui.trainingToPacientDialog;

import cz.fel.ds.database.model.Patient;
import cz.fel.ds.database.model.TrainingProgram;
import cz.fel.ds.database.model.TrainingProgramToPatient;
import cz.fel.ds.database.services.BasicService;
import cz.fel.ds.database.services.SearchService;
import cz.fel.ds.gui.GuiTool;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created by Barush on 17. 5. 2015.
 */
public class TrainingToPatientController
{
    private SearchService searchService = new SearchService();
    private BasicService basicService =new BasicService();
    private Patient p;

    @FXML
    private TextField dayField;
    @FXML
    private TableView<TrainingProgramToPatient> trainingProgramPatientTable;
    @FXML
    private TableView<TrainingProgram> trainingProgramsTable;
    @FXML
    private TableColumn<TrainingProgramToPatient,String> tpptName;
    @FXML
    private Button delete;
    @FXML
    private Button save;

    private ObservableList<TrainingProgram> dataTrainingPrograms;
    private ObservableList<TrainingProgramToPatient> dataTrainingProgramsToPatient;

    @FXML
    public void addTPToPatient(ActionEvent event) //SIPKA DOLEVA
    {
        try
        {
            if(trainingProgramsTable.getSelectionModel().getSelectedItem() == null)throw new Exception();
            TrainingProgramToPatient tp = new TrainingProgramToPatient(p, trainingProgramsTable.getSelectionModel().getSelectedItem());
            tp.setDay(Integer.parseInt(dayField.getText()));
            basicService.saveTrainingProgramToPatient(tp);
            refreshTable();
        }
        catch (Exception e)
        {
            System.out.println("neni zvoleny training program");
        }
        refreshTable();
    }

    @FXML
    public void deleteTPFromPatient(ActionEvent event)  //SIPKA DOPRAVA
    {
        try
        {
            TrainingProgramToPatient tp = trainingProgramPatientTable.getSelectionModel().getSelectedItem();
            if(tp==null)throw new Exception();
            basicService.deleteTrainingProgramToPatient(tp);
            refreshTable();
        }
        catch(Exception e)
        {
            System.out.println("neni zvoleny training program to patient");
        }
    }

    @FXML
    public void saveTrainingProgramToPatient(ActionEvent event)
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
    public void deleteTrainingProgramToPatient(ActionEvent event)
    {
        try
        {
            for (int i = 0; i < dataTrainingProgramsToPatient.size(); i++)
            {
                basicService.deleteTrainingProgramToPatient(dataTrainingProgramsToPatient.get(i));
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
        dataTrainingPrograms = searchService.trainingProgramsAll();
        dataTrainingProgramsToPatient = searchService.trainingProgramToPatientsSearch(p);
        trainingProgramsTable.setItems(dataTrainingPrograms);
        trainingProgramPatientTable.setItems(dataTrainingProgramsToPatient);

        trainingProgramsTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
        //duration totally
        trainingProgramPatientTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("day"));
        tpptName.setCellValueFactory(cellValue -> new SimpleStringProperty(cellValue.getValue().getTrainingProgram().getName()));
        refreshTable();
    }

    public void refreshTable()
    {
        dataTrainingProgramsToPatient.clear();
        dataTrainingProgramsToPatient.addAll(searchService.trainingProgramToPatientsSearch(p));
        dataTrainingPrograms.clear();
        dataTrainingPrograms.addAll(searchService.trainingProgramsAll());
    }
}
