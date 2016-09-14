package com.dao.pagination;

import com.dataweb.IntervalPagination;

import java.util.List;

/**
 * Created by user on 20.08.2016.
 */
public interface ProductPaginationDaoImpl {
    List getObjects(IntervalPagination data);
    Long getCountObjects(IntervalPagination data);
    Object getCountMax(String field);
    Object getCountMin(String field);
    Long getCountAllObjects();
}
