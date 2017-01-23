package com.dao.video;

import com.dataweb.Interval;
import com.dataweb.IntervalVidio;
import com.entity.Video;
import java.util.List;

/**
 * Created by user on 20.08.2016.
 */
public interface VideoDaoImpl {
    List getVidios(Long id);
    List searchVidios(String str);
    Video getVidio(Long id);
    List getCategories();
    Long getCount(String section);
    List getObjects(String category,IntervalVidio interval);
}
