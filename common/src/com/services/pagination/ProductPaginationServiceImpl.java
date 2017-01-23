package com.services.pagination;

import com.dataweb.Interval;
import com.dataweb.IntervalPagination;
import com.dataweb.MenuParametrs;

import java.util.List;

/**
 * Created by user on 20.08.2016.
 */
public interface ProductPaginationServiceImpl {
    List getObjects(IntervalPagination data);
    List getObjects(Interval data);
    Long getCountObjects(IntervalPagination data);
    Object getParametrObjects();
    Long getCountAllObjects();
}
