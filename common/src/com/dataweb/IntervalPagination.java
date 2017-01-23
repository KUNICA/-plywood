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
    @JsonProperty("length")
    protected Long length;
    @JsonProperty("lengthAll")
    protected Boolean lengthAll;
    @JsonProperty("width")
    protected Long width;
    @JsonProperty("widthAll")
    protected Boolean widthAll;
    @JsonProperty("depth")
    protected Long depth;
    @JsonProperty("depthAll")
    protected Boolean depthAll;

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

    public Long getLength() {
        return length;
    }

    public Long getWidth() {
        return width;
    }

    public Long getDepth() {
        return depth;
    }

    public Boolean getLengthAll() {
        return lengthAll;
    }

    public Boolean getWidthAll() {
        return widthAll;
    }

    public Boolean getDepthAll() {
        return depthAll;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public void setDepth(Long depth) {
        this.depth = depth;
    }

    public void setLengthAll(Boolean lengthAll) {
        this.lengthAll = lengthAll;
    }

    public void setWidthAll(Boolean widthAll) {
        this.widthAll = widthAll;
    }

    public void setDepthAll(Boolean depthAll) {
        this.depthAll = depthAll;
    }
}
