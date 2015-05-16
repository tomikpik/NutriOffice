package cz.fel.ds.database.dao;

import cz.fel.ds.database.model.Food;
import cz.fel.ds.util.HibernateUtil;
import javafx.collections.ObservableList;
import org.hibernate.Query;

/**
 * Created by Tom on 15. 5. 2015.
 */
public class FoodDAO
{
    public int create(Food food)
    {
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().save(food);
        HibernateUtil.getSession().getTransaction().commit();
        return food.getFoodId();
    }

    public Food read(int id)
    {
        HibernateUtil.getSession().beginTransaction();
        Food food;
        food = (Food) HibernateUtil.getSession().get(Food.class, id);
        HibernateUtil.getSession().getTransaction().commit();
        return food;
    }

    public boolean update(Food food)
    {
        HibernateUtil.getSession().beginTransaction();
        try
        {
            HibernateUtil.getSession().update(food);
            HibernateUtil.getSession().getTransaction().commit();
        }
        catch (Exception ex)
        {
            return false;
        }
        return true;
    }

    public boolean delete(Food food)
    {
        HibernateUtil.getSession().beginTransaction();
        try
        {
            HibernateUtil.getSession().delete(food);
            HibernateUtil.getSession().getTransaction().commit();
        }
        catch (Exception ex)
        {
            return false;
        }
        return true;
    }

    public ObservableList<Food> selectObjectsTo(String type, Object value)
    {
        HibernateUtil.getSession().beginTransaction();
        Query q = null;
        switch(type)
        {
            case "foodId":
                q =  HibernateUtil.getSession().createQuery("SELECT c from Food c where c.foodId=:foodId");
                break;
            case "foodName":
                q =  HibernateUtil.getSession().createQuery("SELECT c from Food c where c.foodName=:foodName");
                break;
            case "energyValue":
                q =  HibernateUtil.getSession().createQuery("SELECT c from Food c where c.energyValue=:energyValue");
                break;
            default:
                q =  HibernateUtil.getSession().createQuery("SELECT c from Food c");
                ObservableList<Food> listOfFoods = (ObservableList<Food>) q.list();
                HibernateUtil.getSession().getTransaction().commit();
                return listOfFoods;
        }
        q.setParameter(type, value);
        ObservableList<Food> listOfFoods = (ObservableList<Food>) q.list();
        HibernateUtil.getSession().getTransaction().commit();
        return listOfFoods;
    }
}
