package com.services.offer;

import com.dao.product.ProductDaoImpl;
import com.dao.offer.OfferDaoImpl;
import com.entity.*;
import com.services.shopingcart.ShopingCartServiceIml;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;

/**
 * Created by user on 20.08.2016.
 */
@Named
public class OfferService implements OfferServiceImpl {

    @Inject
    private ShopingCartServiceIml shopingCartServiceIml;

    @Inject
    private ProductDaoImpl productDaoImpl;

    @Inject
    private OfferDaoImpl OfferDao;

    @Override
    public Object create(String userName) {
        List list = shopingCartServiceIml.getShoppingCarts(userName);
        Offer offer = new Offer();
        Set<ShoppingCart> shoppingCar = new HashSet<ShoppingCart>();
        Double sumOffer = 0D;
        for (Object iter :list) {
            ShoppingCart productCart = (ShoppingCart)iter;
            productCart.setActual(1L);
            shoppingCar.add(productCart);
            Product product = (Product)productDaoImpl.getProduct(productCart.getProductId());
            sumOffer+=product.getPrice();
        }
        offer.setShoppingCart(shoppingCar);
        offer.setOrderPrice(sumOffer);
        Operations operationIn = new Operations();
        operationIn.setDateOper(new Date());
        operationIn.setTypeOper(OperationType.OPERATION_IN);
        operationIn.setUserName(userName);
        offer.setOperationIn(operationIn);

        return offer;
    }

    @Override
    public Object getOffer(String userName) {
        return OfferDao.getOffer(userName);
    }

    public List getProducts(Set shopingCartlist){
        ArrayList<Product> products = new ArrayList<Product>();
        for (Object iter :shopingCartlist) {
            ShoppingCart productCart = (ShoppingCart)iter;
            Product product = (Product)productDaoImpl.getProduct(productCart.getProductId());
            products.add(product);
        }
        return products;
    }
}
