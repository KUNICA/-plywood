package com.services.registration;

import com.dao.account.IUserDao;
import com.dataweb.Registration;
import com.entity.*;
import com.services.SaveOrUpdateObjectInputServiceImpl;
import com.services.alert.AlertServiceImpl;
import com.services.util.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.keygen.*;

import javax.inject.Inject;
import javax.inject.Named;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 16.09.2016.
 */
@Named
public class RegistrationService implements RegistrationServiceImpl{

    @Inject
    private SaveOrUpdateObjectInputServiceImpl saveService;

    @Inject
    private IUserDao userDao;

    @Qualifier("alertService")
    @Autowired
    private AlertServiceImpl alertService;


    public void newUser(Registration registration){
        List<Object> list  = new ArrayList<Object>();

        PersonalData personalData = new PersonalData();
        personalData.setEmail(registration.getEmail());
        personalData.setUserName(registration.getLastName());
        personalData.setAdress(registration.getAddress());
        personalData.setCompanyName(registration.getCompanyName());
        personalData.setPhone(registration.getPhone());
        personalData.setTown(registration.getTown());
        personalData.setZip(registration.getZip());
        personalData.setFullName(registration.getFullName());

        Operations operationIn = new Operations();
        operationIn.setDateOper(new Date());
        operationIn.setTypeOper(OperationType.OPERATION_IN);
        operationIn.setUserName(registration.getLastName());

        personalData.setOperationIn(operationIn);

        String password = PasswordGenerator.generate(10,10);

        Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
        String hash = md5PasswordEncoder.encodePassword(password,"");
        list.add( new Users(registration.getLastName(), hash, (byte) 1));
        list.add(personalData);

        list.add(new Authorities(registration.getLastName(), Authority.ROLE_USER));
        list.add(new GroupMembers(registration.getLastName(), Group.USER));

        saveService.inputObject(list);
        alertService.sendAlertAuthorization(registration.getLastName(),password);
    }


    public void changePassword(String userName,String password){
        Users user = userDao.getUser(userName);
        Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
        String hash = md5PasswordEncoder.encodePassword(password,"");
        user.setPassword(hash);
        saveService.inputObject(user);
    }
}
