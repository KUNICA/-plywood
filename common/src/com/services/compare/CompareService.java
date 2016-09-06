package com.services.compare;

import com.dao.compare.CompareDaoImpl;
import com.dao.product.ProductDao;
import com.entity.Product;
import com.entity.UsersFilds;
import com.services.SaveOrUpdateObjectInputServiceImpl;
import com.services.compare.ui.SearchFields;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by user on 20.08.2016.
 */
@Named
@Service
public class CompareService  implements CompareServiceImpl{
    @Inject
    private CompareDaoImpl compareDao;

    @Inject
    @Named("productDao")
    private ProductDao productDao;

    @Inject
    private SaveOrUpdateObjectInputServiceImpl saveService;

    private boolean isProductCompare(UsersFilds usersFilds, Long productId){
        return SearchFields.getProductsList(usersFilds).contains(productId);
    }

    @Override
    public boolean isProductId(String userName, Long productId) {
        Product product = (Product)productDao.getProduct(productId);
        UsersFilds usersFildsEntity = (UsersFilds)compareDao.getCompare(userName,product.getType());
        return isProductCompare(usersFildsEntity, productId);
    }

    @Override
    public boolean setCompareProduct(String userName, Long productId) {
        Product product = (Product)productDao.getProduct(productId);
        UsersFilds usersFildsEntity = (UsersFilds)compareDao.getCompare(userName,product.getType());
        if( usersFildsEntity==null){
            usersFildsEntity = new UsersFilds();
            usersFildsEntity.setCompare(productId.toString());
            usersFildsEntity.setUsername(userName);
            usersFildsEntity.setType(product.getType());
            saveService.inputObject(usersFildsEntity);
        }else if(!isProductCompare(usersFildsEntity,productId)){
            if(usersFildsEntity.getCompare().length()>0) {
                usersFildsEntity.setCompare(usersFildsEntity.getCompare() + " " + productId.toString());
            } else{
                usersFildsEntity.setCompare(productId.toString());
            }
            saveService.inputObject(usersFildsEntity);
        }
        return true;
    }

    public boolean removeCompareProduct(String userName, Long productId) {
        Product product = (Product)productDao.getProduct(productId);
        UsersFilds usersFildsEntity = (UsersFilds)compareDao.getCompare(userName,product.getType());
        if( usersFildsEntity!=null && isProductCompare(usersFildsEntity,productId)){
            if(usersFildsEntity.getCompare().contains(" ")) {
                String[] fields = usersFildsEntity.getCompare().split(" ");
                if(fields[0].equals(productId.toString())) {
                    usersFildsEntity.setCompare(usersFildsEntity.getCompare().replace(productId.toString() + " ", ""));
                }
                else{
                    usersFildsEntity.setCompare(usersFildsEntity.getCompare().replace(" " + productId.toString(), ""));
                }
            } else if(usersFildsEntity.getCompare().length()>0){
                usersFildsEntity.setCompare("");
            }
            saveService.inputObject(usersFildsEntity);
        }
        return true;
    }

}
