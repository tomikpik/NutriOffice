package cz.fel.ds.database.dao;

import cz.fel.ds.database.model.Exercise;
import cz.fel.ds.util.HibernateUtil;
import javafx.collections.ObservableList;
import org.hibernate.Query;

/**
 * Created by Tom on 15. 5. 2015.
 */
public class ExerciseDAO
{
    public int create(Exercise exercise)
    {
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().save(exercise);
        HibernateUtil.getSession().getTransaction().commit();
        return exercise.getExcerciseId();
    }

    public Exercise read(int id)
    {
        HibernateUtil.getSession().beginTransaction();
        Exercise exercise;
        exercise = (Exercise) HibernateUtil.getSession().get(Exercise.class, id);
        HibernateUtil.getSession().getTransaction().commit();
        return exercise;
    }

    public boolean update(Exercise exercise)
    {
        HibernateUtil.getSession().beginTransaction();
        try
        {
            HibernateUtil.getSession().update(exercise);
            HibernateUtil.getSession().getTransaction().commit();
        }
        catch (Exception ex)
        {
            return false;
        }
        return true;
    }

    public boolean delete(Exercise exercise)
    {
        HibernateUtil.getSession().beginTransaction();
        try
        {
            HibernateUtil.getSession().delete(exercise);
            HibernateUtil.getSession().getTransaction().commit();
        }
        catch (Exception ex)
        {
            return false;
        }
        return true;
    }

    public ObservableList<Exercise> selectObjectsTo(String type, Object value)
    {
        HibernateUtil.getSession().beginTransaction();
        Query q = null;
        switch(type)
        {
            case "excerciseId":
                q =  HibernateUtil.getSession().createQuery("SELECT c from Exercise c where c.excerciseId=:excerciseId");
                break;

            case "name":
                q =  HibernateUtil.getSession().createQuery("SELECT c from Exercise c where c.name=:name");
                break;
            case "kjkgmin":
                q =  HibernateUtil.getSession().createQuery("SELECT c from Exercise c where c.kjkgmin=:kjkgmin");
                break;
            default:
                q =  HibernateUtil.getSession().createQuery("SELECT c from Exercise c");
                ObservableList<Exercise> listOfExercises = (ObservableList<Exercise>) q.list();
                HibernateUtil.getSession().getTransaction().commit();
                return listOfExercises;
        }
        q.setParameter(type, value);
        ObservableList<Exercise> listOfExercises = (ObservableList<Exercise>) q.list();
        HibernateUtil.getSession().getTransaction().commit();
        return listOfExercises;
    }
}
