package cz.fel.ds.database.services;

import cz.fel.ds.database.dao.*;
import cz.fel.ds.database.model.*;

/**
 * Created by Tom on 16. 5. 2015.
 */
public class BasicService {

    FoodDAO foodDAO = new FoodDAO();
    ExerciseDAO exerciseDAO = new ExerciseDAO();
    TrainingProgramDAO trainingProgramDAO = new TrainingProgramDAO();
    DietDAO dietDAO = new DietDAO();
    MealDAO mealDAO = new MealDAO();

    public boolean saveFood(Food food)
    {
        foodDAO.create(food);
        System.out.println(food);
        return true;
    }

    public boolean deleteFood(Food food){
        foodDAO.delete(food);
        System.out.println(food);
        return true;
    }

    public boolean saveExcercise(Exercise exercise){
        exerciseDAO.create(exercise);
        System.out.println(exercise);
        return true;
    }

    public boolean deleteExcercise(Exercise exercise){
        exerciseDAO.delete(exercise);
        System.out.println(exercise);
        return true;
    }


    public boolean trainingAdd(TrainingProgram trainingProgram)
    {
        trainingProgramDAO.create(trainingProgram);
        System.out.println(trainingProgram);
        return true;
    }

    public boolean mealAdd(Meal meal)
    {
        mealDAO.create(meal);
        System.out.println(meal);
        return true;
    }

    public boolean dietAdd(Diet diet)
    {
        dietDAO.create(diet);
        System.out.println(diet);
        return true;
    }

}
