package com.services.compare;

import com.dao.compare.CompareDaoImpl;
import com.dao.product.ProductDaoImpl;
import com.entity.Product;
import com.entity.UsersFilds;
import com.services.compare.ui.SearchFields;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by user on 20.08.2016.
 */
@Named
@Service
public class CompareTableService  implements CompareTableServiceImpl{
    @Inject
    private CompareDaoImpl compareDaoImpl;
    @Inject
    private ProductDaoImpl productDao;

    @Override
    public List getProducts(String userName, Long ProductId) {
        UsersFilds compare = (UsersFilds)compareDaoImpl.getCompare(userName);
        List productsArray =  SearchFields.getProductsList(compare);
        List list = productDao.getProducts(productsArray);
        if(!productsArray.contains(ProductId)) {
            Product ProductsEntity = (Product) productDao.getProduct(ProductId);
            list.add(ProductsEntity);
        }
        return list;
    }
}
