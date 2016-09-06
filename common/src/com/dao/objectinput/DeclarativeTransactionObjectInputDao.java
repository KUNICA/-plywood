package com.dao.objectinput;

import com.dao.DaoCriteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.util.List;

/**
 * Created by user on 20.08.2016.
 */
@Named
public class DeclarativeTransactionObjectInputDao extends DaoCriteria<Object> implements IObjectTransactInputDao{

    @Autowired
    public DeclarativeTransactionObjectInputDao(SessionFactory sessionFactory) {
        super(sessionFactory);      // Конструирует DAO
    }

    @Override
    public void inputObject(Object object) {
        if (object instanceof List) {
            inputObject((List) object);
        } else {
            //currentSession().merge(object);
        }
    }

    public void inputObject(List objects) {
        Session session = null;
        Transaction tx = null;
        try {
            session = currentSession();
            tx = session.beginTransaction();
            for (Object listItem : objects) {
                currentSession().merge(listItem);
            }
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
    }


}
