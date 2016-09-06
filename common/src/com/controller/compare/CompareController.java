package com.controller.compare;


import com.services.account.UserServiceImpl;
import com.services.compare.CompareServiceImpl;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * Created by user on 20.08.2016.
 */
@Controller
@RequestMapping("/compare")
public class CompareController {
    @Inject
    private CompareServiceImpl compareService;
    @Inject
    private UserServiceImpl userService;

    @RequestMapping(value = "/isProduct/{productId}",
            method = RequestMethod.GET)
    public @ResponseBody
    boolean isProduct(@PathVariable("productId") long productId) {
        String userName = userService.getUserName();
        return userName != null && compareService.isProductId(userName, productId);
    }

    @RequestMapping(value = "/save/{id}",
            method = RequestMethod.POST, consumes="application/json", produces="application/json",
            headers = {"Accept=text/xml, application/json"})
    public @ResponseBody
    boolean setCompareProduct(@PathVariable("id") long id) {
        String userName = userService.getUserName();
        return compareService.setCompareProduct(userName,id);
    }

    @RequestMapping(value = "/remove/{id}",
            method = RequestMethod.POST, consumes="application/json", produces="application/json",
            headers = {"Accept=text/xml, application/json"})
    public @ResponseBody
    boolean removeCompareProduct(@PathVariable("id") long id) {
        String userName = userService.getUserName();
        return compareService.removeCompareProduct(userName,id);
    }
}
