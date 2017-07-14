
package com.tapptitude.hourlyweatherview.model;

import com.google.gson.annotations.SerializedName;

public class Snow {

    @SerializedName("english")
    private String english;
    @SerializedName("metric")
    private String metric;

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public String getEnglish() {

        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }
}
