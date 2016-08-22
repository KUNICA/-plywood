package com.services.compare;

/**
 * Created by user on 20.08.2016.
 */
public interface CompareServiceImpl {
    boolean isProductId(String userName,Long productId);
    boolean setCompareProduct(String userName,Long productId);
    boolean removeCompareProduct(String userName, Long productId);
}
