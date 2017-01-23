package com.controller.video;

import com.dataweb.Interval;
import com.dataweb.IntervalVidio;
import com.entity.Type;
import com.services.vidio.VidioServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
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

    @RequestMapping("/section")
    public String videoSection(Model model, HttpSession session){
        return "video";
    }

    @ResponseBody
    @RequestMapping(value="/categories", method = RequestMethod.POST)
    public  List videoCategories() {
        return videoService.getCategories();
    }

    @RequestMapping(value = "/count/{category}",
            method = RequestMethod.POST,
            headers = {"Accept=text/xml, application/json"})
    public @ResponseBody Long getCountParticleboard(@PathVariable("category") String category) {
        return videoService.getCount(category);
    }

    @RequestMapping(value = "/objects/{category}",
            method = RequestMethod.POST, consumes="application/json", produces="application/json",
            headers = {"Accept=text/xml, application/json"})
    public @ResponseBody
    List getObjects(@PathVariable("category") String category,@RequestBody IntervalVidio interval) {
        return videoService.getObjects(category,interval);
    }


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
