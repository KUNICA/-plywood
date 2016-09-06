package com.exel;

/**
 * Created by user on 23.08.2016.
 */
public class PlywoodExel extends ProductExel{

    private String coating;
    private String coatingColor;
    private String waterResistance;
    private String sanded;

    public PlywoodExel() {
        super();
    }

    public String getWaterResistance() {
        return waterResistance;
    }

    public String getSanded() {
        return sanded;
    }

    public void setWaterResistance(String waterResistance) {
        this.waterResistance = waterResistance;
    }

    public void setSanded(String sanded) {
        this.sanded = sanded;
    }

    public String getCoating() {
        return coating;
    }

    public String getCoatingColor() {
        return coatingColor;
    }

    public void setCoating(String coating) {
        this.coating = coating;
    }

    public void setCoatingColor(String coatingColor) {
        this.coatingColor = coatingColor;
    }
}
