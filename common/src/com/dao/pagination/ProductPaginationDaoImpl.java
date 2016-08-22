package com.dao.pagination;

import java.util.List;

/**
 * Created by user on 20.08.2016.
 */
public interface ProductPaginationDaoImpl {
    List getObjects(int start, int end, double minPrice, double maxPrice, Long persons, Long badrooms, Long bathrooms);
    Long getCountObjects(int start, int end,double minPrice,double maxPrice,Long persons,Long badrooms,Long bathrooms);
    Object getCountMax(String field);
    Object getCountMin(String field);
}
