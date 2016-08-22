package com.services.pagination;

import java.util.List;
import com.entity.Images;

/**
 * Created by user on 20.08.2016.
 */
public interface ImageServiceImpl {
    Images getImageProduct(Long id);
    Images getImageGalary(Long id);
    List getImages(Long productId);
}
