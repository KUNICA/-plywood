package com.controller.offer;

import com.entity.Offer;
import com.services.SaveOrUpdateObjectInputServiceImpl;
import com.services.account.IPersonalDataService;
import com.services.alert.AlertServiceImpl;
import com.services.offer.OfferServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by user on 20.08.2016.
 */
@Controller
@RequestMapping(value="/offer")
public class OfferController {

    @Inject
    private OfferServiceImpl offerService;

    @Inject
    protected SaveOrUpdateObjectInputServiceImpl saveOrUpdateObjectInputService;

    @Inject
    IPersonalDataService personalDataService;

    @Qualifier("alertService")
    @Autowired
    private AlertServiceImpl alertService;

    @RequestMapping(value="/create")
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public String createOffer(Model model, HttpSession session) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Offer offer = (Offer)offerService.create(userName);
        saveOrUpdateObjectInputService.inputObject(offer);
        model.addAttribute("listOffer",offer.getShoppingCart());
        model.addAttribute("totalPrice",offer.getOrderPrice());
        alertService.sendAlert(offer);
        alertService.sendAlertAdmin(offer);
        model.addAttribute("personalData",personalDataService.getPersonalData(userName));
        return "offer";
    }

    @RequestMapping(value="/print")
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public String getOffer(Model model, HttpSession session) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Offer offer = (Offer)offerService.getOffer(userName);
        model.addAttribute("listOffer",offer.getShoppingCart());
        model.addAttribute("totalPrice",offer.getOrderPrice());
        model.addAttribute("personalData",personalDataService.getPersonalData(userName)) ;
        return "offer";
    }

    @RequestMapping(value="/isOffer", method = RequestMethod.POST)
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    @ResponseBody
    public Boolean getOfferActual() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Offer offer = (Offer)offerService.getOffer(userName);
        return offer==null;
    }

}
