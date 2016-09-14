package com.dao.shopingcart;

import com.entity.Images;
import com.entity.Product;
import com.entity.ShoppingCart;
import com.entity.Type;

import java.util.List;

/**
 * Created by user on 29.08.2016.
 */
public class ShopingCartDto {

    private Long id;
    private Double price;
    private String shortDescription;
    private String name;
    private Long count;
    protected Long length;
    protected Type type;
    protected Long width;
    protected Long depth;
    protected List<Images> photos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getName() {
        return name;
    }

    public Long getCount() {
        return count;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getLength() {
        return length;
    }

    public Type getType() {
        return type;
    }

    public Long getWidth() {
        return width;
    }

    public Long getDepth() {
        return depth;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public void setDepth(Long depth) {
        this.depth = depth;
    }

    public List<Images> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Images> photos) {
        this.photos = photos;
    }
}
