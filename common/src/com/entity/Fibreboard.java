package com.entity;

import javax.persistence.*;

/**
 * Created by user on 20.08.2016.
 */
@Entity
@DiscriminatorValue(value = "Fibreboard")
public class Fibreboard extends Product {

    private String coating;
    private String grade;

    @Basic
    @Column(name = "coating", nullable = true, length = 1)
    public String getCoating() {
        return coating;
    }

    public void setCoating(String coating) {
        this.coating = coating;
    }

    @Basic
    @Column(name = "grade", nullable = true, length = 225)
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

}
