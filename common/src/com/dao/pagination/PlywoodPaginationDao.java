package com.dao.pagination;

import com.dataweb.IntervalPagination;
import com.dataweb.PlywoodIntervalPagination;
import com.entity.Particleboard;
import com.entity.Plywood;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;

/**
 * Created by user on 27.08.2016.
 */
@Named("plywoodPaginationDao")
public class PlywoodPaginationDao extends ProductPaginationDao implements ProductPaginationDaoImpl{

    protected Criteria getCriteriaProducts(Session session){
        session.enableFetchProfile("mediaProfile");
        return session.createCriteria(Plywood.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .add(Restrictions.isNull("operOut"));
    }

    @Autowired
    public PlywoodPaginationDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    protected Criteria getWereCriteriaParam(Criteria criteria,IntervalPagination data){
        criteria = super.getWereCriteriaParam(criteria, data);
        if(data instanceof PlywoodIntervalPagination){
            PlywoodIntervalPagination dataPlywood = (PlywoodIntervalPagination) data;
            if(dataPlywood.getSanded()!=null){
                criteria.add(Restrictions.eq("sanded",dataPlywood.getSanded()));
            }
            if(dataPlywood.getResistance()!=null){
                criteria.add(Restrictions.eq("waterResistance",dataPlywood.getResistance()));
            }

            if(dataPlywood.getCoating()!=null) {
                criteria.add(Restrictions.eq("coating", dataPlywood.getCoating()));
            }
            if(dataPlywood.getCoating()!=null && dataPlywood.getCoating()
                    && dataPlywood.getMinCoating()!=null && dataPlywood.getMaxCoating()!=null) {
                criteria.add(Restrictions.ge("colorCoating", dataPlywood.getMinCoating()))
                        .add(Restrictions.le("colorCoating", dataPlywood.getMaxCoating()));
            }
        }

        return criteria;
    }

}