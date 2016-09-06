package com.controller.product;

import com.entity.Type;
import com.services.account.IPersonalDataService;
import com.services.view.ViewProductServiceImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

import static com.entity.Type.*;

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

    @RequestMapping(value="/product/{id}")
    public String show(@PathVariable("id") Long id , Model model) {
        Type type = viewProductService.getProductType(id);
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("listPlywood",viewProductService.getProduct(type,id));
        model.addAttribute("personalData",personalDataService.getPersonalData(userName)) ;
        return "view" + type;
    }
}
