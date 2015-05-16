package DAO;

import cz.fel.ds.database.dao.ExerciseDAO;
import cz.fel.ds.database.dao.ExerciseToTrainingProgramDAO;
import cz.fel.ds.database.dao.TrainingProgramDAO;
import cz.fel.ds.database.model.Exercise;
import cz.fel.ds.database.model.ExerciseToTrainingProgram;
import cz.fel.ds.database.model.TrainingProgram;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ExerciseToTrainingProgramDAOTest
{
    ExerciseToTrainingProgramDAO etDAO;
    TrainingProgram tp;
    TrainingProgram tp1;
    Exercise ex;
    Exercise ex1;
    ExerciseToTrainingProgram et;
    ExerciseToTrainingProgram et1;

    @Before
    public void setUp() throws Exception
    {
        ExerciseDAO exDAO = new ExerciseDAO();
        ex = new Exercise();
        ex.setName("Spinning");
        ex.setKjkgmin(15);
        ex1 = new Exercise();
        ex1.setName("Posilovna");
        ex1.setKjkgmin(8);
        //CREATE EXERCISES
        exDAO.create(ex);
        exDAO.create(ex1);

        TrainingProgramDAO tpDAO = new TrainingProgramDAO();
        tp = new TrainingProgram();
        tp1 = new TrainingProgram();
        tp.setName("Cardio");
        tp.setType(0);
        tp1.setName("Muscle-building");
        tp1.setType(1);
        //CREATE EXERCISES
        tpDAO.create(tp);
        tpDAO.create(tp1);

        etDAO = new ExerciseToTrainingProgramDAO();
        et = new ExerciseToTrainingProgram();
        et.setDuration(120);
        et.setExercise(ex1);
        et.setTrainingProgram(tp1);

        et1 = new ExerciseToTrainingProgram();
        et1.setDuration(60);
        et1.setExercise(ex);
        et1.setTrainingProgram(tp);
        //CREATE ExerciseToTrainingProgram
        etDAO.create(et);
        etDAO.create(et1);

        //ale vytvori to dva spinningy a dve posilky
    }

    @Test
    public void testRead() throws Exception
    {
        ExerciseToTrainingProgram etREAD = etDAO.read(ex1, tp1);
        assertEquals(etREAD,et);
        assertNotEquals(etREAD,et1);
    }

    @Test
    public void testSelectObjectsTo() throws Exception
    {

    }
}