package com.dao.video;

import com.dao.DaoCriteria;
import com.dataweb.Interval;
import com.dataweb.IntervalVidio;
import com.entity.Product;
import com.entity.Video;
import com.google.common.base.Strings;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
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

    public List searchVidios(String str){
        Session session = null;
        Transaction tx = null;
        List listVideo = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();

            listVideo = getCriteria(session).add(Restrictions.eq("headline",str)).list();

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

    @Override
    public List getCategories() {
        Session session = null;
        Transaction tx = null;
        List listCategories = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();

            listCategories = getCriteria(session).setProjection(Projections.distinct(Projections.property("section"))).list();

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
        return listCategories;
    }

    @Override
    public Long getCount(String section) {
        Session session = null;
        Transaction tx = null;
        Long count =0L;

        try {
            session = currentSession();
            tx = session.beginTransaction();

            Criteria criteria = getCriteria(session);
            if(!Strings.isNullOrEmpty(section) && !section.equals("All")){
                criteria = criteria.add(Restrictions.eq("section",section));
            }
            count = (Long)criteria.setProjection(Projections.rowCount()).uniqueResult();

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
        return count;
    }

    public List getObjects(String category,IntervalVidio data) {

        Session session = null;
        Transaction tx = null;
        List video = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();

            Criteria criteria = getCriteria(session);
            if(!Strings.isNullOrEmpty(category) && !category.equals("All")){
                criteria = criteria.add(Restrictions.eq("section",category));
            }

            if(!Strings.isNullOrEmpty(data.getPageSearch())){
                criteria = criteria.add(Restrictions.like("headline","%" + data.getPageSearch() +  "%"));
            }

            video = criteria.setFirstResult(data.getStart().intValue()).addOrder(Order.desc("data"))
                    .setMaxResults(data.getEnd().intValue()).list();


            tx.commit();
        }catch (HibernateException e) {
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
}
