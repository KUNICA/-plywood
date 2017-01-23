package com.services.alert;

import com.dataweb.LeaveMessageValid;
import com.entity.Mailing;
import com.entity.Offer;
import com.entity.PersonalData;
import com.services.account.IPersonalDataService;
import com.services.mailing.MailingServiceImpl;
import com.services.print.CreateMailServiceImpl;
import com.services.print.ExportServicePdf;
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

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

    @Inject
    private ExportServicePdf exportServicePdf;

    @Autowired
    private VelocityEngine velocityEngine; //see application-context.xml

    @Autowired
    ServletContext context;

    @Inject
    private CreateMailServiceImpl createMailService;

    private static final String template = "tanks.vm";
    private static final String templateAdmin = "application.vm";
    private static final String templateAuthorization = "authorization.vm";
    private static final String templateLeaveMessage = "leave-message.vm";
    private static final String templateAlertCompanyOffer = "alertCompanyOffer.vm";

    private static final String emailAdmin = "info@plywood-house.com";


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
                message.setFrom(emailAdmin);
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
                    message.setFrom(emailAdmin);
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


    // Отправляет сообщение со страницы контакты админу
    public void sendAlertMessageContacts(final LeaveMessageValid leaveMessageValid) {
        try {
            MimeMessagePreparator preparator = new MimeMessagePreparator() {
                @SuppressWarnings({"rawtypes", "unchecked"})
                public void prepare(MimeMessage mimeMessage) throws Exception {
                    String encodingOptions = "text/html; charset=UTF-8";
                    mimeMessage.setHeader("Content-Type", encodingOptions);
                    MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                    message.setFrom(emailAdmin);
                    message.setTo(emailAdmin);
                    message.setSubject(MimeUtility.encodeText("Message ", "UTF-8", "Q"));
                    Map<String, Object> model = new HashMap<>();
                    model.put("message", leaveMessageValid);
                    String text = VelocityEngineUtils.mergeTemplateIntoString(
                            velocityEngine, templateLeaveMessage, "UTF-8", model);
                    message.setText(MimeUtility.encodeText(text, "UTF-8", "Q"));
                    message.setText(text, true);
                }
            };
            mailSender.send(preparator);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public void sendAlertMessageCompanyOffer(final Mailing mailing,final String name,final String logo,final String signature,final String toEmail){
        try{
            MimeMessagePreparator preparator = new MimeMessagePreparator() {
                @SuppressWarnings({"rawtypes", "unchecked"})
                public void prepare(MimeMessage mimeMessage) throws Exception {
                    MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true);
                    message.setFrom(emailAdmin);
                    if(toEmail!=null){
                        message.setTo(toEmail);
                    }else{
                        message.setTo(mailing.getEmail());
                    }
                    message.setSubject("Message " + mailing.getNameCompany());
                    Map<String, Object> model = new HashMap<>();
                    model.put("name",name);
                    model.put("logoImg","<img style = \"float:right\" src=\"" + "cid:logo" + "\">");
                   // model.put("signature","<img src=\"" + "cid:signature" + "\">");
                    model.put("company",mailing.getNameCompany());
                    model.put("address",mailing.getAddress());
                    model.put("email",mailing.getEmail());
                    model.put("description","\"" + mailing.getDiscription() + "\"");

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String sDate = sdf.format(new Date(System.currentTimeMillis()));
                    model.put("newDate",sDate);
                    String text = VelocityEngineUtils.mergeTemplateIntoString(
                            velocityEngine, templateAlertCompanyOffer, "UTF-8", model);


                    MimeMultipart multipart = new MimeMultipart("related");

                    MimeBodyPart textPart = createMailService.getBodyText(text);
                    multipart.addBodyPart(textPart);

                    MimeBodyPart logoImage = createMailService.getImageBody("//images//",logo,"logo");
                    multipart.addBodyPart(logoImage);

                  //  MimeBodyPart signatureImage = createMailService.getImageBody("//images//",signature,"signature");
                  //  multipart.addBodyPart(signatureImage);


                    MimeBodyPart messageBodyPartPdf = new MimeBodyPart();
                    InputStream pdfStream = exportServicePdf.exportPdfToInputStream(exportServicePdf.getReportCompanyOfferPdf(mailing.getId()));
                    ByteArrayDataSource ds = new ByteArrayDataSource(pdfStream, "application/pdf");
                    messageBodyPartPdf.setDataHandler(new DataHandler(ds));
                    messageBodyPartPdf.setFileName("darnumas.pdf");
                    multipart.addBodyPart(messageBodyPartPdf);


                    mimeMessage.setContent(multipart);



                }
            };
            mailSender.send(preparator);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


}

