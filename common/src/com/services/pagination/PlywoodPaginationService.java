package com.services.pagination;

import com.dao.pagination.ProductPaginationDaoImpl;
import com.dataweb.IntervalPagination;
import com.dataweb.MenuParametrs;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by user on 27.08.2016.
 */
@Named("plywoodPaginationService")
public class PlywoodPaginationService extends ProductPaginationService implements ProductPaginationServiceImpl{
    @Inject
    @Named("plywoodPaginationDao")
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
        MenuParametrs param = new MenuParametrs();
        return getParametr(param);
    }
}
