package com.exel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 20.08.2016.
 */
public class ProductExel {

    private String id;
    private String name;
    private String city;
    private String street;
    private String house;
    private String housing;
    private String apartment;
    private String sleeps;
    private String countRooms;
    private String countFloors;
    private String countBathrooms;
    private String price;
    private String shortDescription;
    private Map<String,String> photos;
    private Map<String,String> videos;

    public ProductExel() {
        this.photos = new HashMap<String,String>();
        this.videos = new HashMap<String,String>();
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHouse() {
        return house;
    }

    public String getHousing() {
        return housing;
    }

    public String getApartment() {
        return apartment;
    }

    public String getSleeps() {
        return sleeps;
    }

    public String getCountRooms() {
        return countRooms;
    }

    public String getCountFloors() {
        return countFloors;
    }

    public String getCountBathrooms() {
        return countBathrooms;
    }

    public String getPrice() {
        return price;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public Map<String, String> getPhotos() {
        return photos;
    }

    public Map<String, String> getVideos() {
        return videos;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public void setHousing(String housing) {
        this.housing = housing;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public void setSleeps(String sleeps) {
        this.sleeps = sleeps;
    }

    public void setCountRooms(String countRooms) {
        this.countRooms = countRooms;
    }

    public void setCountFloors(String countFloors) {
        this.countFloors = countFloors;
    }

    public void setCountBathrooms(String countBathrooms) {
        this.countBathrooms = countBathrooms;
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

    public void setVideos(Map<String, String> videos) {
        this.videos = videos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

