package com.services.view;

import com.entity.Type;

/**
 * Created by user on 02.09.2016.
 */
public interface ViewProductServiceImpl {
    Type getProductType(Long id);
    Object getProduct(Type type,Long id);
}
