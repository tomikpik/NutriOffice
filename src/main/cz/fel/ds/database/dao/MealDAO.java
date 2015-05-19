package cz.fel.ds.database.dao;

import cz.fel.ds.database.model.Meal;
import cz.fel.ds.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

/**
 * Created by Tom on 15. 5. 2015.
 */
public class MealDAO
{
    public int create(Meal meal)
    {
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().save(meal);
        HibernateUtil.getSession().flush();
        HibernateUtil.getSession().getTransaction().commit();
        return meal.getMealId();
    }

    public Meal read(int id)
    {
        HibernateUtil.getSession().beginTransaction();
        Meal meal;
        meal = (Meal) HibernateUtil.getSession().get(Meal.class, id);
        HibernateUtil.getSession().flush();
        HibernateUtil.getSession().getTransaction().commit();
        return meal;
    }


    public boolean update(Meal meal)
    {
        HibernateUtil.getSession().beginTransaction();
        try
        {
            HibernateUtil.getSession().update(meal);
            HibernateUtil.getSession().flush();
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

            Query q = null;
            q =  HibernateUtil.getSession().createQuery("DELETE from MealToFood mtf where mtf.meal.mealId=:tpid");
            q.setParameter("tpid",meal.getMealId());
            q.executeUpdate();
            HibernateUtil.getSession().delete(meal);
            HibernateUtil.getSession().flush();
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
        ObservableList<Meal> listOfMeals;
        switch(type)
        {
            case "mealId":
                q =  HibernateUtil.getSession().createQuery("SELECT c from Meal c where c.mealId=:mealId");
                break;

            case "mealName":
                q =  HibernateUtil.getSession().createQuery("SELECT c from Meal c where c.mealName=:mealName");
                break;

            case "nameStartsWith":
                Criteria crit = HibernateUtil.getSession().createCriteria(Meal.class);
                crit.add(Restrictions.ilike("mealName","%"+value+"%"));
                listOfMeals = FXCollections.observableList(crit.list());
                HibernateUtil.getSession().flush();
                HibernateUtil.getSession().getTransaction().commit();
                return listOfMeals;
                //q =  HibernateUtil.getSession().createQuery("SELECT c from Meal c where c.mealName like concat(:nameStartsWith, '%') ");
                //break;

            default:
                q =  HibernateUtil.getSession().createQuery("SELECT c from Meal c");
                listOfMeals = FXCollections.observableList(q.list());
                HibernateUtil.getSession().flush();
                HibernateUtil.getSession().getTransaction().commit();
                return listOfMeals;
        }
        q.setParameter(type, value);
        listOfMeals = FXCollections.observableList(q.list());
        HibernateUtil.getSession().flush();
        HibernateUtil.getSession().getTransaction().commit();
        return listOfMeals;
    }
}
