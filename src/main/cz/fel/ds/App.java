package cz.fel.ds;

import cz.fel.ds.database.dao.PatientDAO;
import cz.fel.ds.database.model.Patient;
import cz.fel.ds.gui.mainPage.MainPage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Tom on 15. 5. 2015.
 */
public class App {
    public static void main(String[] args) {

        //fillDatabase();
        new MainPage().start();
    }

    private static void fillDatabase()
    {
        PatientDAO patientDAO = new PatientDAO();
        List<Patient> temp = new ArrayList<>();
        String[] firstNames = new String[]{"Barus","Tomik","Romanek","Pepa","Drahoslava"};
        String[] lastNames = new String[]{"Suchanova","Pious","Long asi","Omacka","Novotna"};
        Date date = new Date(2015,2,5);
        for (int i = 0; i < 5; i++)
        {
            Patient p = new Patient();
            p.setFirstName(firstNames[i]);
            p.setLastName(lastNames[i]);
            //p.setBirthdate(date);
            p.setEmail(lastNames[i] + "@seznam.cz");
            p.setGender(Math.random() > 0.5 ? "M" : "Ž");
            patientDAO.create(p);
        }
    }

}
