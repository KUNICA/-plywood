package com.exel;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import java.io.InputStream;
import java.util.List;

/**
 * Created by user on 20.08.2016.
 */
public interface ExelParserImpl {
    List getProducts(InputStream inputStream) throws InvalidFormatException, ProductFormatExelExeption;
}
