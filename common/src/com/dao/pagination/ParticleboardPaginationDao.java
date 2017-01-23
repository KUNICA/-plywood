package com.dao.pagination;

import com.dataweb.IntervalPagination;
import com.dataweb.ParticleboardIntervalPagination;
import com.dataweb.PlywoodIntervalPagination;
import com.entity.Particleboard;
import com.entity.Product;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;

/**
 * Created by user on 27.08.2016.
 */
@Named("particleboardPaginationDao")
public class ParticleboardPaginationDao extends ProductPaginationDao implements ProductPaginationDaoImpl{

    protected Criteria getCriteriaProducts(Session session){
        session.enableFetchProfile("mediaProfile");
        return session.createCriteria(Particleboard.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .add(Restrictions.isNull("operOut"));
    }

    @Autowired
    public ParticleboardPaginationDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    protected Criteria getWereCriteriaParam(Criteria criteria,IntervalPagination data){
        criteria = super.getWereCriteriaParam(criteria, data);
        if(data instanceof ParticleboardIntervalPagination) {
            ParticleboardIntervalPagination dataParticleboard = (ParticleboardIntervalPagination) data;
            if(dataParticleboard.getSanded()!=null){
                criteria.add(Restrictions.eq("sanded",dataParticleboard.getSanded()));
            }
        }

      return criteria;
    }

}
