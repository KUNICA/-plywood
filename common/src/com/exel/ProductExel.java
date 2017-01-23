package com.exel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 20.08.2016.
 */
public class ProductExel {

    protected String productId;
    protected String type;
    protected String thickness;
    protected String length;
    protected String weight;
    protected String price;
    protected String shortDescription;
    protected Map<String,String> photos;

    public ProductExel() {
        this.photos = new HashMap<String,String>();
    }

    public Map<String, String> getPhotos() {
        return photos;
    }

    public String getProductId() {
        return productId;
    }

    public String getThickness() {
        return thickness;
    }

    public String getLength() {
        return length;
    }

    public String getWeight() {
        return weight;
    }

    public String getPrice() {
        return price;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setThickness(String thickness) {
        this.thickness = thickness;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setPhotos(Map<String, String> photos) {
        this.photos = photos;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

