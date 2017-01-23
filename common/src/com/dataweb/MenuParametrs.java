package com.dataweb;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 20.08.2016.
 */
public class MenuParametrs implements Serializable {

    @JsonProperty("minPrice")
    private Double minPrice;
    @JsonProperty("maxPrice")
    private Double maxPrice;
    @JsonProperty("listLength")
    private List listLength;
    @JsonProperty("listWidth")
    private List listWidth;
    @JsonProperty("listDepth")
    private List listDepth;

    public Double getMinPrice() {
        return minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public List getListLength() {
        return listLength;
    }

    public List getListWidth() {
        return listWidth;
    }

    public List getListDepth() {
        return listDepth;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setListLength(List listLength) {
        this.listLength = listLength;
    }

    public void setListWidth(List listWidth) {
        this.listWidth = listWidth;
    }

    public void setListDepth(List listDepth) {
        this.listDepth = listDepth;
    }
}
