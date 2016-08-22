package com.services.vidio;

import com.dao.video.VideoDaoImpl;
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

    @Override
    public Video getVidio(Long id) {
        return videoDao.getVidio(id);
    }
}
