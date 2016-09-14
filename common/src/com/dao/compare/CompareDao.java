package com.dao.compare;

import com.dao.DaoCriteria;
import com.entity.Type;
import com.entity.UsersFilds;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Named;

/**
 * Created by user on 20.08.2016.
 */
@Named
@Component
public class CompareDao extends DaoCriteria<UsersFilds> implements CompareDaoImpl{

    @Autowired
    public CompareDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Object getCompare(String userName, Type type) {
        Session session = null;
        Transaction tx = null;
        Object compare = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();
            compare = createCriteria(session)
                    .add(Restrictions.eq("username",userName))
                    .add(Restrictions.eq("type",type))
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

    @Override
    public Object getCompareCount(String userName, Type type) {
        Session session = null;
        Transaction tx = null;
        Object compare = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();
            compare = createCriteria(session)
                    .add(Restrictions.eq("username",userName))
                    .add(Restrictions.eq("type",type)).setProjection(Projections.rowCount())
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
