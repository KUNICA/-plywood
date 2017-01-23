package com.exel;

/**
 * Created by user on 23.08.2016.
 */
public class ParticleboardExel extends ProductExel {

    private String laminated;

    private String grade;

    private String coating;

    private String sanded;
    private String amountPackage;
    private String numberPackages;

    public String getLaminated() {
        return laminated;
    }

    public void setLaminated(String laminated) {
        this.laminated = laminated;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setCoating(String coating) {
        this.coating = coating;
    }

    public String getCoating() {
        return coating;
    }

    public String getSanded() {
        return sanded;
    }

    public String getAmountPackage() {
        return amountPackage;
    }

    public String getNumberPackages() {
        return numberPackages;
    }

    public void setSanded(String sanded) {
        this.sanded = sanded;
    }

    public void setAmountPackage(String amountPackage) {
        this.amountPackage = amountPackage;
    }

    public void setNumberPackages(String numberPackages) {
        this.numberPackages = numberPackages;
    }
}
