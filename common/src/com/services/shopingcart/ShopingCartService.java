package com.services.shopingcart;

import com.dao.shopingcart.ShopingCartDaoIml;
import com.dataweb.Product;
import com.entity.ShoppingCart;
import com.services.SaveOrUpdateObjectInputServiceImpl;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by user on 20.08.2016.
 */
@Named
@Service
public class ShopingCartService implements ShopingCartServiceIml {

    @Inject
    private ShopingCartDaoIml shopingCartDao;

    @Inject
    private SaveOrUpdateObjectInputServiceImpl saveService;

    @Override
    public Object getShoppingCart(String userName,long productId) {
        return shopingCartDao.getShoppingCart(userName,productId);
    }

    @Override
    public Object checkProduct(Product product,String Username) {
        ShoppingCart shoppingCartEntity  = new ShoppingCart();
        shoppingCartEntity.setActual(product.getActual());
        shoppingCartEntity.setCheck(product.getCheck());
        shoppingCartEntity.setProductId(product.getProductId());
        shoppingCartEntity.setId(product.getId());
        shoppingCartEntity.setUsername(product.getUsername());
        return saveService.inputObject(shoppingCartEntity);
    }

    @Override
    public boolean isProductCheck(long productId) {
        return shopingCartDao.isProductCheck(productId);
    }

    public List getSumShoppingCart(String userName){
        return  shopingCartDao.getSumShoppingCart(userName);
    }

    public List getShoppingCarts(String userName){
        return  shopingCartDao.getShoppingCarts(userName);
    }
}
