package com.dao.shopingcart;

import com.dao.DaoCriteria;
import com.entity.Product;
import com.entity.ShoppingCart;
import org.hibernate.*;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 30.08.2016.
 */
@Named("shopingCartProductDao")
public class ShopingCartProductDao extends DaoCriteria<Product> implements ShopingCartProductDaoImpl {

    protected Criteria getCriteria(Session session){
        return createCriteria(session);
    }

    @Autowired
    public ShopingCartProductDao(SessionFactory sessionFactory) {
        super(sessionFactory);      // Конструирует DAO
    }

    public List getSumShoppingCartProduct(String userName){
        Session session = null;
        Transaction tx = null;
        List list = new ArrayList();

        try {
            session = currentSession();
            tx = session.beginTransaction();

            DetachedCriteria dc = DetachedCriteria.forClass(ShoppingCart.class)
                    .add(Restrictions.eq("username",userName))
                    .add(Restrictions.isNull("actual"))
                    .add(Restrictions.isNotNull("check"))
                    .setProjection(Projections.property("productId"));

            list =  getCriteria(session).createAlias("photos","photo")
                    .add(Restrictions.isNull("photo.operationOut"))
                    .add(Subqueries.propertyIn("id", dc))
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();


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
}
