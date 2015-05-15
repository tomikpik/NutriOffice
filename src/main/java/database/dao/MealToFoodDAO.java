package database.dao;

import database.model.MealToFood;
import util.HibernateUtil;

/**
 * Created by Tom on 15. 5. 2015.
 */
public class MealToFoodDAO {
    public int create(MealToFood mealToFood){
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().save(mealToFood);
        HibernateUtil.getSession().getTransaction().commit();
        return 1;
    }
    public MealToFood read(int id){
        HibernateUtil.getSession().beginTransaction();
        MealToFood mealToFood;
        mealToFood = (MealToFood) HibernateUtil.getSession().get(MealToFood.class, id);
        HibernateUtil.getSession().getTransaction().commit();
        return mealToFood;
    }
    public boolean update(MealToFood mealToFood){
        return true;
    }
    public boolean delete(MealToFood mealToFood){
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().delete(mealToFood);
        HibernateUtil.getSession().getTransaction().commit();
        return true;
    }
}
