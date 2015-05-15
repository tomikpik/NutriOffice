package database.dao;

import database.model.MedicalRecord;
import database.model.Patient;
import util.HibernateUtil;

/**
 * Created by Tom on 15. 5. 2015.
 */
public class PatientDAO {
    public int create(Patient patient){
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().save(patient);
        HibernateUtil.getSession().getTransaction().commit();
        return patient.getPatientId();
    }
    public Patient read(int id){
        HibernateUtil.getSession().beginTransaction();
        Patient patient = (Patient) HibernateUtil.getSession().get(Patient.class, id);
        HibernateUtil.getSession().getTransaction().commit();
        return patient;
    }
    public boolean update(MedicalRecord medicalRecord){
        return true;
    }
    public boolean delete(Patient patient){
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().delete(patient);
        HibernateUtil.getSession().getTransaction().commit();
        return true;
    }

}
