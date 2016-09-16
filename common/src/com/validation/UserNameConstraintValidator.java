package com.validation;

import com.dao.account.IUserDao;
import com.sun.org.glassfish.gmbal.IncludeSubclass;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by user on 16.09.2016.
 */
public class UserNameConstraintValidator implements ConstraintValidator<UserName, String> {

    @Inject
    private IUserDao userDao;

    @Override
    public void initialize(UserName name) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isValid(String nameField, ConstraintValidatorContext cxt) {
        if(nameField == null) {
            return false;
        }
        return userDao.getUser(nameField) == null;
    }
}
