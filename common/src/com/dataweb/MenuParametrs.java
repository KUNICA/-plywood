package com.dataweb;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by user on 20.08.2016.
 */
public class MenuParametrs implements Serializable {

    @JsonProperty("minPrice")
    private Double minPrice;
    @JsonProperty("maxPrice")
    private Double maxPrice;
    @JsonProperty("minLength")
    private Long minLength;
    @JsonProperty("maxLength")
    private Long maxLength;
    @JsonProperty("minWidth")
    private Long minWidth;
    @JsonProperty("maxWidth")
    private Long maxBWidth;
    @JsonProperty("minDepth")
    private Long minDepth;
    @JsonProperty("maxDepth")
    private Long maxDepth;

    public Double getMinPrice() {
        return minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public Long getMinLength() {
        return minLength;
    }

    public Long getMaxLength() {
        return maxLength;
    }

    public Long getMinWidth() {
        return minWidth;
    }

    public Long getMaxBWidth() {
        return maxBWidth;
    }

    public Long getMinDepth() {
        return minDepth;
    }

    public Long getMaxDepth() {
        return maxDepth;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setMinLength(Long minLength) {
        this.minLength = minLength;
    }

    public void setMaxLength(Long maxLength) {
        this.maxLength = maxLength;
    }

    public void setMinWidth(Long minWidth) {
        this.minWidth = minWidth;
    }

    public void setMaxWidth(Long maxBWidth) {
        this.maxBWidth = maxBWidth;
    }

    public void setMinDepth(Long minDepth) {
        this.minDepth = minDepth;
    }

    public void setMaxDepth(Long maxDepth) {
        this.maxDepth = maxDepth;
    }
}
