package com.services.compare;

import com.dao.compare.CompareDaoImpl;
import com.dao.product.ProductDaoImpl;
import com.entity.Product;
import com.entity.Type;
import com.entity.UsersFilds;
import com.services.compare.ui.SearchFields;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

import static com.entity.Type.Particleboard;

/**
 * Created by user on 20.08.2016.
 */
@Named
@Service
public class CompareTableService  implements CompareTableServiceImpl{
    @Inject
    private CompareDaoImpl compareDaoImpl;
    @Inject
    @Named("productDao")
    private ProductDaoImpl productDao;
    @Inject
    @Named("plywoodDao")
    private ProductDaoImpl plywoodDao;
    @Inject
    @Named("particleboardDao")
    private ProductDaoImpl particleboardDao;

    @Override
    public List getProducts(String userName, Type type) {
        UsersFilds compare = (UsersFilds)compareDaoImpl.getCompare(userName,type);
        List productsArray =  SearchFields.getProductsList(compare);
        switch(type){
            case Particleboard:
                return particleboardDao.getProducts(productsArray);
            case Plywood:
                return plywoodDao.getProducts(productsArray);
        }
        return productDao.getProducts(productsArray);
        /*
        if(!productsArray.contains(productId)) {
            Product ProductsEntity = (Product) productDao.getProduct(productId);
            list.add(ProductsEntity);
        }*/
    }
}
