package cz.fel.ds.database.dao;

import cz.fel.ds.database.model.MealType;
import cz.fel.ds.util.HibernateUtil;
import javafx.collections.ObservableList;
import org.hibernate.Query;

/**
 * Created by Barush on 15. 5. 2015.
 */
public class MealTypeDAO
{
    public int create(MealType mealType)
    {
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().save(mealType);
        HibernateUtil.getSession().getTransaction().commit();
        return mealType.getMealTypeId();
    }

    public MealType read(int id)
    {
        HibernateUtil.getSession().beginTransaction();
        MealType mealType;
        mealType = (MealType) HibernateUtil.getSession().get(MealType.class, id);
        HibernateUtil.getSession().getTransaction().commit();
        return mealType;
    }

    public boolean update(MealType mealType)
    {
        HibernateUtil.getSession().beginTransaction();
        try
        {
            HibernateUtil.getSession().update(mealType);
            HibernateUtil.getSession().getTransaction().commit();
        }
        catch (Exception ex)
        {
            return false;
        }
        return true;
    }

    public boolean delete(MealType mealType)
    {
        HibernateUtil.getSession().beginTransaction();
        try
        {
            HibernateUtil.getSession().delete(mealType);
            HibernateUtil.getSession().getTransaction().commit();
        }
        catch (Exception ex)
        {
            return false;
        }
        return true;
    }

    public ObservableList<MealType> selectObjectsTo(String type, Object value)
    {
        HibernateUtil.getSession().beginTransaction();
        Query q = null;
        switch(type)
        {
            case "mealTypeId":
                q =  HibernateUtil.getSession().createQuery("SELECT c from MealType c where c.mealTypeId=:mealTypeId");
                break;
            case "mealTypeName":
                q =  HibernateUtil.getSession().createQuery("SELECT c from MealType c where c.mealTypeName=:mealTypeName");
                break;
            case "mealType":
                q =  HibernateUtil.getSession().createQuery("SELECT c from MealType c where c.mealType=:mealType");
                break;
            default:
                q =  HibernateUtil.getSession().createQuery("SELECT c from MealType c");
                ObservableList<MealType> listOfMealTypes = (ObservableList<MealType>) q.list();
                HibernateUtil.getSession().getTransaction().commit();
                return listOfMealTypes;
        }
        q.setParameter(type, value);
        ObservableList<MealType> listOfMealTypes = (ObservableList<MealType>) q.list();
        HibernateUtil.getSession().getTransaction().commit();
        return listOfMealTypes;
    }
}
