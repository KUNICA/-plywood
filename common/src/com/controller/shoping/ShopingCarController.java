package com.controller.shoping;

import com.dataweb.Product;
import com.entity.ShoppingCart;
import com.services.shopingcart.ShopingCartServiceIml;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by user on 20.08.2016.
 */
@Controller
@RequestMapping("/shoping")
public class ShopingCarController {


    @Inject
    private ShopingCartServiceIml shopingCartService;

    @RequestMapping(value = "/cart/{productId}", method = RequestMethod.POST)
    @RolesAllowed(value={"ROLE_USER", "ROLE_ADMIN"})
    public @ResponseBody
    Product getCartProduct(@PathVariable("productId") long productId) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        ShoppingCart shoppingCartEntity = (ShoppingCart)shopingCartService.getShoppingCart(userName,productId);
        return shoppingCartEntity!=null ? new Product(shoppingCartEntity,userName): new Product(userName) ;
    }

    @RequestMapping(value = "/save",
            method = RequestMethod.POST, consumes="application/json", produces="application/json",
            headers = {"Accept=text/xml, application/json"})
    @RolesAllowed(value={"ROLE_USER", "ROLE_ADMIN"})
    public @ResponseBody
    Product setCart(@RequestBody Product product) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        ShoppingCart shoppingCartEntity = (ShoppingCart) shopingCartService.checkProduct(product,userName);
        return shoppingCartEntity!=null ? new Product(shoppingCartEntity,userName): new Product(userName) ;
    }

    @RequestMapping(value = "/ischeck/{produtcId}", method = RequestMethod.GET)
    public @ResponseBody Boolean isCheck(@PathVariable("produtcId") long id) {
        return shopingCartService.isProductCheck(id);
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public @ResponseBody
    List getProducts() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return shopingCartService.getSumShoppingCart(userName);
    }

}
