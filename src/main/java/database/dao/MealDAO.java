package database.dao;

import database.model.Meal;
import util.HibernateUtil;

/**
 * Created by Tom on 15. 5. 2015.
 */
public class MealDAO {
    public int create(Meal meal){
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().save(meal);
        HibernateUtil.getSession().getTransaction().commit();
        return meal.getMealId();
    }
    public Meal read(int id){
        HibernateUtil.getSession().beginTransaction();
        Meal meal;
        meal = (Meal) HibernateUtil.getSession().get(Meal.class, id);
        HibernateUtil.getSession().getTransaction().commit();
        return meal;
    }
    public boolean update(Meal medicalRecord){
        return true;
    }
    public boolean delete(Meal meal){
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().delete(meal);
        HibernateUtil.getSession().getTransaction().commit();
        return true;
    }
}
