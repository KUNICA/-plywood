package com.services.print;

import com.dao.shopingcart.ShopingCartProductDaoImpl;
import com.dataprint.PrintFilds;
import com.entity.Plywood;
import com.entity.Product;

import javax.inject.Inject;
import javax.inject.Named;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by user on 30.08.2016.
 */
@Named("printPlywoodService")
public class PrintPlywoodService extends PrintProductsService implements PrintProductsServiceImpl {

    @Inject
    @Named("shopingCartPlywoodDao")
    protected ShopingCartProductDaoImpl shopingCartPlywoodDao;

    protected PrintFilds setFild(Product product) {
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);
        PrintFilds filds = super.setFild(product);
        if (product instanceof Plywood) {
            Plywood plywood = (Plywood) product;
            if (plywood.getColorCoating() != null) {
                filds.setLaminated(formatter.format(plywood.getColorCoating()));
            }
            if (plywood.getCoating() != null && plywood.getCoating()) {
                filds.setCoatingPlywood("Yes");
            } else {
                filds.setCoatingPlywood("No");
            }
            if (plywood.getWaterResistance() != null && plywood.getWaterResistance()) {
                filds.setWaterResistance("Fk");
            } else {
                filds.setWaterResistance("-");
            }
            if (plywood.getSanded() != null && plywood.getSanded()) {
                filds.setSanded("Sanded");
            } else {
                filds.setSanded("Unsanded");
            }
        }
        return filds;
    }

    protected List getProducts(String userName){
        return shopingCartPlywoodDao.getSumShoppingCartProduct(userName);
    }
}
