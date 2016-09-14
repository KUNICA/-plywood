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
}
