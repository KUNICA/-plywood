package com.services.exel;

import com.dao.product.ProductDaoImpl;
import com.entity.Grade;
import com.entity.Particleboard;
import com.entity.Plywood;
import com.entity.Product;
import com.exel.*;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.google.common.base.Strings;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 23.08.2016.
 */
@Named
public class InitProductExelService implements InitProductExelServiceImpl{

    @Named("plywoodExelParser")
    @Inject
    protected ExelParserImpl plywoodExelParser;

    @Named("particleboardExelParser")
    @Inject
    protected ExelParserImpl particleboardExelParser;

    @Named("particleboardDao")
    @Inject
    protected ProductDaoImpl particleboardDao;

    @Named("plywoodDao")
    @Inject
    protected ProductDaoImpl plywoodDao;

    private static final String PLYWOOD = "plywood";
    private static final String PARTICLEBOARD = "particleboard";

    public List initPlywoods(InputStream inputStream, String exelParser) throws InvalidFormatException, ProductFormatExelExeption {
        List plywoods = new ArrayList();
        switch (exelParser){
            case PLYWOOD:
                plywoods = plywoodExelParser.getProducts(inputStream);
                break;
            case PARTICLEBOARD:
                plywoods = particleboardExelParser.getProducts(inputStream);
                break;
        }
        return plywoods;
    }

    public Product productInstance(String productStr){
        Product product = new Product();
        switch (productStr) {
            case PLYWOOD:
                product = new Plywood();
                break;
            case PARTICLEBOARD:
                product = new Particleboard();
                break;
        }
        return product;
    }

    public Product getProduct(String productStr,String id){
        Product product = new Product();
        switch (productStr) {
            case PLYWOOD:
                product = (Product)plywoodDao.getProduct(id);
                break;
            case PARTICLEBOARD:
                product = (Product)particleboardDao.getProduct(id);
                break;
        }
        return product;
    }

    public void initProduct(Product product, ProductExel productExel, String productStr) throws NumberFormatException{
        if(!Strings.isNullOrEmpty(productExel.getProductId())){
            product.setProductId(productExel.getProductId());
        }
        if(!Strings.isNullOrEmpty(productExel.getShortDescription())){
            product.setShortDescription(productExel.getShortDescription());
        }
        if(!Strings.isNullOrEmpty(productExel.getLength())){
            product.setLength(Long.parseLong(productExel.getLength()));
        }
        if(!Strings.isNullOrEmpty(productExel.getThickness())){
            product.setDepth(Long.parseLong(productExel.getThickness()));
        }
        if(!Strings.isNullOrEmpty(productExel.getWeight())){
            product.setWidth(Long.parseLong(productExel.getWeight()));
        }
        if(!Strings.isNullOrEmpty(productExel.getPrice())){
            product.setPrice(Double.parseDouble(productExel.getPrice()));
        }
        switch (productStr){
            case PLYWOOD:
                Plywood plywood = (Plywood)product;
                PlywoodExel plywoodExel = (PlywoodExel) productExel;
                if(!Strings.isNullOrEmpty(plywoodExel.getSanded())){
                    plywood.setSanded(plywoodExel.getSanded().equals("sanded"));
                }
                else{
                    plywood.setSanded(false);
                }
                if(!Strings.isNullOrEmpty(plywoodExel.getWaterResistance())){
                    plywood.setWaterResistance(plywoodExel.getWaterResistance().equals("FK"));
                }
                else{
                    plywood.setWaterResistance(false);
                }
                if(!Strings.isNullOrEmpty(plywoodExel.getCoating())){
                    plywood.setCoating(plywoodExel.getCoating().equals("yes"));
                }
                else{
                    plywood.setCoating(false);
                }
                if(!Strings.isNullOrEmpty(plywoodExel.getCoatingColor())){
                    plywood.setColorCoating(Long.parseLong(plywoodExel.getCoatingColor()));
                }
                break;
            case PARTICLEBOARD:
                Particleboard particleboard = (Particleboard)product;
                ParticleboardExel particleboardExel = (ParticleboardExel) productExel;
                if(!Strings.isNullOrEmpty(particleboardExel.getLaminated())){
                    particleboard.setLaminated(Long.parseLong(particleboardExel.getLaminated()));
                }
                if(!Strings.isNullOrEmpty(particleboardExel.getCoating())){
                    particleboard.setCoating(particleboardExel.getCoating().equals("yes"));
                }
                else{
                    particleboard.setCoating(false);
                }
                if(!Strings.isNullOrEmpty(particleboardExel.getGrade()) && Grade.isField(particleboardExel.getGrade())){
                    particleboard.setGrade(Grade.getField(particleboardExel.getGrade()));
                }
                break;
        }


    }
}
