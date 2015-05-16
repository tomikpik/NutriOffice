package cz.fel.ds.database.dao;

import cz.fel.ds.database.model.Exercise;
import cz.fel.ds.database.model.ExerciseToTrainingProgram;
import cz.fel.ds.database.model.TrainingProgram;
import cz.fel.ds.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Query;

/**
 * Created by Tom on 15. 5. 2015.
 */
public class ExerciseToTrainingProgramDAO
{
    public int create(ExerciseToTrainingProgram exerciseToTrainingProgram)
    {
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().save(exerciseToTrainingProgram);
        HibernateUtil.getSession().getTransaction().commit();
        return 1;
    }

    public ExerciseToTrainingProgram read(Exercise exercise, TrainingProgram trainingProgram)
    {
        HibernateUtil.getSession().beginTransaction();
        ExerciseToTrainingProgram exerciseToTrainingProgram = new ExerciseToTrainingProgram();
        exerciseToTrainingProgram.setExercise(exercise);
        exerciseToTrainingProgram.setTrainingProgram(trainingProgram);

        exerciseToTrainingProgram = (ExerciseToTrainingProgram) HibernateUtil.getSession().get(ExerciseToTrainingProgram.class, exerciseToTrainingProgram);
        HibernateUtil.getSession().getTransaction().commit();
        return exerciseToTrainingProgram;
    }

    public boolean update(ExerciseToTrainingProgram exerciseToTrainingProgram)
    {
        HibernateUtil.getSession().beginTransaction();
        try
        {
            HibernateUtil.getSession().update(exerciseToTrainingProgram);
            HibernateUtil.getSession().getTransaction().commit();
        }
        catch (Exception ex)
        {
            return false;
        }
        return true;
    }

    public boolean delete(ExerciseToTrainingProgram exerciseToTrainingProgram)
    {
        HibernateUtil.getSession().beginTransaction();
        try
        {
            HibernateUtil.getSession().delete(exerciseToTrainingProgram);
            HibernateUtil.getSession().getTransaction().commit();
        }
        catch (Exception ex)
        {
            return false;
        }
        return true;
    }

    public ObservableList<ExerciseToTrainingProgram> selectObjectsTo(String type, Object value)
    {
        HibernateUtil.getSession().beginTransaction();
        Query q = null;
        switch(type)
        {
            case "exercise":
                q =  HibernateUtil.getSession().createQuery("SELECT c from ExerciseToTrainingProgram c where c.exercise=:exercise");
                break;

//else if(typeTO.equals("teams_id"))q =  HibernateUtil.getSession().createQuery("select c from Competition as c, Team as team where team in elements(c.teams) and team.id=:teams_id");
            case "trainingProgram":
                q =  HibernateUtil.getSession().createQuery("SELECT c from ExerciseToTrainingProgram c where c.trainingProgram=:trainingProgram");
                break;
            case "duration":
                q =  HibernateUtil.getSession().createQuery("SELECT c from ExerciseToTrainingProgram c where c.duration=:duration");
                break;
            default:
                q =  HibernateUtil.getSession().createQuery("SELECT c from ExerciseToTrainingProgram c");
                ObservableList<ExerciseToTrainingProgram> listOfExercises = FXCollections.observableList(q.list());
                HibernateUtil.getSession().getTransaction().commit();
                return listOfExercises;
        }
        q.setParameter(type, value);
        ObservableList<ExerciseToTrainingProgram> listOfExercises = FXCollections.observableList(q.list());
        HibernateUtil.getSession().getTransaction().commit();
        return listOfExercises;
    }

}
