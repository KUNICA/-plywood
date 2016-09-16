package com.services.registration;

import com.dataweb.Registration;

/**
 * Created by user on 16.09.2016.
 */
public interface RegistrationServiceImpl {
   void newUser(Registration registration);
   void changePassword(String userName,String password);
}
