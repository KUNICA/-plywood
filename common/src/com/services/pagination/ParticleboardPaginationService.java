package com.services.pagination;

import com.dao.pagination.ProductPaginationDaoImpl;
import com.dataweb.Interval;
import com.dataweb.IntervalPagination;
import com.dataweb.MenuParametrs;
import com.dataweb.ParticleboardsMenuParametrs;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by user on 27.08.2016.
 */
@Named("particleboardPaginationService")
public class ParticleboardPaginationService extends ProductPaginationService implements ProductPaginationServiceImpl {

    @Inject
    @Named("particleboardPaginationDao")
    private ProductPaginationDaoImpl particleboardPaginationDao;

    @Override
    public List getObjects(IntervalPagination data) {
        return particleboardPaginationDao.getObjects(data);
    }

    public List getObjects(Interval data){
        return particleboardPaginationDao.getObjects(data);
    }


    @Override
    public Long getCountObjects(IntervalPagination data) {
        return particleboardPaginationDao.getCountObjects(data);
    }


    public Object getParametrObjects() {
        ParticleboardsMenuParametrs param = new ParticleboardsMenuParametrs();
        param.setMinPrice((Double)particleboardPaginationDao.getCountMin("price"));
        param.setMaxPrice((Double)particleboardPaginationDao.getCountMax("price"));

        param.setListLength(particleboardPaginationDao.getFields("length"));

        param.setListWidth(particleboardPaginationDao.getFields("width"));

        param.setListDepth(particleboardPaginationDao.getFields("depth"));
        return getParametr(param);
    }

    @Override
    public Long getCountAllObjects() {
        return particleboardPaginationDao.getCountAllObjects();
    }
}
