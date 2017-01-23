package com.services.vidio;

import com.dao.video.VideoDaoImpl;
import com.dataweb.Interval;
import com.dataweb.IntervalVidio;
import com.entity.Video;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by user on 20.08.2016.
 */
@Named
public class VideoService implements VidioServiceImpl{

    @Inject
    private VideoDaoImpl videoDao;

    @Override
    public List getVidios(Long id) {
        return videoDao.getVidios(id);
    }

    public List searchVidios(String str){
        return videoDao.searchVidios(str);
    }

    @Override
    public Video getVidio(Long id) {
        return videoDao.getVidio(id);
    }

    @Override
    public List getCategories() {
        return videoDao.getCategories();
    }

    @Override
    public Long getCount(String section) {
        return videoDao.getCount(section);
    }

    @Override
    public  List getObjects(String category,IntervalVidio interval){
        return videoDao.getObjects(category,interval);
    }

}
