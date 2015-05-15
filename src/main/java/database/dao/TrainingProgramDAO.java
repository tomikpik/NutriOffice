package database.dao;

import database.model.TrainingProgram;
import util.HibernateUtil;

/**
 * Created by Tom on 15. 5. 2015.
 */
public class TrainingProgramDAO
{
    public int create(TrainingProgram trainingProgram){
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().save(trainingProgram);
        HibernateUtil.getSession().getTransaction().commit();
        return trainingProgram.getTrainingProgramId();
    }
    public TrainingProgram read(int id){
        HibernateUtil.getSession().beginTransaction();
        TrainingProgram trainingProgram = (TrainingProgram) HibernateUtil.getSession().get(TrainingProgram.class, id);
        HibernateUtil.getSession().getTransaction().commit();
        return trainingProgram;
    }
    public boolean update(TrainingProgram trainingProgram){
        return true;
    }

    public boolean delete(TrainingProgram trainingProgram){
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().delete(trainingProgram);
        HibernateUtil.getSession().getTransaction().commit();
        return true;
    }

}