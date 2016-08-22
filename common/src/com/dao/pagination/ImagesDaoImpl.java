package com.dao.pagination;

import java.util.List;

/**
 * Created by user on 20.08.2016.
 */
public interface ImagesDaoImpl {
    List getImages(Long productId);
    Object getObject(Long id);
}
