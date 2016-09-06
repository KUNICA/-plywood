package com.entity;

import javax.persistence.*;

/**
 * Created by user on 20.08.2016.
 */
@Entity
@Table(name = "particleboard", schema = "plywood_work")
public class Particleboard extends Product {

    private Boolean coating;
    private Grade grade;
    private Long laminated;

    public Particleboard() {
        super();
        this.type = Type.Particleboard;
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
    @Column(name = "grade")
    @Enumerated(EnumType.STRING)
    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "laminated", nullable = true)
    public Long getLaminated() {
        return laminated;
    }

    public void setLaminated(Long laminated) {
        this.laminated = laminated;
    }

}
