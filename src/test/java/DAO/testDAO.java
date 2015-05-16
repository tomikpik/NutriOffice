package DAO;

/**
 * Created by Tom on 15. 5. 2015.
 */

import cz.fel.ds.database.dao.PatientDAO;
import cz.fel.ds.database.model.Patient;
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
