package com.services.print;

import java.util.List;

/**
 * Created by user on 20.08.2016.
 */
public interface PrintProductsServiceImpl {
    List getListParamProducts(String userName);
    String getImageSrc(String patch);
}
