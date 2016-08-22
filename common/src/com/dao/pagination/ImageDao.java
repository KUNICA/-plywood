package com.dao.pagination;

import com.entity.Images;
import org.hibernate.*;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Named;
import java.util.List;

/**
 * Created by user on 20.08.2016.
 */
@Named("imageDao")
@Component
public class ImageDao  implements ImagesDaoImpl{


    private SessionFactory sessionFactory;

    private Session currentSession() {           // Извлекает текущий
        return sessionFactory.getCurrentSession(); // сеанс из фабрики
    }

    private Criteria getCriteria(Session session, Long id){
        return session.createCriteria(Images.class)
                .add(Restrictions.isNull("operationOut"))
                .add(Restrictions.eq("produtcId",id));
    }

    @Autowired
    public ImageDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;      // Конструирует DAO
    }


    public Object getObject(Long id) {
        Session session = null;
        Transaction tx = null;
        Object image = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();

            image = getCriteria(session,id)
                    .add(Restrictions.eq("main",true)).uniqueResult();

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


    public List getImages(Long productId) {
        Session session = null;
        Transaction tx = null;
        List listImage = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();

            listImage = getCriteria(session,productId).list();

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
        return listImage;
    }
}
