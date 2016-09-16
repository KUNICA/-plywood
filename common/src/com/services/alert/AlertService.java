package com.services.alert;

import com.entity.Offer;
import com.entity.PersonalData;
import com.services.account.IPersonalDataService;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 20.08.2016.
 */
@Component("alertService")
@Named
public class AlertService  implements AlertServiceImpl{

    @Qualifier("mailSender")
    @Autowired
    private JavaMailSender mailSender;

    @Inject
    protected IPersonalDataService personalDataService;

    @Autowired
    private VelocityEngine velocityEngine; //see application-context.xml

    private ResourceLoader resourceLoader = new DefaultResourceLoader();

    private static final String template = "tanks.vm";
    private static final String templateAdmin = "application.vm";
    private static final String templateAuthorization = "authorization.vm";

    private static final String emailAdmin = "kun-oleg@tut.by";


    // Отправляет сообщение об оферте админу
    public void sendAlertAdmin(final Offer offer) {
        try {
            MimeMessagePreparator preparator = new MimeMessagePreparator() {
                @SuppressWarnings({"rawtypes", "unchecked"})
                public void prepare(MimeMessage mimeMessage) throws Exception {
                    String encodingOptions = "text/html; charset=UTF-8";
                    mimeMessage.setHeader("Content-Type", encodingOptions);
                    MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                    PersonalData person = personalDataService.getPersonalData(offer.getOperationIn().getUserName());
                    message.setFrom(emailAdmin);
                    message.setTo(emailAdmin);
                    message.setSubject(MimeUtility.encodeText("Message " + offer.getOperationIn().getUserName(), "UTF-8", "Q"));
                    Map<String, Object> model = new HashMap<>();
                    model.put("totalPrice", offer.getOrderPrice());
                    model.put("listOffer", offer.getShoppingCart());
                    model.put("person", person);
                    String text = VelocityEngineUtils.mergeTemplateIntoString(
                            velocityEngine, templateAdmin, "UTF-8", model);
                    message.setText(MimeUtility.encodeText(text, "UTF-8", "Q"));
                    message.setText(text, true);
                }
            };
            mailSender.send(preparator);
        }catch(Exception e){
                e.printStackTrace();
            }
    }


    // Отправляет сообщение об оферте клиенту
    public void sendAlert(final Offer offer) {
        try{
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @SuppressWarnings({"rawtypes", "unchecked"})
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                PersonalData person = personalDataService.getPersonalData(offer.getOperationIn().getUserName());
                message.setFrom(person.getEmail());
                message.setTo(person.getEmail());
                message.setSubject("Message " + offer.getOperationIn().getUserName());
                Map<String, Object> model = new HashMap<>();
                model.put("totalPrice", offer.getOrderPrice());
                model.put("listOffer", offer.getShoppingCart());
                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, template, "UTF-8", model);
                message.setText(text,true);
            }
        };
        mailSender.send(preparator);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void sendAlertAuthorization(final String userName,final String password) {
        try{
            MimeMessagePreparator preparator = new MimeMessagePreparator() {
                @SuppressWarnings({"rawtypes", "unchecked"})
                public void prepare(MimeMessage mimeMessage) throws Exception {
                    MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                    PersonalData person = personalDataService.getPersonalData(userName);
                    message.setFrom(person.getEmail());
                    message.setTo(person.getEmail());
                    message.setSubject("Message " + userName);
                    Map<String, Object> model = new HashMap<>();
                    model.put("userName", userName);
                    model.put("password", password);
                    String text = VelocityEngineUtils.mergeTemplateIntoString(
                            velocityEngine, templateAuthorization, "UTF-8", model);
                    message.setText(text,true);
                }
            };
            mailSender.send(preparator);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


}

