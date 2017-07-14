
package com.tapptitude.hourlyweatherview.model;

import com.google.gson.annotations.SerializedName;

public class Windchill {

    @SerializedName("english")
    private String english;
    @SerializedName("metric")
    private String metric;

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }
}
