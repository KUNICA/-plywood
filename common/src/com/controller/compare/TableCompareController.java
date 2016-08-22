package com.controller.compare;


import org.springframework.web.bind.annotation.RequestMapping;
import com.services.account.IPersonalDataService;
import com.services.compare.CompareTableServiceImpl;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

/**
 * Created by user on 20.08.2016.
 */
@Controller
@RequestMapping("/compare")
public class TableCompareController {
    @Inject
    private CompareTableServiceImpl compareTableService;

    @Inject
    IPersonalDataService personalDataService;

    @RequestMapping(value="/table/{productId}")
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public String showHome(@PathVariable("productId") long productId , Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("tableCompare",compareTableService.getProducts(userName,productId));
        model.addAttribute("personalData",personalDataService.getPersonalData(userName)) ;
        return "tableCompare";
    }
}
