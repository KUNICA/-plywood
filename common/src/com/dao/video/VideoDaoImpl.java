package com.dao.video;

import com.entity.Video;
import java.util.List;

/**
 * Created by user on 20.08.2016.
 */
public interface VideoDaoImpl {
    List getVidios(Long id);
    Video getVidio(Long id);
}
