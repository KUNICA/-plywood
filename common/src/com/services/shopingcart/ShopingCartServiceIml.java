package com.services.shopingcart;

import com.dataweb.Product;
import com.entity.ShoppingCart;

import java.util.List;

/**
 * Created by user on 20.08.2016.
 */
public interface ShopingCartServiceIml {
    Object getShoppingCart(String userName, long productId);
    Object checkProduct(Product product, String Username);
    boolean isProductCheck(long productId);
    List getSumShoppingCart(String userName);
    List getShoppingCarts(String userName);
    Long plusProduct(ShoppingCart product);
    Long minusProduct(ShoppingCart product);
    Double getTotalPrice(List list);
}

