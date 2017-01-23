package com.controller.admin;

import com.dataweb.ParticleboardLaminatedValid;
import com.dataweb.ParticleboardValid;
import com.dataweb.PlywoodValid;
import com.entity.*;
import com.exel.ProductFormatExelExeption;
import com.services.SaveOrUpdateObjectInputServiceImpl;
import com.services.account.IPersonalDataService;
import com.services.admin.ProductService;
import com.services.admin.ImageFileServiceImpl;
import com.services.admin.ImageUploadException;
import com.services.contacts.ContactServiceImpl;
import com.services.exel.ProductExcelServiceImpl;
import com.services.exel.ExelServiceImpl;
import com.services.mailing.MailingServiceImpl;
import com.services.util.ConverterProductImpl;
import com.services.vidio.VidioServiceImpl;
import com.services.view.ViewProductServiceImpl;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Date;

import static com.entity.Type.Particleboard;
import static com.entity.Type.ParticleboardLaminated;
import static com.entity.Type.Plywood;

/**
 * Created by user on 20.08.2016.
 */
@Controller
@RequestMapping(value="/admin")
public class AdminController {
    private static final String fileUploadControllerURL = "/admin/upload";
    private static final String fileSaveControllerURL = "/admin/save";
    private static final String fileUploadVideoURL = "/admin/video-save";
    private static final String fileUploadContactURL = "/admin/contact-save";
    private static final String fileUploadMailDeliveryURL = "/admin/mail-delivery-save";

    @Inject
    private ProductExcelServiceImpl productExelService;

    @Inject
    @Named("videoExelService")
    private ExelServiceImpl videoExelService;

    @Inject
    @Named("mailingExelService")
    private ExelServiceImpl mailingExelService;

    @Inject
    @Named("contactExelService")
    private ExelServiceImpl contactExelService;

    @Inject
    private VidioServiceImpl vidioServiceImpl;

    @Inject
    private ProductService productServiceImpl;

    @Inject
    private ImageFileServiceImpl imageFileServiceImpl;

    @Inject
    IPersonalDataService personalDataService;

    @Inject
    private ViewProductServiceImpl viewProductService;

    @Inject
    private ConverterProductImpl converterProduct;

    @Inject
    private SaveOrUpdateObjectInputServiceImpl saveService;

    @Inject
    private ContactServiceImpl contactService;

    @Inject
    private MailingServiceImpl mailingService;


