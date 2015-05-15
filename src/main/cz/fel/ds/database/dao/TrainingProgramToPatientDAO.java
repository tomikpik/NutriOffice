package cz.fel.ds.database.dao;

import cz.fel.ds.database.model.TrainingProgramToPatient;
import cz.fel.ds.util.HibernateUtil;

/**
 * Created by Tom on 15. 5. 2015.
 */
public class TrainingProgramToPatientDAO {
    public int create(TrainingProgramToPatient trainingProgramToPatient){
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().save(trainingProgramToPatient);
        HibernateUtil.getSession().getTransaction().commit();
        return 1;
    }
    public TrainingProgramToPatient read(int id){
        HibernateUtil.getSession().beginTransaction();
        TrainingProgramToPatient trainingProgramToPatient;
        trainingProgramToPatient = (TrainingProgramToPatient) HibernateUtil.getSession().get(TrainingProgramToPatient.class, id);
        HibernateUtil.getSession().getTransaction().commit();
        return trainingProgramToPatient;
    }
    public boolean update(TrainingProgramToPatient trainingProgramToPatient){
        return true;
    }
    public boolean delete(TrainingProgramToPatient trainingProgramToPatient){
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().delete(trainingProgramToPatient);
        HibernateUtil.getSession().getTransaction().commit();
        return true;
    }
}