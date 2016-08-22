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
    @JsonProperty("minPersons")
    private Long minPersons;
    @JsonProperty("maxPersons")
    private Long maxPersons;
    @JsonProperty("minBadrooms")
    private Long minBadrooms;
    @JsonProperty("maxBadrooms")
    private Long maxBadrooms;
    @JsonProperty("minBathrooms")
    private Long minBathrooms;
    @JsonProperty("maxBathrooms")
    private Long maxBathrooms;

    public Double getMinPrice() {
        return minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public Long getMinPersons() {
        return minPersons;
    }

    public Long getMaxPersons() {
        return maxPersons;
    }

    public Long getMinBadrooms() {
        return minBadrooms;
    }

    public Long getMaxBadrooms() {
        return maxBadrooms;
    }

    public Long getMinBathrooms() {
        return minBathrooms;
    }

    public Long getMaxBathrooms() {
        return maxBathrooms;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setMinPersons(Long minPersons) {
        this.minPersons = minPersons;
    }

    public void setMaxPersons(Long maxPersons) {
        this.maxPersons = maxPersons;
    }

    public void setMinBadrooms(Long minBadrooms) {
        this.minBadrooms = minBadrooms;
    }

    public void setMaxBadrooms(Long maxBadrooms) {
        this.maxBadrooms = maxBadrooms;
    }

    public void setMinBathrooms(Long minBathrooms) {
        this.minBathrooms = minBathrooms;
    }

    public void setMaxBathrooms(Long maxBathrooms) {
        this.maxBathrooms = maxBathrooms;
    }
}
