package com.dao;

import com.entity.Video;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Named;

/**
 * Created by user on 20.08.2016.
 */
@Named
@Component
public class SaveOrUpdateObjectInputDao extends DaoCriteria<Object> implements IObjectInputDao {


    @Autowired
    public SaveOrUpdateObjectInputDao(SessionFactory sessionFactory) {
        super(sessionFactory);      // Конструирует DAO
    }

    public Object inputObject(Object object) throws HibernateException {

        Session session = null;
        Transaction tx = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(object);
            tx.commit();
        }
        catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        finally {
            if (session != null && session.isOpen())
                session.close();
        }
        return object;
    }

}
