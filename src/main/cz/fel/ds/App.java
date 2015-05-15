package cz.fel.ds;

import cz.fel.ds.gui.mainPage.MainPage;

/**
 * Created by Tom on 15. 5. 2015.
 */
public class App {
    public static void main(String[] args) {
       /* MedicalRecordDAO medicalRecordDAO = new MedicalRecordDAO();
        PatientDAO patientDAO = new PatientDAO();
        ExcerciseDAO excerciseDAO = new ExcerciseDAO();
        ExcerciseToTrainingProgramDAO excerciseToTrainingProgramDAO = new ExcerciseToTrainingProgramDAO();
        TrainingProgramDAO trainingProgramDAO = new TrainingProgramDAO();
        TrainingProgramToPatientDAO trainingProgramToPatientDAO = new TrainingProgramToPatientDAO();

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setChest(1);
        medicalRecord.setFat(2);
        medicalRecord.setWaist(5);


        Patient patient = new Patient();
        patient.setFirstName("Tasdasdomáš");
        patient.setLastName("Pikous");

        medicalRecord.setPatient(patient);
        medicalRecordDAO.create(medicalRecord);

        Excercise excercise = new Excercise();
        excercise.setName("běh");
        excercise.setKjkgmin(10);


        TrainingProgram trainingProgram = new TrainingProgram();
        trainingProgram.setName("Tomíkův cvičební program");
        trainingProgram.setType(0);

        trainingProgramDAO.create(trainingProgram);
        excerciseDAO.create(excercise);

        ExcerciseToTrainingProgram excerciseToTrainingProgram = new ExcerciseToTrainingProgram();
        excerciseToTrainingProgram.setDuration(100);
        excerciseToTrainingProgram.setExcercise(excercise);
        excerciseToTrainingProgram.setTrainingProgram(trainingProgram);


        excerciseToTrainingProgramDAO.create(excerciseToTrainingProgram);


        TrainingProgramToPatient trainingProgramToPatient = new TrainingProgramToPatient();
        trainingProgramToPatient.setDay(0);
        trainingProgramToPatient.setPatient(patient);
        trainingProgramToPatient.setTrainingProgram(trainingProgram);

        trainingProgramToPatientDAO.create(trainingProgramToPatient);


*/

        /*
        Food food = new Food();
        food.setEnergyValue(100);
        food.setFoodName("banán");

        FoodDAO foodDAO = new FoodDAO();
        foodDAO.create(food);

        Meal meal = new Meal();
        meal.setMealName("banánová mísa");

        MealDAO mealDAO = new MealDAO();
        mealDAO.create(meal);

        MealToFood mealtf = new MealToFood();
        mealtf.setFood(food);
        mealtf.setMeal(meal);
        mealtf.setQuantity(1);

        MealToFoodDAO mealToFoodDAO = new MealToFoodDAO();
        mealToFoodDAO.create(mealtf);

*/
 //hhohohohoohvino
        new MainPage().start();
    }


}
