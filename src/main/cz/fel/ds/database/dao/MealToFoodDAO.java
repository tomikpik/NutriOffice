package cz.fel.ds.database.dao;

import cz.fel.ds.database.model.Food;
import cz.fel.ds.database.model.Meal;
import cz.fel.ds.database.model.MealToFood;
import cz.fel.ds.util.HibernateUtil;
import javafx.collections.FXCollections;
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

    public MealToFood read(Meal meal, Food food)
    {
        HibernateUtil.getSession().beginTransaction();
        MealToFood mealToFood = new MealToFood();
        mealToFood.setMeal(meal);
        mealToFood.setFood(food);

        mealToFood = (MealToFood) HibernateUtil.getSession().get(MealToFood.class, mealToFood);
        HibernateUtil.getSession().getTransaction().commit();
        return mealToFood;
    }

    public boolean update(MealToFood mealToFood)
    {
        HibernateUtil.getSession().beginTransaction();
        try
        {
            HibernateUtil.getSession().update(mealToFood);
            HibernateUtil.getSession().getTransaction().commit();
        }
        catch (Exception ex)
        {
            return false;
        }
        return true;
    }

    public boolean delete(MealToFood mealToFood)
    {
        HibernateUtil.getSession().beginTransaction();
        try
        {
            HibernateUtil.getSession().delete(mealToFood);
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
                ObservableList<MealToFood> listOfMealToFoods = FXCollections.observableList(q.list());
                HibernateUtil.getSession().getTransaction().commit();
                return listOfMealToFoods;
        }
        q.setParameter(type, value);
        ObservableList<MealToFood> listOfMealToFoods = FXCollections.observableList(q.list());
        HibernateUtil.getSession().getTransaction().commit();
        return listOfMealToFoods;
    }
}
