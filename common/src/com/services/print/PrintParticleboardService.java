package com.services.print;

import com.dao.shopingcart.ShopingCartProductDaoImpl;
import com.dataprint.PrintFilds;
import com.entity.Particleboard;
import com.entity.Product;
import com.google.common.base.Strings;

import javax.inject.Inject;
import javax.inject.Named;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by user on 30.08.2016.
 */
@Named("printParticleboardService")
public class PrintParticleboardService extends PrintProductsService implements PrintProductsServiceImpl {

    @Inject
    @Named("shopingCartParticleboardDao")
    protected ShopingCartProductDaoImpl shopingCartParticleboardDao;

    protected PrintFilds setFild(Product product){
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);
        PrintFilds filds =  super.setFild(product);
        if(product instanceof Particleboard){
            Particleboard particleboard = (Particleboard)product;
            if (particleboard.getSanded() != null && particleboard.getSanded()) {
                filds.setSanded("Sanded");
            } else {
                filds.setSanded("Unsanded");
            }
        }

        return filds;
    }

    protected List getProducts(String userName){
        return shopingCartParticleboardDao.getSumShoppingCartProduct(userName);
    }
}
