package cz.fel.ds.database.dao;

import cz.fel.ds.database.model.Exercise;
import cz.fel.ds.util.HibernateUtil;
import javafx.collections.FXCollections;
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
        return exercise.getExerciseId();
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
    {//"from Entity e where e.name like 'e%'");
        HibernateUtil.getSession().beginTransaction();
        Query q = null;
        System.out.println(type+" "+value);
        switch(type)
        {
            case "excerciseId":
                q =  HibernateUtil.getSession().createQuery("SELECT c from Exercise c where c.exerciseId=:excerciseId");
                break;

            case "name":
                q =  HibernateUtil.getSession().createQuery("SELECT c from Exercise c where c.name=:name");
                break;

            case "nameStartsWith":
                q =  HibernateUtil.getSession().createQuery("SELECT c from Exercise c where c.name like concat(:nameStartsWith, '%') ");
                break;

            case "kjkgmin":
                q =  HibernateUtil.getSession().createQuery("SELECT c from Exercise c where c.kjkgmin=:kjkgmin");
                break;
            default:
                q =  HibernateUtil.getSession().createQuery("SELECT c from Exercise c");
                ObservableList<Exercise> listOfExercises = FXCollections.observableList(q.list());
                HibernateUtil.getSession().getTransaction().commit();
                return listOfExercises;
        }
        q.setParameter(type, value);
        ObservableList<Exercise> listOfExercises = FXCollections.observableList(q.list());
        HibernateUtil.getSession().getTransaction().commit();
        return listOfExercises;
    }
}
