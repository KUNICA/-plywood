package com.controller.registration;


import com.dataweb.Password;
import com.dataweb.Registration;
import com.entity.*;
import com.services.SaveOrUpdateObjectInputServiceImpl;
import com.services.registration.RegistrationServiceImpl;
import com.validation.RegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 15.09.2016.
 */

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Inject
    private RegistrationServiceImpl registrationService;

    @RequestMapping(value="/form")
    public String getRegistration(Model model) {
        model.addAttribute("registration",new Registration());
        return "registration";
    }

    @RequestMapping(value="/set", method = RequestMethod.POST)
    public  String setRegistration(@Valid  Registration registration, BindingResult result) {
        if(result.hasErrors()){
            return "registration";
        }
        registrationService.newUser(registration);
        return "redirect:/registration/complete";
    }

    @RequestMapping(value="/complete")
    public  String completionRegistration() {
        return "complete";
    }

    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    @RequestMapping(value="/change")
    public  String changeRegistration(Model model) {
        model.addAttribute("registration",new Password());
        return "change";
    }

    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    @RequestMapping(value="/password", method = RequestMethod.POST)
    public  String setRegistration(@Valid Password password, BindingResult result) {
        if(result.hasErrors()){
            return "change";
        }
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        registrationService.changePassword(userName,password.getPassword());
        return "passwordComplete";
    }
}
