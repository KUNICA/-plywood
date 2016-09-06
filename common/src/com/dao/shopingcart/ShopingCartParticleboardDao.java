package com.dao.shopingcart;

import com.entity.Particleboard;
import com.entity.Plywood;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.util.List;

/**
 * Created by user on 30.08.2016.
 */
@Named("shopingCartParticleboardDao")
public class ShopingCartParticleboardDao extends ShopingCartProductDao implements ShopingCartProductDaoImpl{

    @Autowired
    public ShopingCartParticleboardDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    protected Criteria getCriteria(Session session){
        return session.createCriteria(Particleboard.class);
    }

}
