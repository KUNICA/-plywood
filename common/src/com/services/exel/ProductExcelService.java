package com.services.exel;

import com.entity.*;
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
    protected SaveOrUpdateObjectInputServiceImpl saveOrUpdateObjectInputService;

    @Inject
    protected RemoveServiceImpl removeService;

    @Inject
    protected InitProductExelServiceImpl initProductExelService;



    public boolean addProduct(InputStream inputStream, String userName,String exelParser) throws NumberFormatException, InvalidFormatException, NullPointerException,IOException,InvocationTargetException, IllegalArgumentException, ProductFormatExelExeption {
        List plywoods = initProductExelService.initPlywoods(inputStream,exelParser);
        List products  = getProduct(plywoods,userName,exelParser);
        saveOrUpdateObjectInputService.inputObject(products); //одну страницу
        return true;
    }

    private List getProduct(List plywoods,String userName,String productStr)throws NumberFormatException, NullPointerException,InvocationTargetException, IllegalArgumentException,IOException{
        ArrayList<Product> products = new ArrayList<Product>();
        for (Object it: plywoods) {
            ProductExel plywood = (ProductExel) it;
            Product product = initProductExelService.getProduct(productStr,plywood.getProductId());
             product = !Strings.isNullOrEmpty(plywood.getProductId()) && product!=null ?
                    updateInit(plywood.getProductId(),plywood,userName,productStr) :
                    saveInit(plywood,userName,productStr);
            products.add(product);
        }
        return products;
    }

    private Product updateInit(String id, ProductExel productExel, String userName,String productStr)
            throws NumberFormatException, NullPointerException, InvocationTargetException,
            IllegalArgumentException,IOException {
        Product product = initProductExelService.getProduct(productStr,id);
        initProductExelService.initProduct(product,productExel,productStr);
        removeService.remove(product,userName);
        product.setOperOut(null);
        setPhotos(product,productExel,userName);
        return product;
    }

    private Product saveInit(ProductExel productExel, String userName,String productStr) throws NumberFormatException,NullPointerException,InvocationTargetException, IllegalArgumentException,IOException{
        Product product = setProductParametrs(productExel,userName,productStr);
        setPhotos(product,productExel,userName);
        return product;
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

    private Product setProductParametrs(ProductExel productExel, String userName,String productStr)
            throws NumberFormatException{
        Product product = initProductExelService.productInstance(productStr);
        initProductExelService.initProduct(product,productExel,productStr);
        Operations operationIn = new Operations();
        operationIn.setDateOper(new Date());
        operationIn.setTypeOper(OperationType.OPERATION_IN);
        operationIn.setUserName(userName);
        product.setOperIn(operationIn);
        return product;
    }

}



