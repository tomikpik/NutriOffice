package DAO; /**
 * Created by Tom on 15. 5. 2015.
 */

import database.dao.ExcerciseDAO;
import database.dao.ExcerciseToTrainingProgramDAO;
import database.dao.PatientDAO;
import database.dao.TrainingProgramDAO;
import database.model.Excercise;
import database.model.ExcerciseToTrainingProgram;
import database.model.Patient;
import database.model.TrainingProgram;
import junit.framework.TestCase;

public class testPersonDAO extends TestCase{

    public void test1() throws Exception {
        PatientDAO pDAO = new PatientDAO();

        Patient patient = new Patient();
        patient.setFirstName("Tomáš");
        patient.setLastName("Pikous");
        patient.setEmail("tomas.pikous@gmail.com");

        int result = pDAO.create(patient);

        Excercise excercise = new Excercise();
        excercise.setKjkgmin(100);
        excercise.setName("ahoj");
        new ExcerciseDAO().create(excercise);

        TrainingProgram tp = new TrainingProgram();
        tp.setName("los rotopedos");
        tp.setType(0);
        new TrainingProgramDAO().create(tp);

        ExcerciseToTrainingProgram ettp = new ExcerciseToTrainingProgram();
        ettp.setDuration(100);
        ettp.setExcercise(excercise);
        ettp.setTrainingProgram(tp);
        new ExcerciseToTrainingProgramDAO().create(ettp);

    }

}
