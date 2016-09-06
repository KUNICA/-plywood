package com.controller.pagination;

import com.dataweb.*;
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
import javax.inject.Named;
import java.io.IOException;
import java.util.List;

/**
 * Created by user on 20.08.2016.
 */
@Controller
@RequestMapping("/pagination")
public class PaginationController {

    @Inject
    @Named("productPaginationService")
    private ProductPaginationServiceImpl productPaginationService;

    @Inject
    @Named("particleboardPaginationService")
    private ProductPaginationServiceImpl particleboardPaginationService;

    @Inject
    @Named("plywoodPaginationService")
    private ProductPaginationServiceImpl plywoodPaginationService;

    @Inject
    private ImageService imageService;

    private ResourceLoader resourceLoader = new DefaultResourceLoader();

    @RequestMapping(value = "/countActualObjects",
            method = RequestMethod.POST,
            headers = {"Accept=text/xml, application/json"})
    public @ResponseBody Long getCount(@RequestBody IntervalPagination interval) {
        return productPaginationService.getCountObjects(interval);
    }

    @RequestMapping(value = "/objects",
            method = RequestMethod.POST, consumes="application/json", produces="application/json",
            headers = {"Accept=text/xml, application/json"})
    public @ResponseBody
    List getObjects(@RequestBody IntervalPagination interval) {
        List list = productPaginationService.getObjects(interval);
        return list;
    }

    @RequestMapping(value = "/parametrs", method = RequestMethod.POST, consumes="application/json", produces="application/json",
            headers = {"Accept=text/xml, application/json"})
    public @ResponseBody
    Object getParametrs() {
        return  productPaginationService.getParametrObjects();
    }

    @RequestMapping(value = "/countActualParticleboard",
            method = RequestMethod.POST,
            headers = {"Accept=text/xml, application/json"})
    public @ResponseBody Long getCountParticleboard(@RequestBody ParticleboardIntervalPagination interval) {
        return particleboardPaginationService.getCountObjects(interval);
    }

    @RequestMapping(value = "/particleboards",
    method = RequestMethod.POST, consumes="application/json", produces="application/json",
    headers = {"Accept=text/xml, application/json"})
    public @ResponseBody
    List getParticleboards(@RequestBody ParticleboardIntervalPagination interval) {
        List list = particleboardPaginationService.getObjects(interval);
        return list;
    }

    @RequestMapping(value = "/parametrsParticleboards", method = RequestMethod.POST, consumes="application/json", produces="application/json",
            headers = {"Accept=text/xml, application/json"})
    public @ResponseBody
    Object getParametrsParticleboard() {
        return  particleboardPaginationService.getParametrObjects();
    }

    @RequestMapping(value = "/countActualPlywood",
            method = RequestMethod.POST,
            headers = {"Accept=text/xml, application/json"})
    public @ResponseBody Long getCountPlywood(@RequestBody PlywoodIntervalPagination interval) {
        return plywoodPaginationService.getCountObjects(interval);
    }

    @RequestMapping(value = "/plywoods",
            method = RequestMethod.POST, consumes="application/json", produces="application/json",
            headers = {"Accept=text/xml, application/json"})
    public @ResponseBody
    List getPlywoods(@RequestBody PlywoodIntervalPagination interval) {
        List list = plywoodPaginationService.getObjects(interval);
        return list;
    }

    @RequestMapping(value = "/parametrsPlywoods", method = RequestMethod.POST, consumes="application/json", produces="application/json",
            headers = {"Accept=text/xml, application/json"})
    public @ResponseBody
    Object getParametrsPlywood() {
        return  plywoodPaginationService.getParametrObjects();
    }

    @ResponseBody
    @RequestMapping(value = "/img/{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public Resource getImage(@PathVariable("id") long id) throws IOException {
        Images imagesEntity =  imageService.getImageProduct(id);
        Resource resource = null;
        if(imagesEntity!=null){
            resource = resourceLoader.getResource("images/product/"  + imagesEntity.getImg());
        }
        return resource;
    }

    @ResponseBody
    @RequestMapping(value = "/imgPatch/{id}", method = RequestMethod.GET)
    public UrlImage getImgPatch(@PathVariable("id") long id){
        Images imagesEntity =  imageService.getImageProduct(id);
        return new UrlImage(imagesEntity.getImg());
    }

    @ResponseBody
    @RequestMapping(value = "/galary/imgPatch/{id}", method = RequestMethod.GET)
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
            resource = resourceLoader.getResource("images/product/"  + imagesEntity.getImg());
        }
        return resource;
    }

    @RequestMapping(value = "/galary/images/{id}", method = RequestMethod.GET)
    public @ResponseBody
    List getImages(@PathVariable("id") long id) {
        return imageService.getImages(id);
    }



}
