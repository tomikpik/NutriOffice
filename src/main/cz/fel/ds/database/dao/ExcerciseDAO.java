package cz.fel.ds.database.dao;

import cz.fel.ds.database.model.Excercise;
import cz.fel.ds.util.HibernateUtil;

/**
 * Created by Tom on 15. 5. 2015.
 */
public class ExcerciseDAO {
    public int create(Excercise excercise){
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().save(excercise);
        HibernateUtil.getSession().getTransaction().commit();
        return excercise.getExcerciseId();
    }
    public Excercise read(int id){
        HibernateUtil.getSession().beginTransaction();
        Excercise excercise;
        excercise = (Excercise) HibernateUtil.getSession().get(Excercise.class, id);
        HibernateUtil.getSession().getTransaction().commit();
        return excercise;
    }
    public boolean update(Excercise excercise){
        return true;
    }
    public boolean delete(Excercise excercise){
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().delete(excercise);
        HibernateUtil.getSession().getTransaction().commit();
        return true;
    }
}
