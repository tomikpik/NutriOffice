package DAO;

import cz.fel.ds.database.dao.*;
import cz.fel.ds.database.model.Exercise;
import javafx.collections.ObservableList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SearchServiceTest
{
    PatientDAO patientDAO = new PatientDAO();
    FoodDAO foodDAO = new FoodDAO();
    ExerciseDAO exerciseDAO = new ExerciseDAO();
    TrainingProgramDAO trainingProgramDAO = new TrainingProgramDAO();
    DietDAO dietDAO = new DietDAO();
    MealDAO mealDAO = new MealDAO();

    @Before
    public void setUp() throws Exception
    {
        exerciseDAO.create(new Exercise("Bowling",5.0));
        exerciseDAO.create(new Exercise("Bobova draha",5.0));
        exerciseDAO.create(new Exercise("Florbal",6.0));
        exerciseDAO.create(new Exercise("Hokej",7.0));
    }

    @Test
    public void testExcerciseSearch() throws Exception
    {
       ObservableList<Exercise> result = exerciseDAO.selectObjectsTo("nameStartsWith", "Bo");
       assertEquals(result.size(),2);

    }
}