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
    TrainingProgramToPatientDAO trainingProgramToPatientDAO = new TrainingProgramToPatientDAO();
    MealToFoodDAO mealToFoodDAO = new MealToFoodDAO();
    MealScheduleDAO mealScheduleDAO = new MealScheduleDAO();
    DietDAO dietDAO = new DietDAO();
    MealDAO mealDAO = new MealDAO();
    PatientDAO patientDAO = new PatientDAO();
    MedicalRecordDAO medicalRecordDAO = new MedicalRecordDAO();

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

    public boolean saveMeal(Meal meal)
    {
        mealDAO.create(meal);
        System.out.println(meal);
        return true;
    }

    public boolean deleteMeal(Meal meal){
        mealDAO.delete(meal);
        return true;
    }



    public boolean dietAdd(Diet diet)
    {
        dietDAO.create(diet);
        System.out.println(diet);
        return true;
    }

    public void deleteDiet(Diet diet)
    {
        dietDAO.delete(diet);
    }

    public boolean saveMealToFood(MealToFood mtf) {
        mealToFoodDAO.create(mtf);
        return true;
    }


    public void deleteMealToFood(MealToFood mtf) {
        mealToFoodDAO.delete(mtf);
    }


    public boolean savePatient(Patient patient) {
        patientDAO.create(patient);
        return true;
    }

    public void deletePatient(Patient patient){
        patientDAO.delete(patient);
    }

    public void saveMedicalRecord(MedicalRecord mr) {
        medicalRecordDAO.updateOrInsertIfNotExists(mr);
    }
    public void deleteMedicalRecord(MedicalRecord mr) {
        medicalRecordDAO.delete(mr);
    }

    public void deleteTrainingProgramToPatient(TrainingProgramToPatient tp)
    {
        trainingProgramToPatientDAO.delete(tp);
    }

    public void saveTrainingProgramToPatient(TrainingProgramToPatient tp)
    {
        trainingProgramToPatientDAO.updateOrInsertIfNotExists(tp);
    }

    public void saveMealSchedule(MealSchedule ms)
    {
        mealScheduleDAO.create(ms);
    }

    public void deleteMealSchedule(MealSchedule ms)
    {
        mealScheduleDAO.delete(ms);
    }

    public void updateDiet(Diet d)
    {
        dietDAO.update(d);
    }
}
