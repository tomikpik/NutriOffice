package cz.fel.ds.database.dao;

import cz.fel.ds.database.model.Food;
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
public class FoodDAO
{
    public int create(Food food)
    {
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().save(food);
        HibernateUtil.getSession().flush();
        HibernateUtil.getSession().getTransaction().commit();
        return food.getFoodId();
    }

    public Food read(int id)
    {
        HibernateUtil.getSession().beginTransaction();
        Food food;
        food = (Food) HibernateUtil.getSession().get(Food.class, id);
        HibernateUtil.getSession().flush();
        HibernateUtil.getSession().getTransaction().commit();
        return food;
    }

    public boolean update(Food food)
    {
        HibernateUtil.getSession().beginTransaction();
        try
        {
            HibernateUtil.getSession().update(food);
            HibernateUtil.getSession().flush();
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
            HibernateUtil.getSession().flush();
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
        ObservableList<Food> listOfFoods;
        switch(type)
        {
            case "foodId":
                q =  HibernateUtil.getSession().createQuery("SELECT c from Food c where c.foodId=:foodId");
                break;
            case "foodName":
                q =  HibernateUtil.getSession().createQuery("SELECT c from Food c where c.foodName=:foodName");
                break;

            case "nameStartsWith":
                Criteria crit = HibernateUtil.getSession().createCriteria(Food.class);
                crit.add(Restrictions.ilike("foodName", "%" + value + "%"));
                listOfFoods = FXCollections.observableList(crit.list());
                HibernateUtil.getSession().flush();
                HibernateUtil.getSession().getTransaction().commit();
                return listOfFoods;
                //q =  HibernateUtil.getSession().createQuery("SELECT c from Food c where c.foodName like concat(:nameStartsWith, '%') ");
               // break;

            case "energyValue":
                q =  HibernateUtil.getSession().createQuery("SELECT c from Food c where c.energyValue=:energyValue");
                break;
            default:
                q =  HibernateUtil.getSession().createQuery("SELECT c from Food c");
                listOfFoods = FXCollections.observableList(q.list());
                HibernateUtil.getSession().flush();
                HibernateUtil.getSession().getTransaction().commit();
                return listOfFoods;
        }
        q.setParameter(type, value);
        listOfFoods = FXCollections.observableList(q.list());
        HibernateUtil.getSession().flush();
        HibernateUtil.getSession().getTransaction().commit();
        System.out.println(listOfFoods.size());
        return listOfFoods;
    }
}
