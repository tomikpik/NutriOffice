package cz.fel.ds.database.dao;

import cz.fel.ds.database.model.MedicalRecord;
import cz.fel.ds.database.model.Patient;
import cz.fel.ds.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.Date;


/**
 * Created by Tom on 15. 5. 2015.
 */
public class MedicalRecordDAO
{
    public int create(MedicalRecord medicalRecord)
    {
        try {
            HibernateUtil.getSession().beginTransaction();
            HibernateUtil.getSession().save(medicalRecord);
            HibernateUtil.getSession().getTransaction().commit();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public MedicalRecord read(Date date, Patient patient)
    {
        HibernateUtil.getSession().beginTransaction();
        MedicalRecord medicalRecord = (MedicalRecord) HibernateUtil.getSession().get(MedicalRecord.class,new MedicalRecord(patient,date));
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
            ex.printStackTrace();
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
        ObservableList<MedicalRecord> listOfMedicalRecords;
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

                //
                Criteria crit;
                crit = HibernateUtil.getSession().createCriteria(MedicalRecord.class);
               //crit.add(Restrictions.or(Restrictions.or(Restrictions.ilike("lastName", value + "%"), Restrictions.ilike("firstName", value + "%")), Restrictions.eq("patientId", i)));
                crit.add(Restrictions.eq("patient", value));

                //sort podle patient id
                crit.addOrder(Order.asc("date"));


                listOfMedicalRecords = FXCollections.observableList(crit.list());
                HibernateUtil.getSession().getTransaction().commit();
                return listOfMedicalRecords;
                //q =  HibernateUtil.getSession().createQuery("SELECT c from MedicalRecord c where c.patient=:patient");
                //break;
            default:
                q =  HibernateUtil.getSession().createQuery("SELECT c from MedicalRecord c");
                listOfMedicalRecords = FXCollections.observableList(q.list());
                HibernateUtil.getSession().getTransaction().commit();
                return listOfMedicalRecords;
        }
        q.setParameter(type, value);
        listOfMedicalRecords = FXCollections.observableList(q.list());
        HibernateUtil.getSession().getTransaction().commit();
        return listOfMedicalRecords;
    }

    public void updateOrInsertIfNotExists(MedicalRecord mr) {
        try {
            MedicalRecord medicalRecord;
            if ((medicalRecord = this.read(mr.getDate(), mr.getPatient())) != null) {
                HibernateUtil.getSession().getTransaction().begin();
                HibernateUtil.getSession().merge(mr);
                HibernateUtil.getSession().getTransaction().commit();

                //needs to update

            } else {
                //just save
                HibernateUtil.getSession().getTransaction().begin();
                this.create(mr);
                HibernateUtil.getSession().getTransaction().commit();
            }
        }catch(Exception e){

        }


    }
}
