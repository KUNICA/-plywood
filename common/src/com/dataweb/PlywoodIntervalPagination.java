package com.dataweb;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by user on 27.08.2016.
 */
public class PlywoodIntervalPagination extends IntervalPagination {

    @JsonProperty("minCoating")
    protected Long  minCoating;
    @JsonProperty("maxCoating")
    protected Long maxCoating;

    @JsonProperty("sanded")
    protected Boolean sanded;
    @JsonProperty("resistance")
    protected Boolean resistance;


    @JsonProperty("coating")
    protected Boolean coating;

    public Boolean getSanded() {
        return sanded;
    }

    public void setSanded(Boolean sanded) {
        this.sanded = sanded;
    }

    public Boolean getResistance() {
        return resistance;
    }

    public void setResistance(Boolean resistance) {
        this.resistance = resistance;
    }

    public Long getMinCoating() {
        return minCoating;
    }

    public void setMinCoating(Long minCoating) {
        this.minCoating = minCoating;
    }

    public void setMaxCoating(Long maxCoating) {
        this.maxCoating = maxCoating;
    }

    public Long getMaxCoating() {
        return maxCoating;
    }

    public Boolean getCoating() {
        return coating;
    }

    public void setCoating(Boolean coating) {
        this.coating = coating;
    }
}
