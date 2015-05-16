package cz.fel.ds.database.dao;

import cz.fel.ds.database.model.Meal;
import cz.fel.ds.util.HibernateUtil;
import javafx.collections.ObservableList;
import org.hibernate.Query;

/**
 * Created by Tom on 15. 5. 2015.
 */
public class MealDAO
{
    public int create(Meal meal)
    {
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().save(meal);
        HibernateUtil.getSession().getTransaction().commit();
        return meal.getMealId();
    }

    public Meal read(int id)
    {
        HibernateUtil.getSession().beginTransaction();
        Meal meal;
        meal = (Meal) HibernateUtil.getSession().get(Meal.class, id);
        HibernateUtil.getSession().getTransaction().commit();
        return meal;
    }


    public boolean update(Meal meal)
    {
        HibernateUtil.getSession().beginTransaction();
        try
        {
            HibernateUtil.getSession().update(meal);
            HibernateUtil.getSession().getTransaction().commit();
        }
        catch (Exception ex)
        {
            return false;
        }
        return true;
    }

    public boolean delete(Meal meal)
    {
        HibernateUtil.getSession().beginTransaction();
        try
        {
            HibernateUtil.getSession().delete(meal);
            HibernateUtil.getSession().getTransaction().commit();
        }
        catch (Exception ex)
        {
            return false;
        }
        return true;
    }

    public ObservableList<Meal> selectObjectsTo(String type, Object value)
    {
        HibernateUtil.getSession().beginTransaction();
        Query q = null;
        switch(type)
        {
            case "mealId":
                q =  HibernateUtil.getSession().createQuery("SELECT c from Meal c where c.mealId=:mealId");
                break;

            case "mealName":
                q =  HibernateUtil.getSession().createQuery("SELECT c from Meal c where c.mealName=:mealName");
                break;
            default:
                q =  HibernateUtil.getSession().createQuery("SELECT c from Meal c");
                ObservableList<Meal> listOfMeals = (ObservableList<Meal>) q.list();
                HibernateUtil.getSession().getTransaction().commit();
                return listOfMeals;
        }
        q.setParameter(type, value);
        ObservableList<Meal> listOfMeals = (ObservableList<Meal>) q.list();
        HibernateUtil.getSession().getTransaction().commit();
        return listOfMeals;
    }
}
