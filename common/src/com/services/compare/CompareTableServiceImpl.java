package com.services.compare;

import com.entity.Type;

import java.util.List;

/**
 * Created by user on 20.08.2016.
 */
public interface CompareTableServiceImpl {
    List getProducts(String userName, Type type);
}
