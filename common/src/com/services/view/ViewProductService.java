package com.services.view;

import com.dao.product.ProductDao;
import com.entity.Product;
import com.entity.Type;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by user on 02.09.2016.
 */
@Named
public class ViewProductService implements ViewProductServiceImpl {

    @Inject
    @Named("productDao")
    private ProductDao productDao;

    @Inject
    @Named("plywoodDao")
    private ProductDao plywoodDao;

    @Inject
    @Named("particleboardDao")
    private ProductDao particleboardDao;


    @Override
    public Type getProductType(Long id) {
        Product product = (Product)productDao.getProduct(id);
        return product.getType();
    }


    @Override
    public Object getProduct(Type type,Long id) {
        Object product = null;
        switch(type){
            case Particleboard:
                product = particleboardDao.getProduct(id);
                break;
            case Plywood:
                product = plywoodDao.getProduct(id);
                break;
        }
        return product;
    }
}
