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
    @Inject
    private ProductDaoImpl productDaoImpl;

    @Inject
    protected SaveOrUpdateObjectInputServiceImpl saveOrUpdateObjectInputService;

    @Inject
    private RemoveServiceImpl removeService;

    public List getProducts(String userName){
        return productDaoImpl.getProducts(userName);
    }

    @Override
    public boolean removeProduct(long productId,String userName) {
        Product product = (Product)productDaoImpl.getProduct(productId);
        removeService.remove(product,userName);
        saveOrUpdateObjectInputService.inputObject(product);
        return true;
    }

}
