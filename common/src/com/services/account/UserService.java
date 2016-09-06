package com.services.account;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.inject.Named;

/**
 * Created by user on 31.08.2016.
 */
@Named
public class UserService implements UserServiceImpl{

    private static final String anonymousUser = "anonymousUser";

    public String getUserName(){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(userName != null && userName.equals(anonymousUser)) {
            WebAuthenticationDetails authenticationDetails = ((WebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails());
            return userName + authenticationDetails.getSessionId();
        }
        return userName;
    }
}
