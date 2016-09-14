package com.dao.shopingcart;

import com.dao.DaoCriteria;
import com.entity.Product;
import com.entity.ShoppingCart;
import com.entity.Type;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

import static org.apache.camel.model.rest.RestParamType.query;

/**
 * Created by user on 20.08.2016.
 */
@Named
@Component
public class ShopingCartDao extends DaoCriteria<ShoppingCart> implements ShopingCartDaoIml {

    private Criteria getCriteria(Session session, long productId){
        return createCriteria(session)
                .add(Restrictions.eq("productId",productId));
    }


    @Autowired
    public ShopingCartDao(SessionFactory sessionFactory) {
        super(sessionFactory);      // Конструирует DAO
    }


    public boolean isProductCheck(long productId){
        Session session = null;
        Transaction tx = null;
        Object shopingCart = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();

            shopingCart = getCriteria(session, productId)
                    .add(Restrictions.isNotNull("check"))
                    .add(Restrictions.isNull("actual"))
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
        return shopingCart != null;
    }

    public Object getShoppingCart(String userName,long productId){
        Session session = null;
        Transaction tx = null;
        Object shopingCart = null;

        try {
            session = currentSession();
            tx = session.beginTransaction();

            shopingCart = getCriteria(session, productId)
                    .add(Restrictions.eq("username",userName))
                    .add(Restrictions.isNull("actual"))
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
        return shopingCart;
    }

    public List getSumShoppingCart(String userName){
        Session session = null;
        Transaction tx = null;
        List list = new ArrayList();

        try {
            session = currentSession();
            tx = session.beginTransaction();
            session.enableFetchProfile("mediaProfile");

            list =  session.createCriteria(ShoppingCart.class,"cart")
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .createAlias("cart.product", "product")
                    .add(Restrictions.eq("cart.username",userName))
                    .add(Restrictions.isNull("cart.actual"))
                    .add(Restrictions.isNotNull("cart.check"))
                    .setProjection(Projections.projectionList()
                            .add(Projections.property("product.price"), "price")
                            .add(Projections.property("product.shortDescription"), "shortDescription")
                            .add(Projections.property("product.name"), "name")
                            .add(Projections.property("cart.count"), "count")
                            .add(Projections.property("product.length"), "length")
                            .add(Projections.property("product.type"), "type")
                            .add(Projections.property("product.width"), "width")
                            .add(Projections.property("product.id"), "id")
                            .add(Projections.property("product.depth"), "depth"))
                    .setResultTransformer(Transformers.aliasToBean(ShopingCartDto.class)).list();

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

    public List getShoppingCarts(String userName){
        Session session = null;
        Transaction tx = null;
        List list = new ArrayList();

        try {
            session = currentSession();
            tx = session.beginTransaction();
            list =  createCriteria(session)
                    .add(Restrictions.eq("username",userName))
                    .add(Restrictions.isNull("actual"))
                    .add(Restrictions.isNotNull("check")).list();

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
