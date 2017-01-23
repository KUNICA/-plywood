package com.services.admin;

import java.util.List;

/**
 * Created by user on 20.08.2016.
 */
public interface ProductServiceImpl {
    List getProducts(String userName);
    List getPlywoods(String userName);
    List getParticleboards(String userName);
    boolean removeProduct(long productId,String userName);
    List getLastProducts(int count);
    List getViewsProducts(int count);
}
