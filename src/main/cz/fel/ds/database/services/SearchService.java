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
    DietDAO dietDAO = new DietDAO();
    MealDAO mealDAO = new MealDAO();
    MealToFoodDAO mealToFoodDAO = new MealToFoodDAO();


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

    public ObservableList<Exercise> exerciseSearch(String input)
    {
        return exerciseDAO.selectObjectsTo("nameStartsWith",input);
    }

    public ObservableList<Food> foodSearch(String input)
    {
        return foodDAO.selectObjectsTo("nameStartsWith",input);
    }

    public ObservableList<ExerciseToTrainingProgram> exerciseToTrainingProgramSearch(String input)
    {
        return exerciseToTrainingProgramDAO.selectObjectsTo("all","");
    }

    public ObservableList<MealToFood> mealToFoodSearch(String input)
    {
        return mealToFoodDAO.selectObjectsTo("all","");
    }

}
