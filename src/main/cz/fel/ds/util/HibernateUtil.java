package cz.fel.ds.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static final Session session = sessionFactory.openSession();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().
                    configure().buildSessionFactory();

        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getSession() {
        return session;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }

}