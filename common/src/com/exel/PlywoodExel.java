package com.exel;

import com.entity.Grade;

/**
 * Created by user on 23.08.2016.
 */
public class PlywoodExel extends ProductExel{

    private String coating;
    private String coatingColor;
    private String waterResistance;
    private String sanded;
    private String grade;
    private String amountPackage;
    private String numberPackages;

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

    public String getGrade() {
        return grade;
    }

    public String getAmountPackage() {
        return amountPackage;
    }

    public String getNumberPackages() {
        return numberPackages;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setAmountPackage(String amountPackage) {
        this.amountPackage = amountPackage;
    }

    public void setNumberPackages(String numberPackages) {
        this.numberPackages = numberPackages;
    }
}
