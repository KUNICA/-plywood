package com.entity;

import javax.persistence.*;

/**
 * Created by user on 20.08.2016.
 */
@Entity
@DiscriminatorValue(value = "Plywood")
public class Plywood extends Product{

    private String coating;
    private String colorCoating;


    @Basic
    @Column(name = "coating", nullable = true, length = 1)
    public String getCoating() {
        return coating;
    }

    public void setCoating(String coating) {
        this.coating = coating;
    }

    @Basic
    @Column(name = "color_coating", nullable = true, length = 225)
    public String getColorCoating() {
        return colorCoating;
    }

    public void setColorCoating(String colorCoating) {
        this.colorCoating = colorCoating;
    }

}
