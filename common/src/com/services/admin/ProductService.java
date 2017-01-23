package com.services.admin;

import com.dao.product.ProductDaoImpl;
import com.entity.Product;
import com.services.SaveOrUpdateObjectInputServiceImpl;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by user on 20.08.2016.
 */
@Named
public class ProductService implements ProductServiceImpl {

    @Named("productDao")
    @Inject
    private ProductDaoImpl productDaoImpl;

    @Named("plywoodDao")
    @Inject
    private ProductDaoImpl plywoodDao;

    @Named("particleboardDao")
    @Inject
    private ProductDaoImpl particleboardDao;

    @Inject
    protected SaveOrUpdateObjectInputServiceImpl saveOrUpdateObjectInputService;

    @Inject
    private RemoveServiceImpl removeService;

    public List getProducts(String userName){
        return productDaoImpl.getProducts(userName);
    }

    public List getPlywoods(String userName){
        return plywoodDao.getProducts(userName);
    }

    public List getParticleboards(String userName){
        return particleboardDao.getProducts(userName);
    }

    @Override
    public boolean removeProduct(long productId,String userName) {
        Product product = (Product)productDaoImpl.getProduct(productId);
        removeService.remove(product,userName);
        saveOrUpdateObjectInputService.inputObject(product);
        return true;
    }

    @Override
    public List getLastProducts(int count) {
        return productDaoImpl.getLastProducts(count);
    }

    @Override
    public List getViewsProducts(int count) {
        return productDaoImpl.getViewsProducts(count);
    }

}
