package com.services.pagination;

import com.dao.pagination.ProductPaginationDaoImpl;
import com.dataweb.MenuParametrs;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by user on 20.08.2016.
 */
@Named
@Transactional
public class ProductPaginationService implements ProductPaginationServiceImpl {
    @Inject
    private ProductPaginationDaoImpl productsEntityPaginationDao;

    @Override
    public List getObjects(Long start, Long end ,Double minPrice,Double maxPrice,Long persons,Long badrooms,Long bathrooms) {
        return productsEntityPaginationDao.getObjects(start.intValue(), end.intValue(),minPrice,maxPrice,persons,badrooms,bathrooms);
    }

    @Override
    public Long getCountObjects(Long start, Long end,double minPrice,double maxPrice,Long persons,Long badrooms,Long bathrooms) {
        return productsEntityPaginationDao.getCountObjects(start.intValue(), end.intValue(),minPrice,maxPrice,persons,badrooms,bathrooms);
    }

    @Override
    public MenuParametrs getParametrObjects() {
        MenuParametrs param = new MenuParametrs();
        param.setMinPrice((Double)productsEntityPaginationDao.getCountMin("price"));
        param.setMaxPrice((Double)productsEntityPaginationDao.getCountMax("price"));
        return param;
    }
}
