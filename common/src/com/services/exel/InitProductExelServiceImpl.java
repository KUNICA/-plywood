package com.services.exel;

import com.entity.Particleboard;
import com.entity.Plywood;
import com.entity.Product;
import com.exel.ProductExel;
import com.exel.ProductFormatExelExeption;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 23.08.2016.
 */
public interface InitProductExelServiceImpl {
    List initPlywoods(InputStream inputStream, String exelParser) throws InvalidFormatException, ProductFormatExelExeption;
    Product productInstance(String productStr);
    Product getProduct(String productStr, String id);
    void initProduct(Product product, ProductExel productExel, String productStr) throws NumberFormatException;
}
