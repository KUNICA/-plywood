package com.services.pagination;

import com.dao.pagination.ProductPaginationDaoImpl;
import com.dataweb.Interval;
import com.dataweb.IntervalPagination;
import com.dataweb.MenuParametrs;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by user on 20.08.2016.
 */
@Named("productPaginationService")
@Transactional
public class ProductPaginationService implements ProductPaginationServiceImpl {
    @Inject
    @Named("productPaginationDao")
    private ProductPaginationDaoImpl productsEntityPaginationDao;

    @Override
    public List getObjects(IntervalPagination data) {
        return productsEntityPaginationDao.getObjects(data);
    }

    public List getObjects(Interval data){
        return productsEntityPaginationDao.getObjects(data);
    }

    @Override
    public Long getCountObjects(IntervalPagination data) {
        return productsEntityPaginationDao.getCountObjects(data);
    }

    public Object getParametrObjects(){
        MenuParametrs param = new MenuParametrs();
        return getParametr(param);
    }

    @Override
    public Long getCountAllObjects() {
        return productsEntityPaginationDao.getCountAllObjects();
    }

    public Object getParametr(MenuParametrs param) {
        return param;
    }
}
