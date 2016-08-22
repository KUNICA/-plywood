package com.services.compare.ui;

import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.List;

import com.entity.UsersFilds;
import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 20.08.2016.
 */
public class SearchFields {
        public static List getProductsList(UsersFilds compare){
            List productsArray = new ArrayList();
            if(compare!=null && !Strings.isNullOrEmpty(compare.getCompare())){
                String fieldsString = compare.getCompare();
                String[] fields = fieldsString.split(" ");
                for (String it:fields) {
                    Long field = new Long(it);
                    productsArray.add(field);
                }
            }
            return productsArray;
        }
}
