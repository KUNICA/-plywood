package com.dao.shopingcart;

import java.util.List;

/**
 * Created by user on 20.08.2016.
 */
public interface ShopingCartDaoIml {
    Object getShoppingCart(String userName,long productId);
    boolean isProductCheck(long productId);
    List getSumShoppingCart(String userName);
    List getShoppingCarts(String userName);
}
