package com.dataweb;

import com.entity.ShoppingCart;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by user on 20.08.2016.
 */
public class Product implements Serializable {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("productId")
    private Long productId;
    @JsonProperty("totalAmount")
    private Double totalAmount;
    @JsonProperty("actual")
    private Long actual;
    @JsonProperty("check")
    private Boolean check;
    @JsonProperty("count")
    private Long count;

    public Product(){

    }

    public Product(ShoppingCart shoppingCart,String userName) {
        this.id = shoppingCart.getId();
        this.username = userName;
        this.productId = shoppingCart.getProductId();
        this.totalAmount = shoppingCart.getTotalAmount();
        this.actual = shoppingCart.getActual();
        this.check = shoppingCart.getCheck();
        this.count = shoppingCart.getCount();
    }
    public Product(String userName) {
        this.username = userName;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Long getProductId() {
        return productId;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public Long getActual() {
        return actual;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setActual(Long actual) {
        this.actual = actual;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
