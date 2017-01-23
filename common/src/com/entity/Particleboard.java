package com.entity;

import javax.persistence.*;

/**
 * Created by user on 20.08.2016.
 */
@Entity
@Table(name = "particleboard")
public class Particleboard extends Product {

    private Boolean coating;
    private Grade grade;
    private Long laminated;
    private Boolean sanded;
    private Long amountPackage;
    private Long numberPackages;

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

    @Basic
    @Column(name = "sanded", nullable = true, length = 1)
    public Boolean getSanded() {
        return sanded;
    }

    public void setSanded(Boolean sanded) {
        this.sanded = sanded;
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

    public void setAmountPackage(Long amountPackage) {
        this.amountPackage = amountPackage;
    }

    public void setNumberPackages(Long numberPackages) {
        this.numberPackages = numberPackages;
    }
}
