package com.services.vidio;

import com.entity.Video;
import java.util.List;

/**
 * Created by user on 20.08.2016.
 */
public interface VidioServiceImpl {
    List getVidios(Long id);
    Video getVidio(Long id);
}
