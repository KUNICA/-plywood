package com.dataweb;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by user on 29.08.2016.
 */
public class ParticleboardIntervalPagination extends IntervalPagination {

    @JsonProperty("coating")
    protected Boolean coating;

    @JsonProperty("minLaminated")
    protected Long minLaminated;

    @JsonProperty("maxLaminated")
    protected Long maxLaminated;

    public  Boolean getCoating() {
        return coating;
    }

    public void setCoating(Boolean coating) {
        this.coating = coating;
    }

    public Long getMinLaminated() {
        return minLaminated;
    }

    public Long getMaxLaminated() {
        return maxLaminated;
    }

    public void setMinLaminated(Long minLaminated) {
        this.minLaminated = minLaminated;
    }

    public void setMaxLaminated(Long maxLaminated) {
        this.maxLaminated = maxLaminated;
    }
}
