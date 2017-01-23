package com.services.pagination;

import com.dao.pagination.ProductPaginationDaoImpl;
import com.dataweb.Interval;
import com.dataweb.IntervalPagination;
import com.dataweb.MenuParametrs;
import com.dataweb.PlywoodMenuParametrs;
import com.entity.Grade;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
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

    public List getObjects(Interval data){
        return productPaginationDao.getObjects(data);
    }

    @Override
    public Long getCountObjects(IntervalPagination data) {
        return productPaginationDao.getCountObjects(data);
    }

    public Object getParametrObjects() {
        PlywoodMenuParametrs param = new PlywoodMenuParametrs();
        param.setMinPrice((Double)productPaginationDao.getCountMin("price"));
        param.setMaxPrice((Double)productPaginationDao.getCountMax("price"));

        param.setListLength(productPaginationDao.getFields("length"));

        param.setListWidth(productPaginationDao.getFields("width"));

        param.setListDepth(productPaginationDao.getFields("depth"));

        param.setListGrades(Grade.values());

        return getParametr(param);
    }


    @Override
    public Long getCountAllObjects() {
        return productPaginationDao.getCountAllObjects();
    }
}
