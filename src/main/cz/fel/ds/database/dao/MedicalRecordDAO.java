package cz.fel.ds.database.dao;

import cz.fel.ds.database.model.MedicalRecord;
import cz.fel.ds.util.HibernateUtil;

/**
 * Created by Tom on 15. 5. 2015.
 */
public class MedicalRecordDAO {
    public int create(MedicalRecord medicalRecord){
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().save(medicalRecord);
        HibernateUtil.getSession().getTransaction().commit();
        return 1;
    }
    public MedicalRecord read(int id){
        HibernateUtil.getSession().beginTransaction();
        MedicalRecord medicalRecord;
        medicalRecord = (MedicalRecord) HibernateUtil.getSession().get(MedicalRecord.class, id);
        HibernateUtil.getSession().getTransaction().commit();
        return medicalRecord;
    }
    public boolean update(MedicalRecord medicalRecord){
        return true;
    }
    public boolean delete(MedicalRecord medicalRecord){
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().delete(medicalRecord);
        HibernateUtil.getSession().getTransaction().commit();
        return true;
    }

}
