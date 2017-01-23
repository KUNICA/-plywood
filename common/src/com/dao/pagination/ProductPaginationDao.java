package com.dao.pagination;

import com.dao.DaoCriteria;
import com.dataweb.Interval;
import com.dataweb.IntervalPagination;
import com.entity.Images;
import com.entity.Product;
import com.entity.Product;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.util.List;

/**
 * Created by user on 20.08.2016.
 */
@Named("productPaginationDao")
public class ProductPaginationDao extends DaoCriteria<Product> implements ProductPaginationDaoImpl {


    protected Criteria getCriteriaProducts(Session session){
        session.enableFetchProfile("mediaProfile");
        return createCriteria(session)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .add(Restrictions.isNull("operOut"));
    }

    protected Criteria getWereCriteriaParam(Criteria criteria,IntervalPagination data){
        criteria.add(Restrictions.ge("price",data.getMinPrice()))
                .add(Restrictions.le("price",data.getMaxPrice()));
        if(!data.getLengthAll()){
            criteria.add(Restrictions.ge("length",data.getLength()))
                    .add(Restrictions.le("length",data.getLength()));
        }
        if(!data.getWidthAll()){
            criteria.add(Restrictions.ge("width",data.getWidth()))
                    .add(Restrictions.le("width",data.getWidth()));
        }
        if(!data.getDepthAll()){
            criteria.add(Restrictions.ge("depth",data.getDepth()))
                    .add(Restrictions.le("depth",data.getDepth()));
        }

        return criteria;
    }

    @Autowired
    public ProductPaginationDao(SessionFactory sessionFactory) {
        super(sessionFactory);      // Конструирует DAO
    }

    @Override
    public List getObjects(IntervalPagination data) {

        Session session = null;
        Transaction tx = null;
        List  productsEntityList = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();

            session.enableFetchProfile("mediaProfile");

            Criteria criteria = getCriteriaProducts(session);
            criteria = getWereCriteriaParam(criteria,data);
            productsEntityList  = criteria.setFirstResult(data.getStart().intValue())
                    .setMaxResults(data.getEnd().intValue()).list();

            // productsEntityList = getCriteriaProducts(session)
            //       .setFirstResult(start)
            //     .setMaxResults(end)
            //   .list();

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
        return productsEntityList;
    }

    @Override
    public Long getCountObjects(IntervalPagination data) {
        Session session = null;
        Transaction tx = null;
        Long count =0L;

        try {
            session = currentSession();
            tx = session.beginTransaction();
            session.enableFetchProfile("mediaProfile");

            Criteria criteria = getCriteriaProducts(session);
            criteria = getWereCriteriaParam(criteria,data);
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

    @Override
    public List getObjects(Interval data) {

        Session session = null;
        Transaction tx = null;
        List  productsEntityList = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();

            session.enableFetchProfile("mediaProfile");

            Criteria criteria = getCriteriaProducts(session);
            productsEntityList  = criteria.setFirstResult(data.getStart().intValue())
                    .setMaxResults(data.getEnd().intValue()).list();

            // productsEntityList = getCriteriaProducts(session)
            //       .setFirstResult(start)
            //     .setMaxResults(end)
            //   .list();

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
        return productsEntityList;
    }

    @Override
    public Long getCountAllObjects() {
        Session session = null;
        Transaction tx = null;
        Long count =0L;

        try {
            session = currentSession();
            tx = session.beginTransaction();
            session.enableFetchProfile("mediaProfile");

            Criteria criteria = getCriteriaProducts(session);
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

    @Override
    public Object getCountMin(String field) {
        Session session = null;
        Transaction tx = null;
        Object count = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();
            count = getCriteriaProducts(session)
                    .setProjection(Projections.min(field))
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
        return count;
    }

    public List getFields(String field) {
        Session session = null;
        Transaction tx = null;
        List list = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();
            Criteria criteria = getCriteriaProducts(session);
            list = criteria.setProjection(Projections.distinct(Projections.property(field)))
                    .addOrder(Order.asc(field))
                    .list();

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
        return list;
    }

    @Override
    public Object getCountMax(String field) {
        Session session = null;
        Transaction tx = null;
        Object count = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();
            count = getCriteriaProducts(session)
                    .setProjection(Projections.max(field))
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
        return count;
    }

}
