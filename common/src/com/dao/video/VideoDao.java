package com.dao.video;

import com.dao.DaoCriteria;
import com.entity.Product;
import com.entity.Video;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Named;
import java.util.List;

/**
 * Created by user on 20.08.2016.
 */
@Named
@Component
public class VideoDao extends DaoCriteria<Video> implements VideoDaoImpl{

    private Criteria getCriteria(Session session){
        return createCriteria(session)
                .add(Restrictions.isNull("operationOut"));

    }

    @Autowired
    public VideoDao(SessionFactory sessionFactory) {
        super(sessionFactory);      // Конструирует DAO
    }

    @Override
    public Video getVidio(Long id) {
        Session session = null;
        Transaction tx = null;
        Video video = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();

            video = (Video)getCriteria(session).add(Restrictions.eq("id",id)).uniqueResult();

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
        return video;
    }

    @Override
    public List getVidios(Long productId) {
        Session session = null;
        Transaction tx = null;
        List listVideo = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();

            listVideo = getCriteria(session).add(Restrictions.eq("productId",productId)).list();

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
        return listVideo;
    }
}
