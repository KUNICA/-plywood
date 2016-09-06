package com.dao.shopingcart;

import com.entity.Plywood;
import com.entity.Product;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.util.List;

/**
 * Created by user on 30.08.2016.
 */
@Named("shopingCartPlywoodDao")
public class ShopingCartPlywoodDao extends ShopingCartProductDao implements ShopingCartProductDaoImpl {

    @Autowired
    public ShopingCartPlywoodDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    protected Criteria getCriteria(Session session){
        return session.createCriteria(Plywood.class);
    }

}
