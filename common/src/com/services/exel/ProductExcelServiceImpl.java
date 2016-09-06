package com.services.exel;

import com.exel.ExelParserImpl;
import com.exel.ProductFormatExelExeption;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by user on 20.08.2016.
 */
public interface ProductExcelServiceImpl {
    boolean addProduct(InputStream inputStream, String userName,String productStr) throws NumberFormatException, InvalidFormatException,InvocationTargetException, IllegalArgumentException,ProductFormatExelExeption, IOException;
}
