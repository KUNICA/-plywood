package com.services.pagination;

import com.dao.pagination.ProductPaginationDaoImpl;
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
    private ProductPaginationDaoImpl productPaginationDao;

    @Override
    public List getObjects(IntervalPagination data) {
        return productPaginationDao.getObjects(data);
    }

    @Override
    public Long getCountObjects(IntervalPagination data) {
        return productPaginationDao.getCountObjects(data);
    }

    public Object getParametrObjects() {
        ParticleboardsMenuParametrs param = new ParticleboardsMenuParametrs();
        param.setMinLaminated((Long)productPaginationDao.getCountMin("laminated"));
        param.setMaxLaminated((Long)productPaginationDao.getCountMax("laminated"));
        return getParametr(param);
    }
}
