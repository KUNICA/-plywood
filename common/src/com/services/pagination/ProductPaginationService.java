package com.services.pagination;

import com.dao.pagination.ProductPaginationDaoImpl;
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

    @Override
    public Long getCountObjects(IntervalPagination data) {
        return productsEntityPaginationDao.getCountObjects(data);
    }

    public Object getParametrObjects(){
        MenuParametrs param = new MenuParametrs();
        return getParametr(param);
    }

    public Object getParametr(MenuParametrs param) {
        param.setMinPrice((Double)productsEntityPaginationDao.getCountMin("price"));
        param.setMaxPrice((Double)productsEntityPaginationDao.getCountMax("price"));

        param.setMinLength((Long)productsEntityPaginationDao.getCountMin("length"));
        param.setMaxLength((Long)productsEntityPaginationDao.getCountMax("length"));

        param.setMinWidth((Long)productsEntityPaginationDao.getCountMin("width"));
        param.setMaxWidth((Long)productsEntityPaginationDao.getCountMax("width"));

        param.setMinDepth((Long)productsEntityPaginationDao.getCountMin("depth"));
        param.setMaxDepth((Long)productsEntityPaginationDao.getCountMax("depth"));
        return param;
    }
}
