package com.services.exel;

import com.dao.product.ProductDaoImpl;
import com.entity.*;
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

    @Named("particleboardLaminatedExelParser")
    @Inject
    protected ExelParserImpl particleboardLaminatedExelParser;

    @Named("particleboardDao")
    @Inject
    protected ProductDaoImpl particleboardDao;

    @Named("particleboardLaminatedDao")
    @Inject
    protected ProductDaoImpl particleboardLaminatedDao;

    @Named("plywoodDao")
    @Inject
    protected ProductDaoImpl plywoodDao;

    private static final String PLYWOOD = "Plywood";
    private static final String PARTICLEBOARD = "Particleboard";
    private static final String PARTICLEBOARD_LAMINATED = "ParticleboardLaminated";

    public List initPlywoods(InputStream inputStream, String exelParser) throws InvalidFormatException, ProductFormatExelExeption {
        List plywoods = new ArrayList();
        switch (exelParser){
            case PLYWOOD:
                plywoods = plywoodExelParser.getProducts(inputStream);
                break;
            case PARTICLEBOARD:
                plywoods = particleboardExelParser.getProducts(inputStream);
                break;
            case PARTICLEBOARD_LAMINATED:
                plywoods = particleboardLaminatedExelParser.getProducts(inputStream);
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
            case PARTICLEBOARD_LAMINATED:
                product = new ParticleboardLaminated();
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
            case PARTICLEBOARD_LAMINATED:
                product = (Product)particleboardLaminatedDao.getProduct(id);
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
        if(!Strings.isNullOrEmpty(productExel.getType())){
            product.setTypeStr(productExel.getType());
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
                if(!Strings.isNullOrEmpty(plywoodExel.getAmountPackage())){
                    plywood.setAmountPackage(Long.parseLong(plywoodExel.getAmountPackage()));
                }
                if(!Strings.isNullOrEmpty(plywoodExel.getNumberPackages())){
                    plywood.setNumberPackages(Long.parseLong(plywoodExel.getNumberPackages()));
                }
                if(!Strings.isNullOrEmpty(plywoodExel.getGrade()) && Grade.isField(plywoodExel.getGrade())){
                    plywood.setGrade(Grade.getField(plywoodExel.getGrade()));
                }

                if(!Strings.isNullOrEmpty(productExel.getType()) && !Strings.isNullOrEmpty(productExel.getLength()) &&
                        !Strings.isNullOrEmpty(productExel.getThickness()) && !Strings.isNullOrEmpty(productExel.getWeight())){
                    String name = productExel.getType() + "/" + productExel.getLength() + "x" + productExel.getWeight() + "x" + productExel.getThickness();
                    plywood.setName(name);
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
                if(!Strings.isNullOrEmpty(particleboardExel.getAmountPackage())){
                    particleboard.setAmountPackage(Long.parseLong(particleboardExel.getAmountPackage()));
                }
                if(!Strings.isNullOrEmpty(particleboardExel.getNumberPackages())){
                    particleboard.setNumberPackages(Long.parseLong(particleboardExel.getNumberPackages()));
                }
                if(!Strings.isNullOrEmpty(particleboardExel.getSanded())){
                    particleboard.setSanded(particleboardExel.getSanded().equals("sanded"));
                }
                else{
                    particleboard.setSanded(false);
                }
                if(!Strings.isNullOrEmpty(productExel.getType()) && !Strings.isNullOrEmpty(productExel.getLength()) &&
                        !Strings.isNullOrEmpty(productExel.getThickness()) && !Strings.isNullOrEmpty(productExel.getWeight())){
                    String name = productExel.getType() + "/" + productExel.getLength() + "x" + productExel.getWeight() + "x" + productExel.getThickness();
                    particleboard.setName(name);
                }
                break;
            case PARTICLEBOARD_LAMINATED:
                ParticleboardLaminated particleboardLaminated = (ParticleboardLaminated)product;
                ParticleboardExel particleboardLaminatedExel = (ParticleboardExel) productExel;
                if(!Strings.isNullOrEmpty(particleboardLaminatedExel.getLaminated())){
                    particleboardLaminated.setLaminated(particleboardLaminatedExel.getLaminated());
                }
                if(!Strings.isNullOrEmpty(particleboardLaminatedExel.getAmountPackage())){
                    particleboardLaminated.setAmountPackage(Long.parseLong(particleboardLaminatedExel.getAmountPackage()));
                }
                if(!Strings.isNullOrEmpty(particleboardLaminatedExel.getNumberPackages())){
                    particleboardLaminated.setNumberPackages(Long.parseLong(particleboardLaminatedExel.getNumberPackages()));
                }
                if(!Strings.isNullOrEmpty(productExel.getType()) && !Strings.isNullOrEmpty(productExel.getLength()) &&
                        !Strings.isNullOrEmpty(productExel.getThickness()) && !Strings.isNullOrEmpty(productExel.getWeight()) &&
                        !Strings.isNullOrEmpty(particleboardLaminatedExel.getLaminated())){
                    String name = productExel.getType() + "/" + particleboardLaminatedExel.getLaminated() +
                            "/" + productExel.getLength() + "x" + productExel.getWeight() + "x" + productExel.getThickness();
                    particleboardLaminated.setName(name);
                }
                break;
        }


    }
}
