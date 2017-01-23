package com.entity;

import javax.persistence.*;

/**
 * Created by user on 20.08.2016.
 */
@Entity
@Table(name = "plywood")
public class Plywood extends Product{

    private Boolean coating;
    private Long colorCoating;
    private Boolean waterResistance;
    private Boolean sanded;
    private Grade grade;
    private Long amountPackage;
    private Long numberPackages;

    public Plywood() {
        super();
        this.type = Type.Plywood;
    }

    @Basic
    @Column(name = "coating", nullable = true, length = 1)
    public Boolean getCoating() {
        return coating;
    }

    public void setCoating(Boolean coating) {
        this.coating = coating;
    }

    @Basic
    @Column(name = "color_coating", nullable = true, length = 225)
    public Long getColorCoating() {
        return colorCoating;
    }

    public void setColorCoating(Long colorCoating) {
        this.colorCoating = colorCoating;
    }

    @Basic
    @Column(name = "water_resistance", nullable = false, length = 1)
    public Boolean getWaterResistance() {
        return waterResistance;
    }

    @Basic
    @Column(name = "sanded", nullable = false, length = 1)
    public Boolean getSanded() {
        return sanded;
    }

    public void setWaterResistance(Boolean waterResistance) {
        this.waterResistance = waterResistance;
    }

    public void setSanded(Boolean sanded) {
        this.sanded = sanded;
    }

    @Basic
    @Column(name = "grade")
    @Enumerated(EnumType.STRING)
    public Grade getGrade() {
        return grade;
    }

    @Basic
    @Column(name = "amount_package", nullable = true, length = 225)
    public Long getAmountPackage() {
        return amountPackage;
    }

    @Basic
    @Column(name = "number_packages", nullable = true, length = 225)
    public Long getNumberPackages() {
        return numberPackages;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public void setAmountPackage(Long amountPackage) {
        this.amountPackage = amountPackage;
    }

    public void setNumberPackages(Long numberPackages) {
        this.numberPackages = numberPackages;
    }
}
