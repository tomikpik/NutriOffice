package cz.fel.ds.database.dao;

import cz.fel.ds.database.model.Food;
import cz.fel.ds.database.model.MealToFood;
import cz.fel.ds.util.HibernateUtil;
import javafx.collections.ObservableList;
import org.hibernate.Query;

/**
 * Created by Tom on 15. 5. 2015.
 */
public class MealToFoodDAO
{
    public int create(MealToFood mealToFood)
    {
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().save(mealToFood);
        HibernateUtil.getSession().getTransaction().commit();
        return 1;
    }

    public MealToFood read(int id)
    {
        HibernateUtil.getSession().beginTransaction();
        MealToFood mealToFood;
        mealToFood = (MealToFood) HibernateUtil.getSession().get(MealToFood.class, id);
        HibernateUtil.getSession().getTransaction().commit();
        return mealToFood;
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

    public boolean delete(MealToFood food)
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

    public ObservableList<MealToFood> selectObjectsTo(String type, Object value)
    {
        HibernateUtil.getSession().beginTransaction();
        Query q = null;
        switch(type)
        {
            case "meal":
                q =  HibernateUtil.getSession().createQuery("SELECT c from MealToFood c where c.meal=:meal");
                break;
            case "quantity":
                q =  HibernateUtil.getSession().createQuery("SELECT c from MealToFood c where c.quantity=:quantity");
                break;
            case "food":
                q =  HibernateUtil.getSession().createQuery("SELECT c from MealToFood c where c.food=:food");
                break;
            default:
                q =  HibernateUtil.getSession().createQuery("SELECT c from MealToFood c");
                ObservableList<MealToFood> listOfMealToFoods = (ObservableList<MealToFood>) q.list();
                HibernateUtil.getSession().getTransaction().commit();
                return listOfMealToFoods;
        }
        q.setParameter(type, value);
        ObservableList<MealToFood> listOfMealToFoods = (ObservableList<MealToFood>) q.list();
        HibernateUtil.getSession().getTransaction().commit();
        return listOfMealToFoods;
    }
}
