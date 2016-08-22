package com.dao.pagination;

import com.entity.Product;
import com.entity.Product;
import org.hibernate.*;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.util.List;

/**
 * Created by user on 20.08.2016.
 */
@Named
public class ProductPaginationDao implements ProductPaginationDaoImpl {

    private SessionFactory sessionFactory;

    private Criteria getCriteriaProducts(Session session){
        session.enableFetchProfile("mediaProfile");
        return session.createCriteria(Product.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .add(Restrictions.isNull("operationOut"));
    }

    @Autowired
    public ProductPaginationDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;      // Конструирует DAO
    }

    private Session currentSession() {           // Извлекает текущий
        return sessionFactory.getCurrentSession(); // сеанс из фабрики
    }

    @Override
    public List getObjects(int start, int end,double minPrice,double maxPrice,Long persons,Long badrooms,Long bathrooms) {

        Session session = null;
        Transaction tx = null;
        List  productsEntityList = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();

            session.enableFetchProfile("mediaProfile");
            Query query = session.createQuery("from Product product where product.operOut is null and " +
                    "product.price >= :priceMin and product.price <= :priceMax  " )
                    .setFirstResult(start)
                    .setMaxResults(end);

            query.setDouble("priceMin", minPrice);
            query.setDouble("priceMax", maxPrice);

            productsEntityList = query.list();

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
    public Long getCountObjects(int start, int end,double minPrice,double maxPrice,Long persons,Long badrooms,Long bathrooms) {
        Session session = null;
        Transaction tx = null;
        Long count =0L;

        try {
            session = currentSession();
            tx = session.beginTransaction();
            count = (Long)getCriteriaProducts(session)
                    .add(Restrictions.ge("price",minPrice))
                    .add(Restrictions.le("price",maxPrice))
                    .setProjection(Projections.rowCount()).uniqueResult();
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
        Object count =0f;

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
