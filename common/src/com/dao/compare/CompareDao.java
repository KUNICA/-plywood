package com.dao.compare;

import com.entity.UsersFilds;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Named;

/**
 * Created by user on 20.08.2016.
 */
@Named
@Component
public class CompareDao implements CompareDaoImpl{

    private SessionFactory sessionFactory;

    private Session currentSession() {           // Извлекает текущий
        return sessionFactory.getCurrentSession(); // сеанс из фабрики
    }

    @Autowired
    public CompareDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;      // Конструирует DAO
    }

    @Override
    public Object getCompare(String userName) {
        Session session = null;
        Transaction tx = null;
        Object compare = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();
            compare = session.createCriteria(UsersFilds.class)
                    .add(Restrictions.eq("username",userName))
                    .uniqueResult();

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
        return compare;
    }
}
