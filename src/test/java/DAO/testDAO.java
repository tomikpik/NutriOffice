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

public class testDAO extends TestCase{

    public void testPerson() throws Exception {
        PatientDAO pDAO = new PatientDAO();

        Patient patient = new Patient();
        patient.setFirstName("Tomáš");
        patient.setLastName("Pikous");
        patient.setEmail("tomas.pikous@gmail.com");

        int result = pDAO.create(patient);


    }

}
