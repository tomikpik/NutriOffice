package cz.fel.ds.database.dao;

import cz.fel.ds.database.model.Patient;
import cz.fel.ds.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Query;

/**
 * Created by Tom on 15. 5. 2015.
 */
public class PatientDAO
{
    public int create(Patient patient)
    {
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().save(patient);
        HibernateUtil.getSession().getTransaction().commit();
        return patient.getPatientId();
    }

    public Patient read(int id)
    {
        HibernateUtil.getSession().beginTransaction();
        Patient patient = (Patient) HibernateUtil.getSession().get(Patient.class, id);
        HibernateUtil.getSession().getTransaction().commit();
        return patient;
    }


    public boolean updiet(Patient patient)
    {
        HibernateUtil.getSession().beginTransaction();
        try
        {
            HibernateUtil.getSession().update(patient);
            HibernateUtil.getSession().getTransaction().commit();
        }
        catch (Exception ex)
        {
            return false;
        }
        return true;
    }

    public boolean delete(Patient patient)
    {
        HibernateUtil.getSession().beginTransaction();
        try
        {
            HibernateUtil.getSession().delete(patient);
            HibernateUtil.getSession().getTransaction().commit();
        }
        catch (Exception ex)
        {
            return false;
        }
        return true;
    }

    public ObservableList<Patient> selectObjectsTo(String type, Object value)
    {
        HibernateUtil.getSession().beginTransaction();
        Query q = null;
        switch(type)
        {
            case "patientId":
                q =  HibernateUtil.getSession().createQuery("SELECT c from Patient c where c.patientId=:patientId");
                break;
            case "firstName":
                q =  HibernateUtil.getSession().createQuery("SELECT c from Patient c where c.firstName=:firstName");
                break;
            case "lastName":
                q =  HibernateUtil.getSession().createQuery("SELECT c from Patient c where c.lastName=:lastName");
                break;

            case "nameStartsWith":
                q =  HibernateUtil.getSession().createQuery("SELECT c from Patient c where c.lastName like concat(:nameStartsWith, '%') ");
                break;

            case "gender":
                q =  HibernateUtil.getSession().createQuery("SELECT c from Patient c where c.gender=:gender");
                break;
            case "birthdate":
                q =  HibernateUtil.getSession().createQuery("SELECT c from Patient c where c.birthdate=:birthdate");
                break;
            case "nationalId":
                q =  HibernateUtil.getSession().createQuery("SELECT c from Patient c where c.nationalId=:nationalId");
                break;
            case "email":
                q =  HibernateUtil.getSession().createQuery("SELECT c from Patient c where c.email=:email");
                break;
            case "phone":
                q =  HibernateUtil.getSession().createQuery("SELECT c from Patient c where c.phone=:phone");
                break;
            case "dietStarted":
                q =  HibernateUtil.getSession().createQuery("SELECT c from Patient c where c.dietStarted=:dietStarted");
                break;
            case "diet":
                q =  HibernateUtil.getSession().createQuery("SELECT c from Patient c where c.diet=:diet");
                break;
            default:
                q =  HibernateUtil.getSession().createQuery("SELECT c from Patient c");
                ObservableList<Patient> listOfPatients = FXCollections.observableList(q.list());
                HibernateUtil.getSession().getTransaction().commit();
                return listOfPatients;
        }
        q.setParameter(type, value);
        ObservableList<Patient> listOfPatients = FXCollections.observableList(q.list());
        HibernateUtil.getSession().getTransaction().commit();
        return listOfPatients;
    }

}
