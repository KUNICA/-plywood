package com.dataweb;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by user on 20.08.2016.
 */
public class IntervalPagination implements Serializable {

    @JsonProperty("start")
    protected Long start;
    @JsonProperty("end")
    protected Long end;
    @JsonProperty("minPrice")
    protected Double minPrice;
    @JsonProperty("maxPrice")
    protected Double maxPrice;
    @JsonProperty("minLength")
    protected Long minLength;
    @JsonProperty("maxLength")
    protected Long maxLength;
    @JsonProperty("minWidth")
    protected Long minWidth;
    @JsonProperty("maxWidth")
    protected Long maxWidth;
    @JsonProperty("minDepth")
    protected Long minDepth;
    @JsonProperty("maxDepth")
    protected Long maxDepth;

    public Long getStart() {
        return start;
    }

    public Long getEnd() {
        return end;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

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

    public Long getMaxWidth() {
        return maxWidth;
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

    public void setMaxWidth(Long maxWidth) {
        this.maxWidth = maxWidth;
    }

    public void setMinDepth(Long minDepth) {
        this.minDepth = minDepth;
    }

    public void setMaxDepth(Long maxDepth) {
        this.maxDepth = maxDepth;
    }
}
