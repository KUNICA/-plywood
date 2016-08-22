package com.dataweb;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by user on 20.08.2016.
 */
public class IntervalPagination implements Serializable {

    @JsonProperty("start")
    private Long start;
    @JsonProperty("end")
    private Long end;
    @JsonProperty("minPrice")
    private Double minPrice;
    @JsonProperty("maxPrice")
    private Double maxPrice;
    @JsonProperty("persons")
    private Long persons;
    @JsonProperty("badrooms")
    private Long badrooms;
    @JsonProperty("bathrooms")
    private Long bathrooms;

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

    public Long getPersons() {
        return persons;
    }

    public Long getBadrooms() {
        return badrooms;
    }

    public Long getBathrooms() {
        return bathrooms;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setPersons(Long persons) {
        this.persons = persons;
    }

    public void setBadrooms(Long badrooms) {
        this.badrooms = badrooms;
    }

    public void setBathrooms(Long bathrooms) {
        this.bathrooms = bathrooms;
    }
}
