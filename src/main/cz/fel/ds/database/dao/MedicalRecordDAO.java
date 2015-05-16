package cz.fel.ds.database.dao;

import cz.fel.ds.database.model.MedicalRecord;
import cz.fel.ds.database.model.Patient;
import cz.fel.ds.util.HibernateUtil;
import javafx.collections.ObservableList;
import org.hibernate.Query;
import java.util.Date;

/**
 * Created by Tom on 15. 5. 2015.
 */
public class MedicalRecordDAO
{
    public int create(MedicalRecord medicalRecord)
    {
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().save(medicalRecord);
        HibernateUtil.getSession().getTransaction().commit();
        return 1;
    }

    public MedicalRecord read(Date date, Patient patient)
    {
        HibernateUtil.getSession().beginTransaction();
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setDate(date);
        medicalRecord.setPatient(patient);

        medicalRecord = (MedicalRecord) HibernateUtil.getSession().get(MedicalRecord.class, medicalRecord);
        HibernateUtil.getSession().getTransaction().commit();
        return medicalRecord;
    }

    public boolean update(MedicalRecord medicalRecord)
    {
        HibernateUtil.getSession().beginTransaction();
        try
        {
            HibernateUtil.getSession().update(medicalRecord);
            HibernateUtil.getSession().getTransaction().commit();
        }
        catch (Exception ex)
        {
            return false;
        }
        return true;
    }

    public boolean delete(MedicalRecord medicalRecord)
    {
        HibernateUtil.getSession().beginTransaction();
        try
        {
            HibernateUtil.getSession().delete(medicalRecord);
            HibernateUtil.getSession().getTransaction().commit();
        }
        catch (Exception ex)
        {
            return false;
        }
        return true;
    }

    public ObservableList<MedicalRecord> selectObjectsTo(String type, Object value)
    {
        HibernateUtil.getSession().beginTransaction();
        Query q = null;
        switch(type)
        {
            case "height":
                q =  HibernateUtil.getSession().createQuery("SELECT c from MedicalRecord c where c.height=:height");
                break;
            case "weight":
                q =  HibernateUtil.getSession().createQuery("SELECT c from MedicalRecord c where c.weight=:weight");
                break;
            case "fat":
                q =  HibernateUtil.getSession().createQuery("SELECT c from MedicalRecord c where c.fat=:fat");
                break;
            case "waist":
                q =  HibernateUtil.getSession().createQuery("SELECT c from MedicalRecord c where c.waist=:waist");
                break;
            case "hip":
                q =  HibernateUtil.getSession().createQuery("SELECT c from MedicalRecord c where c.hip=:hip");
                break;
            case "chest":
                q =  HibernateUtil.getSession().createQuery("SELECT c from MedicalRecord c where c.chest=:chest");
                break;
            case "date":
                q =  HibernateUtil.getSession().createQuery("SELECT c from MedicalRecord c where c.date=:date");
                break;
            case "patient":
                q =  HibernateUtil.getSession().createQuery("SELECT c from MedicalRecord c where c.patient=:patient");
                break;
            default:
                q =  HibernateUtil.getSession().createQuery("SELECT c from MedicalRecord c");
                ObservableList<MedicalRecord> listOfMedicalRecords = (ObservableList<MedicalRecord>) q.list();
                HibernateUtil.getSession().getTransaction().commit();
                return listOfMedicalRecords;
        }
        q.setParameter(type, value);
        ObservableList<MedicalRecord> listOfMedicalRecords = (ObservableList<MedicalRecord>) q.list();
        HibernateUtil.getSession().getTransaction().commit();
        return listOfMedicalRecords;
    }

}
