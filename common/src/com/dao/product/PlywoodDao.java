package com.dao.product;

import com.entity.Plywood;
import com.entity.Product;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Named;
import java.util.List;

/**
 * Created by user on 23.08.2016.
 */
@Named("plywoodDao")
@Component
public class PlywoodDao extends ProductDao implements ProductDaoImpl{

    protected Criteria getCriteriaProducts(Session session){
        session.enableFetchProfile("mediaProfile");
        return session.createCriteria(Plywood.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .add(Restrictions.isNull("operOut"));
    }

    @Autowired
    public PlywoodDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
