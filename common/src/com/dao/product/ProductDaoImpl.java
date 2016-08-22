package com.dao.product;

import java.util.List;

/**
 * Created by user on 20.08.2016.
 */
public interface ProductDaoImpl {
    List getProducts(List productsIdList);
    Object getProduct(Long productsId);
    List getProducts(String userName);
}
