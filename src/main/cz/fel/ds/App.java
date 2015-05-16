package cz.fel.ds;

import cz.fel.ds.database.dao.*;
import cz.fel.ds.database.model.*;
import cz.fel.ds.gui.GuiTool;

import java.util.Date;

/**
 * Created by Tom on 15. 5. 2015.
 */
public class App {
    public static void main(String[] args) {
        fillDatabase();
        new GuiTool().start();
    }

    private static void fillDatabase()
    {
        //PATIENTS
        PatientDAO patientDAO = new PatientDAO();
        String[] firstNames = new String[]{"Barus","Tomik","Romanek","Pepa","Drahoslava"};
        String[] lastNames = new String[]{"Suchanova","Pikous","Long asi","Omacka","Novotna"};
        Date date = new Date(2015,2,5);
        for (int i = 0; i < 5; i++)
        {
            Patient p = new Patient();
            p.setFirstName(firstNames[i]);
            p.setLastName(lastNames[i]);
            //p.setBirthdate(date);n
            p.setEmail(lastNames[i] + "@seznam.cz");
            p.setGender(Math.random() > 0.5 ? "M" : "Z");
            patientDAO.create(p);
        }

        //EXERCISES
        ExerciseDAO exerciseDAO = new ExerciseDAO();
        String[] names = new String[]{"Spinning","Beh-36km/h","Beh-20km/h","Beh-10km/h","Jizda na kole", "Bowling"};
        double[] kjkgmin = new double[]{0.65,2.2,0.7,0.56,0.39};
        for (int i = 0; i < 5; i++)
        {
            Exercise p = new Exercise();
            p.setName(names[i]);
            p.setKjkgmin(kjkgmin[i]);
            exerciseDAO.create(p);
        }

        //TRAINING PROGRAM
        TrainingProgramDAO trainingProgramDAO = new TrainingProgramDAO();
        String[] tp = new String[]{"Cardio - zena","Cardio - muz","Posilovaci - zena","Posilovaci - muz", "Spalovaci - zena","Spalovaci - muz"};
        for (int i = 0; i < 6; i++)
        {
            TrainingProgram p = new TrainingProgram();
            p.setName(tp[i]);
            trainingProgramDAO.create(p);
        }

        //FOOD
        FoodDAO foodDAO = new FoodDAO();
        String[] food = new String[]{"Banan","Knedlik houskovy","Zeli","Bageta","Kureci prsa", "Orechy kesu","Steak hovezi libovy"};
        double[] energy = new double[]{386,740,105,1150,366,2316.63,523.38};
        for (int i = 0; i < 6; i++)
        {
            Food p = new Food();
            p.setFoodName(food[i]);
            p.setEnergyValue(energy[i]);
            foodDAO.create(p);
        }

        //MEAL
        MealDAO mealDAO = new MealDAO();
        String[] meal = new String[]{"Knedlo-vepro-kuro","Steak s bagetou","Bananovo-avokadovy koktejl","Zelnacka","Bageta s kuretem a kesu", "Avokado-kesu dort"};
        for (int i = 0; i < 6; i++)
        {
            Meal p = new Meal();
            p.setMealName(meal[i]);
            mealDAO.create(p);
        }

        //MEAL TO FOOD se nabinduje v guicku
        //EXERCISE TO TRAINING PROGRAM taky v guicku
        //TRAINING PROGRAM to PATIENT taky v guicku

        //DIET
        DietDAO dietDAO = new DietDAO();
        String[] diet = new String[]{"Redukcni - zena","Redukcni - muz","Nabiraci - zena","Nabiraci - muz","Udrzovaci - zena", "Udrzovaci - muz"};
        for (int i = 0; i < 6; i++)
        {
            Diet p = new Diet();
            p.setName(diet[i]);
            dietDAO.create(p);
        }

        //MEAL TYPE
        MealTypeDAO mealTypeDAO = new MealTypeDAO();
        String[] type = new String[]{"Snidane","Dopoledni svacina","Obed","Odpoledni svacina","Vecere","Druha vecere"};
        for (int i = 0; i < 6; i++)
        {
            MealType p = new MealType();
            p.setMealTypeName(type[i]);
            mealTypeDAO.create(p);
        }


    }

}

