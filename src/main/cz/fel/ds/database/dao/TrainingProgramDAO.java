package cz.fel.ds.database.dao;

import cz.fel.ds.database.model.TrainingProgram;
import cz.fel.ds.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Query;

/**
 * Created by Tom on 15. 5. 2015.
 */
public class TrainingProgramDAO
{
    public int create(TrainingProgram trainingProgram)
    {
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().save(trainingProgram);
        HibernateUtil.getSession().getTransaction().commit();
        return trainingProgram.getTrainingProgramId();
    }

    public TrainingProgram read(int id)
    {
        HibernateUtil.getSession().beginTransaction();
        TrainingProgram trainingProgram = (TrainingProgram) HibernateUtil.getSession().get(TrainingProgram.class, id);
        HibernateUtil.getSession().getTransaction().commit();
        return trainingProgram;
    }


    public boolean update(TrainingProgram trainingProgram)
    {
        HibernateUtil.getSession().beginTransaction();
        try
        {
            HibernateUtil.getSession().update(trainingProgram);
            HibernateUtil.getSession().getTransaction().commit();
        }
        catch (Exception ex)
        {
            return false;
        }
        return true;
    }

    public boolean delete(TrainingProgram trainingProgram)
    {
        HibernateUtil.getSession().beginTransaction();
        try
        {
            HibernateUtil.getSession().delete(trainingProgram);
            HibernateUtil.getSession().getTransaction().commit();
        }
        catch (Exception ex)
        {
            return false;
        }
        return true;
    }

    public ObservableList<TrainingProgram> selectObjectsTo(String type, Object value)
    {
        HibernateUtil.getSession().beginTransaction();
        Query q = null;
        switch(type)
        {
            case "trainingProgramId":
                q =  HibernateUtil.getSession().createQuery("SELECT c from TrainingProgram c where c.trainingProgramId=:trainingProgramId");
                break;
            case "name":
                q =  HibernateUtil.getSession().createQuery("SELECT c from TrainingProgram c where c.name=:name");
                break;

            case "nameStartsWith":
                q =  HibernateUtil.getSession().createQuery("SELECT c from TrainingProgram c where c.name like concat(:nameStartsWith, '%') ");
                break;

            case "type":
                q =  HibernateUtil.getSession().createQuery("SELECT c from TrainingProgram c where c.type=:type");
                break;
            default:
                q =  HibernateUtil.getSession().createQuery("SELECT c from TrainingProgram c");
                ObservableList<TrainingProgram> listOfTrainingPrograms = FXCollections.observableList(q.list());
                return listOfTrainingPrograms;
        }
        q.setParameter(type, value);
        ObservableList<TrainingProgram> listOfTrainingPrograms = FXCollections.observableList(q.list());
        HibernateUtil.getSession().getTransaction().commit();
        return listOfTrainingPrograms;
    }

}