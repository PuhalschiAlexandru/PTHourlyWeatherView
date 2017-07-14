
package com.tapptitude.hourlyweatherview.model;

import com.google.gson.annotations.SerializedName;

public class Wdir {

    @SerializedName("dir")
    private String dir;
    @SerializedName("degrees")
    private String degrees;

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getDegrees() {
        return degrees;
    }

    public void setDegrees(String degrees) {
        this.degrees = degrees;
    }
}
