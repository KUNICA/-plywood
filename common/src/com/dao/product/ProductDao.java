package com.dao.product;

import com.dao.DaoCriteria;
import com.entity.Product;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Named;
import java.util.List;

/**
 * Created by user on 20.08.2016.
 */
@Named("productDao")
@Component
public class ProductDao extends DaoCriteria<Product> implements ProductDaoImpl {

    protected Criteria getCriteriaProducts(Session session){
        session.enableFetchProfile("mediaProfile");
        return createCriteria(session)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .add(Restrictions.isNull("operOut"));
    }

    @Autowired
    public ProductDao(SessionFactory sessionFactory) {
        super(sessionFactory);      // Конструирует DAO
    }


    @Override
    public List getProducts(List productsIdList) {
        Session session = null;
        Transaction tx = null;
        List  productsList = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();

            productsList = getCriteriaProducts(session)
                    .add(Restrictions.in("id",productsIdList))
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
        return productsList;
    }

    @Override
    public Object getProduct(Long productsId) {
        Session session = null;
        Transaction tx = null;
        Object  product = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();

            product = getCriteriaProducts(session)
                    .add(Restrictions.eq("id",productsId))
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
        return product;
    }

    @Override
    public List getProducts(String userName) {
        Session session = null;
        Transaction tx = null;
        List  products = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();

            products = getCriteriaProducts(session)
                    .createAlias("operIn","operIn")
                    .add(Restrictions.eq("operIn.userName",userName))
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
        return products;
    }

    @Override
    public Object getProduct(String productId) {
        Session session = null;
        Transaction tx = null;
        Object  product = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();

            product = getCriteriaProducts(session)
                    .add(Restrictions.eq("productId",productId))
                    .setMaxResults(1).uniqueResult();
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
        return product;
    }

    @Override
    public List getLastProducts(int count) {
        Session session = null;
        Transaction tx = null;
        List  products = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();

            products = createCriteria(session)
                    .createAlias("operIn","operIn")
                    .add(Restrictions.isNull("operOut"))
                    .addOrder(Order.desc("operIn.dateOper"))
                    .setMaxResults(count)
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
        return products;
    }

    @Override
    public List getViewsProducts(int count) {
        Session session = null;
        Transaction tx = null;
        List  products = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();

            products = createCriteria(session)
                    .add(Restrictions.isNull("operOut"))
                    .addOrder(Order.desc("views"))
                    .setMaxResults(count)
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
        return products;
    }
}
