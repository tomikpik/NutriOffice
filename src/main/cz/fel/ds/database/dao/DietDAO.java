package cz.fel.ds.database.dao;

import cz.fel.ds.database.model.Diet;
import cz.fel.ds.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Query;

/**
 * Created by Barush on 15. 5. 2015.
 */
public class DietDAO
{
    public int create(Diet diet)
    {
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().save(diet);
        HibernateUtil.getSession().flush();
        HibernateUtil.getSession().getTransaction().commit();
        return diet.getDietId();
    }

    public Diet read(int id)
    {
        HibernateUtil.getSession().beginTransaction();
        Diet diet;
        diet = (Diet) HibernateUtil.getSession().get(Diet.class, id);
        HibernateUtil.getSession().flush();
        HibernateUtil.getSession().getTransaction().commit();
        return diet;
    }

    public boolean update(Diet diet)
    {
        HibernateUtil.getSession().beginTransaction();
        try
        {
            HibernateUtil.getSession().update(diet);
            HibernateUtil.getSession().flush();
            HibernateUtil.getSession().getTransaction().commit();
        }
        catch (Exception ex)
        {
            return false;
        }
        return true;
    }

    public boolean delete(Diet diet)
    {
        HibernateUtil.getSession().beginTransaction();
        try
        {
            Query q = null;
            q =  HibernateUtil.getSession().createQuery("DELETE from MealSchedule ms where ms.diet.dietId=:tpid");
            q.setParameter("tpid",diet.getDietId());
            q.executeUpdate();
            HibernateUtil.getSession().delete(diet);
            HibernateUtil.getSession().flush();
            HibernateUtil.getSession().getTransaction().commit();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public ObservableList<Diet> selectObjectsTo(String type, Object value)
    {
        HibernateUtil.getSession().beginTransaction();
        Query q = null;
        switch(type)
        {
            case "dietId":
                q =  HibernateUtil.getSession().createQuery("SELECT c from Diet c where c.dietId=:dietId");
                break;

            case "name":
                q =  HibernateUtil.getSession().createQuery("SELECT c from Diet c where c.name=:name");
                break;

            case "nameStartsWith":
                q =  HibernateUtil.getSession().createQuery("SELECT c from Diet c where c.name like concat(:nameStartsWith, '%') ");
                break;

            default:
                q =  HibernateUtil.getSession().createQuery("SELECT c from Diet c");
                ObservableList<Diet> listOfDiets = FXCollections.observableList(q.list());
                HibernateUtil.getSession().flush();
                HibernateUtil.getSession().getTransaction().commit();
                return listOfDiets;
        }
        q.setParameter(type, value);
        ObservableList<Diet> listOfDiets = FXCollections.observableList(q.list());
        HibernateUtil.getSession().flush();
        HibernateUtil.getSession().getTransaction().commit();
        return listOfDiets;
    }
}
