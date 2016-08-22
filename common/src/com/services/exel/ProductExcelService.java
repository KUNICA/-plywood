package com.services.exel;

import com.dao.product.ProductDaoImpl;
import com.entity.*;
import com.exel.ExelParserImpl;
import com.exel.ProductExel;
import com.exel.ProductFormatExelExeption;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.google.common.base.Strings;
import com.services.SaveOrUpdateObjectInputServiceImpl;
import com.services.admin.RemoveServiceImpl;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 20.08.2016.
 */
@Named
public class ProductExcelService implements ProductExcelServiceImpl {

    @Inject
    protected ExelParserImpl exelParser;

    @Inject
    protected SaveOrUpdateObjectInputServiceImpl saveOrUpdateObjectInputService;

    @Inject
    protected ProductDaoImpl productDao;

    @Inject
    protected RemoveServiceImpl removeService;


    public boolean addProduct(InputStream inputStream, String userName) throws NumberFormatException, InvalidFormatException, NullPointerException,IOException,InvocationTargetException, IllegalArgumentException, ProductFormatExelExeption {
        List plywoods = exelParser.getProducts(inputStream);
        List products = getProduct(plywoods,userName);
        saveOrUpdateObjectInputService.inputObject(products.get(0)); //одну страницу
        return true;
    }

    private List getProduct(List plywoods,String userName)throws NumberFormatException, NullPointerException,InvocationTargetException, IllegalArgumentException,IOException{
        ArrayList<Product> products = new ArrayList<Product>();
        for (Object it: plywoods) {
            ProductExel plywood = (ProductExel) it;
            Long id = !Strings.isNullOrEmpty(plywood.getId()) ? Long.parseLong(plywood.getId()) : null;
            Product product = (id!=null)? updateInit(id,plywood,userName) : saveInit(plywood,userName);
            products.add(product);
        }
        return products;
    }

    private Product updateInit(Long id, ProductExel productExel, String userName) throws NumberFormatException, NullPointerException, InvocationTargetException, IllegalArgumentException,IOException {
        Product product = (Product)productDao.getProduct(id);
        initProduct(product,productExel);
        removeService.remove(product,userName);
        product.setOperOut(null);
        setPhotos(product,productExel,userName);
        setVideos(product,productExel,userName);
        return product;
    }

    private Product saveInit(ProductExel productExel, String userName) throws NumberFormatException,NullPointerException,InvocationTargetException, IllegalArgumentException,IOException{
        Product product = setProductParametrs(productExel,userName);
        setPhotos(product,productExel,userName);
        setVideos(product,productExel,userName);
        return product;
    }

    private void setVideos(Product product, ProductExel productExel, String userName){
        int i=0;
        for(Map.Entry<String, String> e : productExel.getVideos().entrySet()) {
            Video videoEntity = new Video();
            videoEntity.setLink(e.getValue());
            videoEntity.setDescription(e.getKey());

            Operations operationIn = new Operations();
            operationIn.setDateOper(new Date());
            operationIn.setTypeOper(OperationType.OPERATION_IN);
            operationIn.setUserName(userName);
            videoEntity.setOperationIn(operationIn);

            product.getVideos().add(videoEntity);
            i++;
        }

    }

    private void setPhotos(Product product, ProductExel productExel, String userName){
        int i=0;
        for(Map.Entry<String, String> e : productExel.getPhotos().entrySet()) {
            Images imagesEntity = new Images();
            imagesEntity.setImg(e.getValue());
            imagesEntity.setDescription(e.getKey());
            if(i==0){
                imagesEntity.setMain(true);
            }

            Operations operationIn = new Operations();
            operationIn.setDateOper(new Date());
            operationIn.setTypeOper(OperationType.OPERATION_IN);
            operationIn.setUserName(userName);
            imagesEntity.setOperationIn(operationIn);

            product.getPhotos().add(imagesEntity);
            i++;
        }
    }

    private void initProduct(Product product,ProductExel productExel) throws NumberFormatException{

    }

    private Product setProductParametrs(ProductExel productExel, String userName) throws NumberFormatException{
        Product product = new Product();
        initProduct(product,productExel);
        Operations operationIn = new Operations();
        operationIn.setDateOper(new Date());
        operationIn.setTypeOper(OperationType.OPERATION_IN);
        operationIn.setUserName(userName);
        product.setOperIn(operationIn);
        return product;
    }

}



