package com.dataweb;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by user on 29.08.2016.
 */
public class ParticleboardsMenuParametrs extends MenuParametrs {

    @JsonProperty("minLaminated")
    private Long minLaminated;
    @JsonProperty("maxLaminated")
    private Long maxLaminated;

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
