package com.controller.compare;


import com.entity.Type;
import com.services.account.UserServiceImpl;
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
    private IPersonalDataService personalDataService;

    @Inject
    private UserServiceImpl userService;

    @RequestMapping(value="/table/{type}")
    public String showHome(@PathVariable("type") Type type , Model model) {
        String userName = userService.getUserName();
        model.addAttribute("tableCompare",compareTableService.getProducts(userName,type));
        model.addAttribute("personalData",personalDataService.getPersonalData(userName)) ;
        return "tableCompare" + type;
    }
}
