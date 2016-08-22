package com.controller.pagination;

import com.dataweb.IntervalPagination;
import com.dataweb.MenuParametrs;
import com.dataweb.UrlImage;
import com.entity.Images;
import com.services.pagination.ImageService;
import com.services.pagination.ProductPaginationServiceImpl;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

/**
 * Created by user on 20.08.2016.
 */
@Controller
@RequestMapping("/pagination")
public class PaginationController {

    @Inject
    private ProductPaginationServiceImpl productEntityPaginationService;

    @Inject
    private ImageService imageService;

    private ResourceLoader resourceLoader = new DefaultResourceLoader();

    @RequestMapping(value = "/countActualObjects",
            method = RequestMethod.POST,
            headers = {"Accept=text/xml, application/json"})
    public @ResponseBody Long getCount(@RequestBody IntervalPagination interval) {
        return productEntityPaginationService.getCountObjects(interval.getStart(),interval.getEnd(),
                interval.getMinPrice(),interval.getMaxPrice(),interval.getPersons(),
                interval.getBadrooms(),interval.getBathrooms());
    }

    @RequestMapping(value = "/objects",
            method = RequestMethod.POST, consumes="application/json", produces="application/json",
            headers = {"Accept=text/xml, application/json"})
    public @ResponseBody
    List getObjects(@RequestBody IntervalPagination interval) {
        List list = productEntityPaginationService.getObjects(interval.getStart(),interval.getEnd(),
                interval.getMinPrice(),interval.getMaxPrice(),interval.getPersons(),
                interval.getBadrooms(),interval.getBathrooms());
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/img/{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public Resource getImage(@PathVariable("id") long id) throws IOException {
        Images imagesEntity =  imageService.getImageProduct(id);
        Resource resource = null;
        if(imagesEntity!=null){
            resource = resourceLoader.getResource("images/products/"  + imagesEntity.getImg());
        }
        return resource;
    }

    @ResponseBody
    @RequestMapping(value = "/imgPatch/{id}", method = RequestMethod.POST)
    public UrlImage getImgPatch(@PathVariable("id") long id){
        Images imagesEntity =  imageService.getImageProduct(id);
        return new UrlImage(imagesEntity.getImg());
    }

    @ResponseBody
    @RequestMapping(value = "/galary/imgPatch/{id}", method = RequestMethod.POST)
    public UrlImage getImgGalaryPatch(@PathVariable("id") long id) throws IOException {
        Images imagesEntity =  imageService.getImageGalary(id);
        return new UrlImage(imagesEntity.getImg());
    }

    @ResponseBody
    @RequestMapping(value = "/galary/img/{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public Resource getImageGalary(@PathVariable("id") long id) throws IOException {
        Images imagesEntity =  imageService.getImageGalary(id);
        Resource resource = null;
        if(imagesEntity!=null){
            resource = resourceLoader.getResource("images/products/"  + imagesEntity.getImg());
        }
        return resource;
    }

    @RequestMapping(value = "/galary/images/{id}", method = RequestMethod.GET)
    public @ResponseBody
    List getImages(@PathVariable("id") long id) {
        return imageService.getImages(id);
    }

    @RequestMapping(value = "/parametrs", method = RequestMethod.POST, consumes="application/json", produces="application/json",
            headers = {"Accept=text/xml, application/json"})
    public @ResponseBody
    MenuParametrs getParametrs() {
        return  productEntityPaginationService.getParametrObjects();
    }


}
