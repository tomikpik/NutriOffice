package database.dao;

import database.model.Food;
import util.HibernateUtil;

/**
 * Created by Tom on 15. 5. 2015.
 */
public class FoodDAO {
    public int create(Food food){
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().save(food);
        HibernateUtil.getSession().getTransaction().commit();
        return food.getFoodId();
    }
    public Food read(int id){
        HibernateUtil.getSession().beginTransaction();
        Food food;
        food = (Food) HibernateUtil.getSession().get(Food.class, id);
        HibernateUtil.getSession().getTransaction().commit();
        return food;
    }
    public boolean update(Food food){
        return true;
    }
    public boolean delete(Food food){
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().delete(food);
        HibernateUtil.getSession().getTransaction().commit();
        return true;
    }
}
