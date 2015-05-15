package database.dao;

import database.model.ExcerciseToTrainingProgram;
import util.HibernateUtil;

/**
 * Created by Tom on 15. 5. 2015.
 */
public class ExcerciseToTrainingProgramDAO {
    public int create(ExcerciseToTrainingProgram excerciseToTrainingProgram){
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().save(excerciseToTrainingProgram);
        HibernateUtil.getSession().getTransaction().commit();
        return 1;
    }
    public ExcerciseToTrainingProgram read(int id){
        HibernateUtil.getSession().beginTransaction();
        ExcerciseToTrainingProgram excerciseToTrainingProgram;
        excerciseToTrainingProgram = (ExcerciseToTrainingProgram) HibernateUtil.getSession().get(ExcerciseToTrainingProgram.class, id);
        HibernateUtil.getSession().getTransaction().commit();
        return excerciseToTrainingProgram;
    }
    public boolean update(ExcerciseToTrainingProgram excerciseToTrainingProgram){
        return true;
    }
    public boolean delete(ExcerciseToTrainingProgram excerciseToTrainingProgram){
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().delete(excerciseToTrainingProgram);
        HibernateUtil.getSession().getTransaction().commit();
        return true;
    }
}
