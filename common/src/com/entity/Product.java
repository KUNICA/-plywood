package com.entity;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by user on 20.08.2016.
 */
@Entity
@FetchProfile(name = "mediaProfile", fetchOverrides = {
        @FetchProfile.FetchOverride(entity = Product.class, association = "photos", mode = FetchMode.JOIN)})
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(
        name="type",
        discriminatorType=DiscriminatorType.STRING)
@Table(name = "product")
public class Product {
    protected Long id;
    protected Operations operOut;
    protected Operations operIn;
    protected Double price;
    protected String shortDescription;
    protected String name;
    protected Long length;
    protected Type type;
    protected Long width;
    protected Long depth;
    protected String productId;
    protected List<Images> photos;

    public Product() {
        this.photos = new ArrayList<Images>();
        this.type = Type.Product;
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
    @Column(name = "unic_id", length = 225)
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "price", nullable = true, precision = 5)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "short_description", nullable = true, length = -1)
    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 225)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "length", nullable = true)
    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    @Basic
    @Column(name = "width", nullable = true)
    public Long getWidth() {
        return width;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    @Basic
    @Column(name = "depth", nullable = true)
    public Long getDepth() {
        return depth;
    }

    public void setDepth(Long depth) {
        this.depth = depth;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="oper_in", updatable = true,insertable = true)
    public Operations getOperIn() {
        return this.operIn;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="oper_out", updatable = true,insertable = true)
    public Operations getOperOut() {
        return this.operOut;
    }


    public void setOperIn(Operations operationIn) {
        this.operIn = operationIn;
    }

    public void setOperOut(Operations operationOut) {
        this.operOut = operationOut;
    }

    @OneToMany(cascade = {CascadeType.ALL,CascadeType.PERSIST})
    @LazyCollection(LazyCollectionOption.FALSE )
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @Where(clause="operation_out is null")
    public List<Images> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Images> photos) {
        this.photos = photos;
    }

    @Basic
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (operIn != product.operIn) return false;
        if (operOut != null ? !operOut.equals(product.operOut) : product.operOut != null) return false;
        if (price != null ? !price.equals(product.price) : product.price != null) return false;
        if (shortDescription != null ? !shortDescription.equals(product.shortDescription) : product.shortDescription != null)
            return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (length != null ? !length.equals(product.length) : product.length != null) return false;
        if (width != null ? !width.equals(product.width) : product.width != null) return false;
        if (depth != null ? !depth.equals(product.depth) : product.depth != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.intValue();
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (shortDescription != null ? shortDescription.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (length != null ? length.hashCode() : 0);
        result = 31 * result + (width != null ? width.hashCode() : 0);
        result = 31 * result + (depth != null ? depth.hashCode() : 0);
        return result;
    }
}
