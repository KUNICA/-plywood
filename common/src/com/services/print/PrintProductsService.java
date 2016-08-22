package com.services.print;

import com.dataprint.PrintFilds;
import com.entity.Product;
import com.entity.Images;
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
@Named
public class PrintProductsService implements PrintProductsServiceImpl {

    private static final int IMAGE_MAIN = 0;
    private static final int IMAGE_2 = 1;
    private static final int IMAGE_3 = 2;
    private static final int IMAGE_4 = 3;
    private static final int IMAGE_5 = 4;


    private ResourceLoader resourceLoader = new DefaultResourceLoader();

    @Inject
    private ShopingCartServiceIml shopingCartService;

    @Override
    public List getListParamProducts(String userName) {
        List products = shopingCartService.getSumShoppingCart(userName);
        ArrayList<PrintFilds> list = new ArrayList<PrintFilds>();
        for (Object iter :products) {
            Product product = (Product)iter;
            PrintFilds fild = getFild(product);
            list.add(fild);
        }

        return list;
    }

    private PrintFilds getFild(Product product){
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);
        PrintFilds fild = new PrintFilds();
        if(product.getPrice()!=null)
            fild.setPrice(formatter.format(product.getPrice()));
        if(!Strings.isNullOrEmpty(product.getShortDescription())){
            fild.setDescription(product.getShortDescription());
        }

        for (Object iter :product.getPhotos()) {
            Images photo = (Images) iter;
            Resource resourceImage = resourceLoader.getResource("images/products/" + photo.getImg());
            if(resourceImage.exists())
                if(photo.getMain()){
                    setImage(resourceImage,fild,0);
                    break;
                }
        }

        int i=1;
        for (Object iter :product.getPhotos()) {
            Images photo = (Images) iter;
            Resource resourceImage = resourceLoader.getResource("images/products/" + photo.getImg());
            if(resourceImage.exists())
                if(!photo.getMain()){
                    setImage(resourceImage,fild,i);
                }
            i++;
        }
        return fild;
    }

    private void setImage(Resource resourceImage,PrintFilds fild, int i){
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
