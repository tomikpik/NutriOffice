package cz.fel.ds.database.dao;

import cz.fel.ds.database.model.MealScheduleChange;
import cz.fel.ds.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Query;

/**
 * Created by Barush on 15. 5. 2015.
 */
public class MealScheduleChangeDAO
{
    public int create(MealScheduleChange mealScheduleChange)
    {
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().save(mealScheduleChange);
        HibernateUtil.getSession().flush();
        HibernateUtil.getSession().getTransaction().commit();
        return mealScheduleChange.getMealScheduleChangeId();
    }

    public MealScheduleChange read(int id)
    {
        HibernateUtil.getSession().beginTransaction();
        MealScheduleChange mealScheduleChange;
        mealScheduleChange = (MealScheduleChange) HibernateUtil.getSession().get(MealScheduleChange.class, id);
        HibernateUtil.getSession().flush();
        HibernateUtil.getSession().getTransaction().commit();
        return mealScheduleChange;
    }

    public boolean update(MealScheduleChange mealScheduleChange)
    {
        HibernateUtil.getSession().beginTransaction();
        try
        {
            HibernateUtil.getSession().update(mealScheduleChange);
            HibernateUtil.getSession().flush();
            HibernateUtil.getSession().getTransaction().commit();
        }
        catch (Exception ex)
        {
            return false;
        }
        return true;
    }

    public boolean delete(MealScheduleChange mealScheduleChange)
    {
        HibernateUtil.getSession().beginTransaction();
        try
        {
            HibernateUtil.getSession().delete(mealScheduleChange);
            HibernateUtil.getSession().flush();
            HibernateUtil.getSession().getTransaction().commit();
        }
        catch (Exception ex)
        {
            return false;
        }
        return true;
    }

    public ObservableList<MealScheduleChange> selectObjectsTo(String type, Object value)
    {
        HibernateUtil.getSession().beginTransaction();
        Query q = null;
        switch(type)
        {
            case "mealScheduleChangeId":
                q =  HibernateUtil.getSession().createQuery("SELECT c from MealScheduleChange c where c.mealScheduleChangeId=:mealScheduleChangeId");
                break;
            case "mealSchedule":
                q =  HibernateUtil.getSession().createQuery("SELECT c from MealScheduleChange c where c.mealSchedule=:mealSchedule");
                break;
            case "patient":
                q =  HibernateUtil.getSession().createQuery("SELECT c from MealScheduleChange c where c.patient=:patient");
                break;
            case "meal":
                q =  HibernateUtil.getSession().createQuery("SELECT c from MealScheduleChange c where c.meal=:meal");
                break;
            default:
                q =  HibernateUtil.getSession().createQuery("SELECT c from MealScheduleChange c");
                ObservableList<MealScheduleChange> listOfMealScheduleChanges = FXCollections.observableList(q.list());
                HibernateUtil.getSession().flush();
                HibernateUtil.getSession().getTransaction().commit();
                return listOfMealScheduleChanges;
        }
        q.setParameter(type, value);
        ObservableList<MealScheduleChange> listOfMealScheduleChanges = FXCollections.observableList(q.list());
        HibernateUtil.getSession().flush();
        HibernateUtil.getSession().getTransaction().commit();
        return listOfMealScheduleChanges;
    }
}
