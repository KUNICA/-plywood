package com.services.print;

import com.dao.shopingcart.ShopingCartProductDaoImpl;
import com.dataprint.PrintFilds;
import com.entity.Product;
import com.entity.Images;
import com.google.common.base.Strings;
import com.services.shopingcart.ShopingCartServiceIml;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 20.08.2016.
 */
@Named("printProductsService")
public class PrintProductsService implements PrintProductsServiceImpl {

    protected static final int IMAGE_MAIN = 0;
    protected static final int IMAGE_2 = 1;
    protected static final int IMAGE_3 = 2;
    protected static final int IMAGE_4 = 3;
    protected static final int IMAGE_5 = 4;
    protected static final int IMAGE_6 = 5;
    protected static final int IMAGE_7 = 6;


    protected ResourceLoader resourceLoader = new DefaultResourceLoader();

    @Inject
    @Named("shopingCartProductDao")
    protected ShopingCartProductDaoImpl shopingCartProductDao;

    @Override
    public List getListParamProducts(String userName) {
        List products = getProducts(userName);
        ArrayList<PrintFilds> list = new ArrayList<PrintFilds>();
        for (Object iter :products) {
            Product product = (Product)iter;
            PrintFilds fild = getFild(product);
            list.add(fild);
        }

        return list;
    }

    protected List getProducts(String userName){
        return shopingCartProductDao.getSumShoppingCartProduct(userName);
    }

    protected PrintFilds getFild(Product product){
        PrintFilds fild = setFild(product);

        for (Object iter :product.getPhotos()) {
            Images photo = (Images) iter;
            Resource resourceImage = resourceLoader.getResource("images/product/" + photo.getImg());
            if(resourceImage.exists())
                if(photo.getMain()!=null && photo.getMain() && photo.getOperationOut() == null){
                    setImage(resourceImage,fild,0);
                    break;
                }
        }

        int i=1;
        for (Object iter :product.getPhotos()) {
            Images photo = (Images) iter;
            Resource resourceImage = resourceLoader.getResource("images/product/" + photo.getImg());
            if(resourceImage.exists())
                if(photo.getMain()==null && photo.getOperationOut() == null){
                    setImage(resourceImage,fild,i);
                    i++;
                }
        }
        return fild;
    }

    protected PrintFilds setFild(Product product){
        PrintFilds fild = new PrintFilds();
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);
        if(product.getPrice()!=null) {
            fild.setPrice(formatter.format(product.getPrice()));
        }
        if(!Strings.isNullOrEmpty(product.getName())){
            fild.setName(product.getName());
        }
        if(!Strings.isNullOrEmpty(product.getShortDescription())){
            fild.setDescription(product.getShortDescription());
        }
        if(product.getLength()!=null){
            fild.setLength(formatter.format(product.getLength()));
        }
        if(product.getWidth()!=null){
            fild.setWidth(formatter.format(product.getWidth()));
        }
        if(product.getDepth()!=null){
            fild.setDepth(formatter.format(product.getDepth()));
        }
        if(product.getType()!=null){
            fild.setType(product.getType().name());
        }
        return fild;
    }

    protected void setImage(Resource resourceImage,PrintFilds fild, int i){
        try {
            switch(i){
                case IMAGE_MAIN:
                    fild.setImage1(resourceImage.getFile().getPath());
                    break;
                case IMAGE_2:
                    fild.setImage2(resourceImage.getFile().getPath());
                    break;
                case IMAGE_3:
                    fild.setImage3(resourceImage.getFile().getPath());
                    break;
                case IMAGE_4:
                    fild.setImage4(resourceImage.getFile().getPath());
                    break;
                case IMAGE_5:
                    fild.setImage5(resourceImage.getFile().getPath());
                    break;
                case IMAGE_6:
                    fild.setImage6(resourceImage.getFile().getPath());
                    break;
                case IMAGE_7:
                    fild.setImage7(resourceImage.getFile().getPath());
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
