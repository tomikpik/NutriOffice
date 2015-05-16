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
    ExerciseToTrainingProgramDAO exerciseToTrainingProgramDAO = new ExerciseToTrainingProgramDAO();
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


    public boolean saveTrainingProgram(TrainingProgram trainingProgram)
    {
        trainingProgramDAO.create(trainingProgram);
        System.out.println(trainingProgram);
        return true;
    }

    public boolean deleteTrainingProgram(TrainingProgram trainingProgram){
        trainingProgramDAO.delete(trainingProgram);
        System.out.println(trainingProgram);
        return true;
    }

    public boolean saveExerciseToTrainingProgram(ExerciseToTrainingProgram exerciseToTrainingProgram){
        exerciseToTrainingProgramDAO.create(exerciseToTrainingProgram);
        System.out.println(exerciseToTrainingProgram);
        return true;
    }

    public boolean deleteExerciseToTrainingProgram(ExerciseToTrainingProgram exerciseToTrainingProgram){
        exerciseToTrainingProgramDAO.delete(exerciseToTrainingProgram);
        System.out.println(exerciseToTrainingProgram);
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
