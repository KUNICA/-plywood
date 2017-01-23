package com.controller;

import com.entity.Offer;
import com.entity.OperationType;
import com.entity.Operations;
import com.services.SaveOrUpdateObjectInputServiceImpl;
import com.services.account.IPersonalDataService;
import com.services.admin.ProductService;
import com.services.offer.OfferServiceImpl;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.List;

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

    @Inject
    ProductService productService;

    private static final String pageParticleboard = "particleboard";
    private static final String pagePlywood = "plywood";

    @RequestMapping( "/home")
    public String showHome(Model model, HttpSession session) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            model.addAttribute("personalData",personalDataService.getPersonalData(userName)) ;
        }
        return "home";
    }

    @RequestMapping(value = "/home/last-products/{count}", method = RequestMethod.POST,
            consumes="application/json", produces="application/json",
            headers = {"Accept=text/xml, application/json"})
    public @ResponseBody
    List lastProducts(@PathVariable("count") int count) {
        return productService.getLastProducts(count);
    }

    @RequestMapping(value = "/home/{index}",method = RequestMethod.GET)
    public String getHome(@PathVariable("index") int index,Model model, HttpSession session) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            model.addAttribute("personalData",personalDataService.getPersonalData(userName)) ;
        }
        return Page.getPage(index).getNamePage();
    }

    @RequestMapping(value = "/home/views-products/{count}", method = RequestMethod.POST,
            consumes="application/json", produces="application/json",
            headers = {"Accept=text/xml, application/json"})
    public @ResponseBody
    List viewsProducts(@PathVariable("count") int count) {
        return productService.getViewsProducts(count);
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



    private enum Page{
        pageParticleboard ("particleboard"),
        pagePlywood("plywood"),
        pageParticleboardLaminated("particleboardLaminated");
        String namePage;
        Page(String name){
            this.namePage = name;
        }

        public static Page getPage(int i) {
            Page[] page =  values();
            return (i<page.length ? page[i]: page[0]);
        }

        public String getNamePage() {
            return namePage;
        }
    }

}
