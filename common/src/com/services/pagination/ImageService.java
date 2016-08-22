package com.services.pagination;

import com.dao.ObjectDaoImpl;
import com.dao.pagination.ImagesDaoImpl;
import com.entity.Images;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by user on 20.08.2016.
 */
@Named
@Service
public class ImageService implements ImageServiceImpl {

    @Named("imageDao")
    @Inject
    private ImagesDaoImpl imageDao;

    @Named("gallaryDao")
    @Inject
    private ObjectDaoImpl gallaryDao;

    @Override
    public Images getImageProduct(Long id) {
        return (Images)imageDao.getObject(id);
    }

    @Override
    public Images getImageGalary(Long id) {
        return (Images)gallaryDao.getObject(id);
    }

    public List getImages(Long productId) {
        return imageDao.getImages(productId);
    }
}
