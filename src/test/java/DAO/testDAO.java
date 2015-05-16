package DAO;

/**
 * Created by Tom on 15. 5. 2015.
 */

import cz.fel.ds.database.dao.ExerciseDAO;
import cz.fel.ds.database.dao.ExerciseToTrainingProgramDAO;
import cz.fel.ds.database.dao.PatientDAO;
import cz.fel.ds.database.dao.TrainingProgramDAO;
import cz.fel.ds.database.model.Exercise;
import cz.fel.ds.database.model.ExerciseToTrainingProgram;
import cz.fel.ds.database.model.Patient;
import cz.fel.ds.database.model.TrainingProgram;
import junit.framework.TestCase;

public class testDAO extends TestCase{

    public void testPerson() throws Exception {
        PatientDAO pDAO = new PatientDAO();

        Patient patient = new Patient();
        patient.setFirstName("Tomáš");
        patient.setLastName("Pikous");
        patient.setEmail("tomas.pikous@gmail.com");

        int result = pDAO.create(patient);

    }

    public void testExercise() throws Exception {
        Exercise cycling = new Exercise("cycling",10.0);
        new ExerciseDAO().create(cycling);
        System.out.println(cycling);

        TrainingProgram tp = new TrainingProgram();
        tp.setName("cyklistění");
        tp.setType(0);
        new TrainingProgramDAO().create(tp);
        System.out.println(tp);

        ExerciseToTrainingProgramDAO ettpDAO = new ExerciseToTrainingProgramDAO();
        ExerciseToTrainingProgram ettp1 = new ExerciseToTrainingProgram();
        ettp1.setDuration(100);
        ettp1.setExercise(cycling);
        ettp1.setTrainingProgram(tp);

        ettpDAO.create(ettp1);


        tp = new TrainingProgram();
        tp.setName("cycling");
        tp.setType(1);
        new TrainingProgramDAO().create(tp);
        System.out.println(tp);

        ettp1 = new ExerciseToTrainingProgram();
        ettp1.setDuration(20);
        ettp1.setExercise(cycling);
        ettp1.setTrainingProgram(tp);

        ettpDAO.create(ettp1);





    }

}
