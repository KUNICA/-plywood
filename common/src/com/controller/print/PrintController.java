package com.controller.print;

import com.services.print.PrintProductsService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

    @RequestMapping(value="/print",method= RequestMethod.GET)
    @Secured({ "ROLE_ADMIN","ROLE_USER"})
    public void getReport(HttpServletResponse response) throws JRException, IOException {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        InputStream jasperStream = this.getClass().getResourceAsStream("/print/report1.jasper");
        Map<String,Object> params = new HashMap<String,Object>();
        List list = printParticleboardService.getListParamProducts(userName);
        List listPlywood = printPlywoodService.getListParamProducts(userName);
        list.addAll(listPlywood);
        JRDataSource dataSource = new JRBeanCollectionDataSource(list);
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

        response.setContentType("application/x-pdf");
        response.setHeader("Content-disposition", "inline; filename=offer.pdf");

        final OutputStream outStream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

    }

}
