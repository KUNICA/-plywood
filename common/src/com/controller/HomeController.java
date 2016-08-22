package com.controller;

import com.entity.Offer;
import com.entity.OperationType;
import com.entity.Operations;
import com.services.SaveOrUpdateObjectInputServiceImpl;
import com.services.account.IPersonalDataService;
import com.services.offer.OfferServiceImpl;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by user on 20.08.2016.
 */
@Controller
public class HomeController {

    @Inject
    IPersonalDataService personalDataService;

    @Inject
    SaveOrUpdateObjectInputServiceImpl saveOrUpdateObjectInputServiceImpl;

    @Inject
    OfferServiceImpl offerServiceImpl;

    @RequestMapping("/home")
    public String showHome(Model model, HttpSession session) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            model.addAttribute("personalData",personalDataService.getPersonalData(userName)) ;
        }
        return "home";
    }

    @RequestMapping("/authorize")
    public void authorize(Model model, HttpServletResponse httpServletResponse) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            model.addAttribute("personalData",personalDataService.getPersonalData(userName)) ;
        }
        httpServletResponse.setHeader("Location", "/home");
    }

    @RequestMapping("/killSession")
    public String killSession(HttpServletRequest request, Model model, HttpSession session) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            Assert.notNull(request, "HttpServletRequest required");
            session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            SecurityContextHolder.clearContext();
        }
        return "home";
    }

    @RequestMapping("/home/remove")
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public String remove() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Offer offer = (Offer)offerServiceImpl.getOffer(userName);
        Operations operationOut = new Operations();
        operationOut.setDateOper(new Date());
        operationOut.setTypeOper(OperationType.OPERATION_OUT);
        operationOut.setUserName(userName);
        offer.setOperationOut(operationOut);
        saveOrUpdateObjectInputServiceImpl.inputObject(offer);
        return "home";
    }


    @RequestMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @RequestMapping("/registration")
    public String showRegistrationForm(Model model) {


        return "registerNewUser";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerSpitter( Model model) {

        return "registrationCompleted";
    }

}
