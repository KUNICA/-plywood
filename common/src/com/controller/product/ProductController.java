package com.controller.product;

import com.entity.Product;
import com.entity.Type;
import com.services.SaveOrUpdateObjectInputServiceImpl;
import com.services.account.IPersonalDataService;
import com.services.view.ViewProductServiceImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

/**
 * Created by user on 02.09.2016.
 */
@Controller
@RequestMapping("/viewproduct")
public class ProductController {

    @Inject
    private ViewProductServiceImpl viewProductService;

    @Inject
    private IPersonalDataService personalDataService;

    @Inject
    private SaveOrUpdateObjectInputServiceImpl saveService;

    @RequestMapping(value="/product/{id}")
    public String show(@PathVariable("id") Long id , Model model) {
        Type type = viewProductService.getProductType(id);
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("listPlywood",viewProductService.getProduct(type,id));
        model.addAttribute("personalData",personalDataService.getPersonalData(userName)) ;
        return "view" + type;
    }

    @RequestMapping(value="/add-views/{id}", method = RequestMethod.POST)
    public String addViews(@PathVariable("id") long id,Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Type type = viewProductService.getProductType(id);
        Product product = (Product)viewProductService.getProduct(type,id);
        product.setViews(product.getViews() + 1);
        saveService.inputObject(product);
        return "ok";
    }
}
