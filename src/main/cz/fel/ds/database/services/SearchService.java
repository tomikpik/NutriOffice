package cz.fel.ds.database.services;

import cz.fel.ds.database.dao.*;
import cz.fel.ds.database.model.*;
import javafx.collections.ObservableList;

/**
 * Created by Barush on 16. 5. 2015.
 */
public class SearchService
{
    PatientDAO patientDAO = new PatientDAO();
    FoodDAO foodDAO = new FoodDAO();
    ExerciseDAO exerciseDAO = new ExerciseDAO();
    TrainingProgramDAO trainingProgramDAO = new TrainingProgramDAO();
    ExerciseToTrainingProgramDAO exerciseToTrainingProgramDAO = new ExerciseToTrainingProgramDAO();
    TrainingProgramToPatientDAO trainingProgramToPatientDAO = new TrainingProgramToPatientDAO();
    DietDAO dietDAO = new DietDAO();
    MealDAO mealDAO = new MealDAO();
    MealTypeDAO mealTypeDAO = new MealTypeDAO();
    MealToFoodDAO mealToFoodDAO = new MealToFoodDAO();
    MedicalRecordDAO medicalRecordDAO = new MedicalRecordDAO();


    public ObservableList<Patient> patientsSearch(String input)
    {
        return patientDAO.selectObjectsTo("nameStartsWith",input);
    }

    public ObservableList<Diet> dietSearch(String input)
    {
        return dietDAO.selectObjectsTo("nameStartsWith",input);
    }

    public ObservableList<Meal> mealSearch(String input)
    {
        return mealDAO.selectObjectsTo("nameStartsWith",input);
    }

    public ObservableList<TrainingProgram> trainingSearch(String input)
    {
        return trainingProgramDAO.selectObjectsTo("nameStartsWith",input);
    }

    public ObservableList<TrainingProgram> trainingProgramsAll()
    {
        return trainingProgramDAO.selectObjectsTo("all","");
    }

    public ObservableList<Exercise> exerciseSearch(String input)
    {
        return exerciseDAO.selectObjectsTo("nameStartsWith",input);
    }

    public ObservableList<Food> foodSearch(String input)
    {
        return foodDAO.selectObjectsTo("nameStartsWith",input);
    }

    public ObservableList<ExerciseToTrainingProgram> exerciseToTrainingProgramSearchAll(String input)
    {
        return exerciseToTrainingProgramDAO.selectObjectsTo("all","");
    }

    public ObservableList<ExerciseToTrainingProgram> exerciseToTrainingSearchByTraining(TrainingProgram trainingProgram)
    {
        return exerciseToTrainingProgramDAO.selectObjectsTo("trainingProgram",trainingProgram);
    }

    public ObservableList<MealToFood> mealToFoodSearchByMeal(Meal meal)
    {
        return mealToFoodDAO.selectObjectsTo("meal",meal);
    }

    public ObservableList<MealType> mealTypesAll()
    {
        return mealTypeDAO.selectObjectsTo("all","");
    }

    public ObservableList<MealToFood> mealToFoodSearch(String input)
    {
        return mealToFoodDAO.selectObjectsTo("all","");
    }

    public ObservableList<MedicalRecord> medicalRecordsByPatient(Patient p) {
        return medicalRecordDAO.selectObjectsTo("patient",p);
    }

    public ObservableList<TrainingProgramToPatient> trainingProgramToPatientsSearch(Patient p)
    {
        return trainingProgramToPatientDAO.selectObjectsTo("patient",p);
    }
}
