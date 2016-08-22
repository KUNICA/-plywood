package com.controller.admin;

import com.exel.ProductFormatExelExeption;
import com.services.account.IPersonalDataService;
import com.services.admin.ProductService;
import com.services.admin.ImageFileServiceImpl;
import com.services.admin.ImageUploadException;
import com.services.exel.ProductExcelServiceImpl;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by user on 20.08.2016.
 */
@Controller
@RequestMapping(value="/admin")
public class AdminController {
    private static final String fileUploadControllerURL = "upload";
    private static final String fileSaveControllerURL = "save";

    @Inject
    ProductExcelServiceImpl productExelService;

    @Inject
    private ProductService productServiceImpl;

    @Inject
    private ImageFileServiceImpl imageFileServiceImpl;

    @Inject
    IPersonalDataService personalDataService;


    @RequestMapping(value="/run")
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public String admin(Model model, HttpSession session) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("fileUploadControllerURL", fileUploadControllerURL);
        model.addAttribute("fileSaveControllerURL", fileSaveControllerURL);
        model.addAttribute("tableInformation",productServiceImpl.getProducts(userName));
        model.addAttribute("personalData",personalDataService.getPersonalData(userName)) ;
        return "admin";
    }

    @RequestMapping(value="/upload", method = RequestMethod.POST)
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public String addProduct(@RequestParam("file") MultipartFile file, Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("tableInformation",productServiceImpl.getProducts(userName));
        model.addAttribute("fileUploadControllerURL", fileUploadControllerURL);
        model.addAttribute("fileSaveControllerURL", fileSaveControllerURL);
        model.addAttribute("personalData",personalDataService.getPersonalData(userName)) ;
        if(file.isEmpty()){
            model.addAttribute("error","Выберите файл");
            return "admin";
        }
        try {
            productExelService.addProduct(file.getInputStream(),userName);
        } catch (ProductFormatExelExeption productFormatExelExeption) {
            model.addAttribute("error",productFormatExelExeption.getFildName());
            return "admin";
        } catch (InvocationTargetException | IOException | NullPointerException | NumberFormatException e) {
            model.addAttribute("error",e.fillInStackTrace());
            return "admin";
        }
        model.addAttribute("error","Файл добавлен");
        return "redirect:/admin/run";
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public String saveImage(@RequestParam("fileImage") MultipartFile file, Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("fileUploadControllerURL", fileUploadControllerURL);
        model.addAttribute("fileSaveControllerURL", fileSaveControllerURL);
        model.addAttribute("tableInformation",productServiceImpl.getProducts(userName));
        model.addAttribute("personalData",personalDataService.getPersonalData(userName)) ;
        if(file.isEmpty()){
            model.addAttribute("errorImage","Выберите файл");
            return "admin";
        }
        try {
            if(!file.isEmpty()) {
                imageFileServiceImpl.validateImage(file);
                imageFileServiceImpl.saveFileImage(file.getOriginalFilename(),file); // Сохранить файл
            }
        } catch (ImageUploadException e) {
            model.addAttribute("errorImage",e.fillInStackTrace());
            return "admin";
        }
        model.addAttribute("errorImage","Файл добавлен");
        return "admin";
    }

    @RequestMapping(value="/remove/{productId}",method = RequestMethod.POST)
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public String remove(@PathVariable("productId") long productId,Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        productServiceImpl.removeProduct(productId,userName);
        model.addAttribute("fileUploadControllerURL", fileUploadControllerURL);
        model.addAttribute("fileSaveControllerURL", fileSaveControllerURL);
        model.addAttribute("tableInformation",productServiceImpl.getProducts(userName));
        model.addAttribute("personalData",personalDataService.getPersonalData(userName)) ;
        return "admin";
    }

    @RequestMapping(value="/products")
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public String showHome( Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("tableInformation",productServiceImpl.getProducts(userName));
        model.addAttribute("personalData",personalDataService.getPersonalData(userName)) ;
        return "admin";
    }

}
