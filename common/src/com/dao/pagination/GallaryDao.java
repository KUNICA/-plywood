package com.dao.pagination;

import com.dao.DaoCriteria;
import com.dao.ObjectDaoImpl;
import com.entity.Images;
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
@Named("gallaryDao")
@Component
public class GallaryDao extends DaoCriteria<Images> implements ObjectDaoImpl {

    @Autowired
    public GallaryDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Object getObject(Long id) {
        Session session = null;
        Transaction tx = null;
        Object image = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();

            image = createCriteria(session)
                    .add(Restrictions.eq("id",id)).add(Restrictions.isNull("operationOut"))
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
        return image;
    }
}
