
package com.tapptitude.hourlyweatherview.model;

import com.google.gson.annotations.SerializedName;

public class Feelslike {

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
}