    @RequestMapping(value="/run")
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public String admin(Model model, HttpSession session) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        setDataModel(model,userName);
        return "admin";
    }

    @RequestMapping(value="/Plywood")
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public String adminPlywood(Model model, HttpSession session) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        setDataModel(model,userName);
        return "adminPlywood";
    }

    @RequestMapping(value="/Particleboard")
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public String adminParticleboard(Model model, HttpSession session) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        setDataModel(model,userName);
        return "adminParticleboard";
    }

    @RequestMapping(value="/ParticleboardLaminated")
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public String adminParticleboardLaminated(Model model, HttpSession session) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        setDataModel(model,userName);
        return "adminParticleboardLaminated";
    }

    @RequestMapping(value="/upload/{product}", method = RequestMethod.POST)
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public String addProduct(@PathVariable("product") String productStr,@RequestParam("file") MultipartFile file, Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        setDataModel(model,userName);
        if(file.isEmpty()){
            model.addAttribute("error","select a file");
            return "admin";
        }
        try {
            productExelService.addProduct(file.getInputStream(),userName,productStr);
        } catch (ProductFormatExelExeption productFormatExelExeption) {
            model.addAttribute("error",productFormatExelExeption.getFildName());
            return "admin";
        } catch (InvocationTargetException | IOException | NullPointerException | NumberFormatException e) {
            model.addAttribute("error",e.fillInStackTrace());
            return "admin";
        }
        model.addAttribute("error","File added");
        return "redirect:/admin/" + productStr;
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public String saveImage(@RequestParam("fileImage") MultipartFile file, Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        setDataModel(model,userName);
        if(file.isEmpty()){
            model.addAttribute("errorImage","select a file");
            return "admin";
        }
        try {
            if(!file.isEmpty()) {
                imageFileServiceImpl.validateImage(file);
                imageFileServiceImpl.saveFileImage(file.getOriginalFilename(),file); // Сохранить файл
            }
        } catch (ImageUploadException e) {
            model.addAttribute("errorImage",e.fillInStackTrace());
            return "admin";
        }
        model.addAttribute("errorImage","File added");
        return "admin";
    }

    @RequestMapping(value="/remove/{productId}",method = RequestMethod.POST)
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public String remove(@PathVariable("productId") long productId,Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        productServiceImpl.removeProduct(productId,userName);
        return "admin";
    }

    @RequestMapping(value="/removeImg/{fileName}",method = RequestMethod.POST)
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public String removeImg(@PathVariable("fileName") String fileName,Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        imageFileServiceImpl.removeFile(fileName + ".jpg");
        return "admin";
    }

    @RequestMapping(value="/products")
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public String showHome( Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        setDataModel(model,userName);
        return "admin";
    }

    private void setDataModel(Model model,String userName){
        model.addAttribute("fileUploadControllerURL", fileUploadControllerURL);
        model.addAttribute("fileSaveControllerURL", fileSaveControllerURL);
        model.addAttribute("personalData",personalDataService.getPersonalData(userName)) ;
    }

    @RequestMapping(value="/plywood-edit/{id}")
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public String plywoodEdit(@PathVariable("id") long productId, Model model) {
        model.addAttribute("plywoodValid",new PlywoodValid());
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        setDataModel(model,userName);
        model.addAttribute("grades", Grade.values());
        model.addAttribute("product",viewProductService.getProduct(Plywood,productId));
        return "plywoodEdit";
    }

    @RequestMapping(value="/particleboard-edit/{id}")
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public String particleboardEdit(@PathVariable("id") long productId,Model model) {
        model.addAttribute("particleboardValid",new ParticleboardValid());
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        setDataModel(model,userName);
        model.addAttribute("product",viewProductService.getProduct(Particleboard,productId));
        model.addAttribute("grades", Grade.values());
        return "particleboardEdit";
    }

    @RequestMapping(value="/particleboardLaminated-edit/{id}")
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public String particleboardLaminatedEdit(@PathVariable("id") long productId,Model model) {
        model.addAttribute("particleboardLaminatedValid",new ParticleboardLaminatedValid());
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        setDataModel(model,userName);
        model.addAttribute("product",viewProductService.getProduct(ParticleboardLaminated,productId));
        return "particleboardLaminatedEdit";
    }

    @RequestMapping(value="/update-plywood/{id}", method = RequestMethod.POST)
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public  String setUpdateParticleboard(@Valid PlywoodValid plywoodValid, BindingResult result, Model model, @PathVariable("id") long productId) {
        if(result.hasErrors()){
            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            setDataModel(model,userName);
            model.addAttribute("grades", Grade.values());
            model.addAttribute("product",viewProductService.getProduct(Plywood,productId));
            return "plywoodEdit";
        }
        saveService.inputObject(converterProduct.getPlywood(productId,plywoodValid));

        return "redirect:/admin/Plywood";
    }

    @RequestMapping(value="/update-particleboard/{id}", method = RequestMethod.POST)
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public  String setUpdatePlywood(@Valid ParticleboardValid particleboardValid, BindingResult result, Model model, @PathVariable("id") long productId) {
        if(result.hasErrors()){
            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            setDataModel(model,userName);
            model.addAttribute("product",viewProductService.getProduct(Particleboard,productId));
            return "particleboardEdit";
        }
        saveService.inputObject(converterProduct.getParticleboard(productId,particleboardValid));
        return "redirect:/admin/Particleboard";
    }

    @RequestMapping(value="/update-particleboardLaminated/{id}", method = RequestMethod.POST)
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public  String setUpdateParticleboardLaminated(@Valid ParticleboardLaminatedValid particleboardLaminatedValid, BindingResult result, Model model, @PathVariable("id") long productId) {
        if(result.hasErrors()){
            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            setDataModel(model,userName);
            model.addAttribute("product",viewProductService.getProduct(ParticleboardLaminated,productId));
            return "particleboardLaminatedEdit";
        }
        saveService.inputObject(converterProduct.getParticleboardLaminated(productId,particleboardLaminatedValid));
        return "redirect:/admin/ParticleboardLaminated";
    }


    @RequestMapping(value="/video")
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public String video(Model model, HttpSession session) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("personalData",personalDataService.getPersonalData(userName)) ;
        model.addAttribute("fileUploadControllerURL", fileUploadVideoURL);
        return "videoAdmin";
    }

    @RequestMapping(value="/video-save/{product}", method = RequestMethod.POST)
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public String addVideo(@PathVariable("product") String productStr,@RequestParam("file") MultipartFile file, Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("personalData",personalDataService.getPersonalData(userName)) ;
        model.addAttribute("fileUploadControllerURL", fileUploadVideoURL);
        if(file.isEmpty()){
            model.addAttribute("error","select a file");
            return "videoAdmin";
        }
        try {
                videoExelService.add(file.getInputStream(),userName);
        } catch (ProductFormatExelExeption productFormatExelExeption) {
            model.addAttribute("error",productFormatExelExeption.getFildName());
            return "videoAdmin";
        } catch (InvocationTargetException | IOException | NullPointerException | NumberFormatException | ParseException e) {
            model.addAttribute("error",e.fillInStackTrace());
            return "videoAdmin";
        }
        model.addAttribute("error","File added");
        return "videoAdmin";
    }

    @RequestMapping(value="/video-remove/{id}",method = RequestMethod.POST)
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public String videoRemove(@PathVariable("id") long id,Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Video video = vidioServiceImpl.getVidio(id);
        Operations operationOut = new Operations();
        operationOut.setDateOper(new Date());
        operationOut.setTypeOper(OperationType.OPERATION_OUT);
        operationOut.setUserName(userName);
        video.setOperationOut(operationOut);
        saveService.inputObject(video);
        return "videoAdmin";
    }

    @RequestMapping(value="/mail-delivery")
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public String mailDelivery(Model model, HttpSession session) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("personalData",personalDataService.getPersonalData(userName)) ;
        model.addAttribute("fileUploadControllerURL", fileUploadMailDeliveryURL);
        model.addAttribute("emailDeliverys", mailingService.getMailings());
        return "mailDeliveryAdmin";
    }


    @RequestMapping(value="/mail-delivery-save", method = RequestMethod.POST)
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public String addMailDelivery(@RequestParam("file") MultipartFile file, Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("personalData",personalDataService.getPersonalData(userName)) ;
        model.addAttribute("fileUploadControllerURL", fileUploadMailDeliveryURL);
        if(file.isEmpty()){
            model.addAttribute("error","select a file");
            return "contactAdmin";
        }
        try {
            mailingExelService.add(file.getInputStream(),userName);
        } catch (ProductFormatExelExeption productFormatExelExeption) {
            model.addAttribute("error",productFormatExelExeption.getFildName());
            return "contactAdmin";
        } catch (InvocationTargetException | IOException | NullPointerException | NumberFormatException | ParseException e) {
            model.addAttribute("error",e.fillInStackTrace());
            return "contactAdmin";
        }

        model.addAttribute("error","File added");
        return "redirect:/admin/mail-delivery";
    }


    @RequestMapping(value="/contact-remove/{id}",method = RequestMethod.POST)
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public String contactRemove(@PathVariable("id") long id,Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Contacts contact = contactService.getContact(id);
        Operations operationOut = new Operations();
        operationOut.setDateOper(new Date());
        operationOut.setTypeOper(OperationType.OPERATION_OUT);
        operationOut.setUserName(userName);
        contact.setOperationOut(operationOut);
        saveService.inputObject(contact);
        return "contactAdmin";
    }

    @RequestMapping(value="/mail-delivery-remove/{id}",method = RequestMethod.POST)
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public String mailDeliveryRemove(@PathVariable("id") long id,Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Mailing mailing = mailingService.getMailing(id);
        Operations operationOut = new Operations();
        operationOut.setDateOper(new Date());
        operationOut.setTypeOper(OperationType.OPERATION_OUT);
        operationOut.setUserName(userName);
        mailing.setOperationOut(operationOut);
        saveService.inputObject(mailing);
        return "mailDeliveryAdmin";
    }


    @RequestMapping(value="/contacts")
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public String contacts(Model model, HttpSession session) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("personalData",personalDataService.getPersonalData(userName)) ;
        model.addAttribute("fileUploadControllerURL", fileUploadContactURL);
        model.addAttribute("contacts", contactService.getContacts());
        return "contactAdmin";
    }


    @RequestMapping(value="/contact-save", method = RequestMethod.POST)
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public String addContact(@RequestParam("file") MultipartFile file, Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("personalData",personalDataService.getPersonalData(userName)) ;
        model.addAttribute("fileUploadControllerURL", fileUploadContactURL);
        if(file.isEmpty()){
            model.addAttribute("error","select a file");
            return "contactAdmin";
        }
        try {
            contactExelService.add(file.getInputStream(),userName);
        } catch (ProductFormatExelExeption productFormatExelExeption) {
            model.addAttribute("error",productFormatExelExeption.getFildName());
            return "contactAdmin";
        } catch (InvocationTargetException | IOException | NullPointerException | NumberFormatException | ParseException e) {
            model.addAttribute("error",e.fillInStackTrace());
            return "contactAdmin";
        }
        model.addAttribute("error","File added");
        return "redirect:/admin/contacts";
    }

    @RequestMapping(value="/send-update/{id}",method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN"})
    public @ResponseBody
    boolean mailDeliverySendUpdare(@PathVariable("id") long id) {
        Mailing mailing = mailingService.getMailing(id);
        mailing.setSend(!mailing.getSend());
        saveService.inputObject(mailing);
        return mailing.getSend();
    }

}
