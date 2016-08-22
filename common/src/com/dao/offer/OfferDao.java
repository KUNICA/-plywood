package com.dao.offer;

import com.entity.Offer;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;

/**
 * Created by user on 20.08.2016.
 */
@Named
public class OfferDao implements OfferDaoImpl{

    private SessionFactory sessionFactory;

    private Session currentSession() {           // Извлекает текущий
        return sessionFactory.getCurrentSession(); // сеанс из фабрики
    }

    private Criteria getCriteria(Session session){
        return session.createCriteria(Offer.class)
                .add(Restrictions.isNull("operationOut"));
    }

    @Autowired
    public OfferDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;      // Конструирует DAO
    }

    @Override
    public Object getOffer(String userName) {
        Session session = null;
        Transaction tx = null;
        Object offer = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();

            offer = getCriteria(session)
                    .createAlias("operationIn","operIn")
                    .add(Restrictions.eq("operIn.userName",userName))
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
        return offer;
    }
}
