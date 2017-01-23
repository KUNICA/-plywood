package com.dataweb;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by user on 29.08.2016.
 */
public class ParticleboardIntervalPagination extends IntervalPagination {

    @JsonProperty("sanded")
    protected Boolean sanded;

    public Boolean getSanded() {
        return sanded;
    }

    public void setSanded(Boolean sanded) {
        this.sanded = sanded;
    }
}
