package com.dao.product;

import com.entity.Particleboard;
import com.entity.Plywood;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Named;
import java.util.List;

/**
 * Created by user on 23.08.2016.
 */
@Named("particleboardDao")
@Component
public class ParticleboardDao extends ProductDao implements ProductDaoImpl {

    protected Criteria getCriteriaProducts(Session session){
        session.enableFetchProfile("mediaProfile");
        return session.createCriteria(Particleboard.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .add(Restrictions.isNull("operOut"));
    }

    @Autowired
    public ParticleboardDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
