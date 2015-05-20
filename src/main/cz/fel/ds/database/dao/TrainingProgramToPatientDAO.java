package cz.fel.ds.database.dao;

import cz.fel.ds.database.model.Patient;
import cz.fel.ds.database.model.TrainingProgram;
import cz.fel.ds.database.model.TrainingProgramToPatient;
import cz.fel.ds.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Query;

/**
 * Created by Tom on 15. 5. 2015.
 */
public class TrainingProgramToPatientDAO
{

    public int create(TrainingProgramToPatient trainingProgramToPatient)
    {
        HibernateUtil.getSession().beginTransaction();


        Query q = null;
        q = HibernateUtil.getSession().createQuery("DELETE from TrainingProgramToPatient tptp where tptp.day=:a and tptp.patient.patientId=:b");
        q.setParameter("a", trainingProgramToPatient.getDay());
        q.setParameter("b", trainingProgramToPatient.getPatient().getPatientId());
        q.executeUpdate();

        HibernateUtil.getSession().save(trainingProgramToPatient);
        HibernateUtil.getSession().getTransaction().commit();
        return 1;
    }

    public TrainingProgramToPatient read(TrainingProgram trainingProgram, Patient patient)
    {
        HibernateUtil.getSession().beginTransaction();
        TrainingProgramToPatient trainingProgramToPatient = new TrainingProgramToPatient(patient,trainingProgram);

        trainingProgramToPatient = (TrainingProgramToPatient) HibernateUtil.getSession().get(TrainingProgramToPatient.class, trainingProgramToPatient);
        HibernateUtil.getSession().getTransaction().commit();
        return trainingProgramToPatient;
    }


    public boolean update(TrainingProgramToPatient trainingProgramToPatient)
    {
        HibernateUtil.getSession().beginTransaction();
        try
        {
            HibernateUtil.getSession().update(trainingProgramToPatient);
            HibernateUtil.getSession().getTransaction().commit();
        }
        catch (Exception ex)
        {
            return false;
        }
        return true;
    }

    public boolean delete(TrainingProgramToPatient trainingProgramToPatient)
    {
        HibernateUtil.getSession().beginTransaction();
        try
        {
            HibernateUtil.getSession().delete(trainingProgramToPatient);
            HibernateUtil.getSession().getTransaction().commit();
        }
        catch (Exception ex)
        {
            return false;
        }
        return true;
    }

    public ObservableList<TrainingProgramToPatient> selectObjectsTo(String type, Object value)
    {
        HibernateUtil.getSession().beginTransaction();
        Query q = null;
        switch(type)
        {
            case "day":
                q =  HibernateUtil.getSession().createQuery("SELECT c from TrainingProgramToPatient c where c.day=:day");
                break;
            case "trainingProgram":
                q =  HibernateUtil.getSession().createQuery("SELECT c from TrainingProgramToPatient c where c.trainingProgram=:trainingProgram");
                break;
            case "patient":
                q =  HibernateUtil.getSession().createQuery("SELECT c from TrainingProgramToPatient c where c.patient=:patient");
                break;
            default:
                q =  HibernateUtil.getSession().createQuery("SELECT c from TrainingProgramToPatient c");
                ObservableList<TrainingProgramToPatient> listOfTrainingProgramToPatients = FXCollections.observableList(q.list());
                HibernateUtil.getSession().getTransaction().commit();
                return listOfTrainingProgramToPatients;
        }
        q.setParameter(type, value);
        ObservableList<TrainingProgramToPatient> listOfTrainingProgramToPatients = FXCollections.observableList(q.list());
        HibernateUtil.getSession().getTransaction().commit();
        return listOfTrainingProgramToPatients;
    }

    public void updateOrInsertIfNotExists(TrainingProgramToPatient tp)
    {
        try
        {
            TrainingProgramToPatient trainingProgramToPatient;
            if ((trainingProgramToPatient = this.read(tp.getTrainingProgram(), tp.getPatient())) != null)
            {
                HibernateUtil.getSession().getTransaction().begin();
                HibernateUtil.getSession().merge(tp);
                HibernateUtil.getSession().getTransaction().commit();
                //needs to update
            }
            else
            {
                HibernateUtil.getSession().getTransaction().begin();
                this.create(tp);
                HibernateUtil.getSession().getTransaction().commit();
            }
        }
        catch(Exception e)
        {

        }
    }
}