package com.dataweb;

import com.entity.Grade;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by user on 27.08.2016.
 */
public class PlywoodIntervalPagination extends IntervalPagination {

    @JsonProperty("sanded")
    protected Boolean sanded;
    @JsonProperty("resistance")
    protected Boolean resistance;
    @JsonProperty("grade")
    protected String grade;
    @JsonProperty("gradeAll")
    protected Boolean gradeAll;


    public Boolean getSanded() {
        return sanded;
    }

    public void setSanded(Boolean sanded) {
        this.sanded = sanded;
    }

    public Boolean getResistance() {
        return resistance;
    }

    public void setResistance(Boolean resistance) {
        this.resistance = resistance;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Boolean getGradeAll() {
        return gradeAll;
    }

    public void setGradeAll(Boolean gradeAll) {
        this.gradeAll = gradeAll;
    }
}
