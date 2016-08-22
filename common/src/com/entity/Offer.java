package com.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by user on 20.08.2016.
 */
@Entity
@Table(name = "offer", schema = "plywood_work")
public class Offer {
    private Long id;
    private double orderPrice;
    private Operations operationIn;
    private Operations operationOut;
    private Set<ShoppingCart> shoppingCart;

    public Offer() {
        this.shoppingCart = new HashSet<ShoppingCart>();
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "order_price", nullable = false, precision = 0)
    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="oper_in", updatable = true,insertable = true)
    public Operations getOperationIn() {
        return operationIn;
    }


    public void setOperationIn(Operations operationIn) {
        this.operationIn = operationIn;
    }

    public void setOperationOut(Operations operationOut) {
        this.operationOut = operationOut;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="oper_out", updatable = true,insertable = true)
    public Operations getOperationOut() {
        return operationOut;
    }

    @OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.ALL,CascadeType.PERSIST})
    @JoinColumn(name = "offer_id", referencedColumnName = "id")
    public Set<ShoppingCart> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(Set<ShoppingCart> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
