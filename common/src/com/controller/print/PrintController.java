package com.controller.print;

import com.entity.Mailing;
import com.services.mailing.MailingServiceImpl;
import com.services.print.ExportServicePdf;
import com.services.print.PrintProductsService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.SimpleFileResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 20.08.2016.
 */
@Controller
@RequestMapping(value="/report")
public class PrintController {

    @Inject
    @Named("printParticleboardService")
    private PrintProductsService printParticleboardService;
    @Inject
    @Named("printPlywoodService")
    private PrintProductsService printPlywoodService;
    @Inject
    @Named("printParticleboardLaminatedService")
    private PrintProductsService printParticleboardLaminatedService;

    @Autowired
    ServletContext context;

    @Inject
    private ExportServicePdf exportServicePdf;

    static String LOCATION = "//avatars//";

    @RequestMapping(value="/print",method= RequestMethod.GET)
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public void getReportShopping(HttpServletResponse response) throws JRException, IOException {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        FileInputStream jasperStream = new FileInputStream (context.getRealPath("/print/report1.jasper"));
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("logo_patch",printPlywoodService.getImageSrc("logo.png"));
       // params.put("patch",  (new File(context.getRealPath("/")).getPath()));
       // File file = new File(context.getRealPath(File.separator) + LOCATION,"PlywoodTitle.jpg");
       // params.put("patchTitle",  (file.getPath()));
      //  params.put("patchFont",  (new File(context.getRealPath("/")).getPath() + "\\TIMES.TTF"));
        List list = printParticleboardService.getListParamProducts(userName);
        List listPlywood = printPlywoodService.getListParamProducts(userName);
        list.addAll(listPlywood);
        List listParticleboardLaminated = printParticleboardLaminatedService.getListParamProducts(userName);
        list.addAll(listParticleboardLaminated);
        JRDataSource dataSource = new JRBeanCollectionDataSource(list);
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

        response.setContentType("application/x-pdf");
        response.setHeader("Content-disposition", "inline; filename=offer.pdf");

        final OutputStream outStream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

    }


    @RequestMapping(value="/companyOffer/{id}",method= RequestMethod.GET)
    public void getReportCompanyOffer(HttpServletResponse response,@PathVariable("id") Long id) throws JRException, IOException {
        JasperPrint jasperPrint = exportServicePdf.getReportCompanyOfferPdf(id);

        response.setContentType("application/x-pdf");
        response.setHeader("Content-disposition", "inline; filename=info.pdf");

        final OutputStream outStream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

    }





}
