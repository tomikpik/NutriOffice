package cz.fel.ds.database.dao;

import cz.fel.ds.database.model.Diet;
import cz.fel.ds.util.HibernateUtil;
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
        HibernateUtil.getSession().getTransaction().commit();
        return diet.getDietId();
    }

    public Diet read(int id)
    {
        HibernateUtil.getSession().beginTransaction();
        Diet diet;
        diet = (Diet) HibernateUtil.getSession().get(Diet.class, id);
        HibernateUtil.getSession().getTransaction().commit();
        return diet;
    }

    public boolean update(Diet diet)
    {
        HibernateUtil.getSession().beginTransaction();
        try
        {
            HibernateUtil.getSession().update(diet);
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
            HibernateUtil.getSession().delete(diet);
            HibernateUtil.getSession().getTransaction().commit();
        }
        catch (Exception ex)
        {
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

            default:
                q =  HibernateUtil.getSession().createQuery("SELECT c from Diet c");
                ObservableList<Diet> listOfDiets = (ObservableList<Diet>) q.list();
                HibernateUtil.getSession().getTransaction().commit();
                return listOfDiets;
        }
        q.setParameter(type, value);
        ObservableList<Diet> listOfDiets = (ObservableList<Diet>) q.list();
        HibernateUtil.getSession().getTransaction().commit();
        return listOfDiets;
    }
}
