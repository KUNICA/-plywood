package com.controller.video;

import com.services.vidio.VidioServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

/**
 * Created by user on 20.08.2016.
 */
@Controller
@RequestMapping("/video")
public class VideoController {

    @Inject
    private VidioServiceImpl videoService;

    @ResponseBody
    @RequestMapping(value = "/vid/{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public String getVidio(@PathVariable("id") long id) throws IOException {
        return  videoService.getVidio(id).getLink();
    }

    @RequestMapping(value = "/videos/{id}", method = RequestMethod.GET)
    public @ResponseBody
    List getVidios(@PathVariable("id") long id) {
        return videoService.getVidios(id);
    }

}
