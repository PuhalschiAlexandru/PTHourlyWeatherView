
package com.tapptitude.hourlyweatherview.model;

import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("version")
    private String version;
    @SerializedName("termsofService")
    private String termsofService;
    @SerializedName("features")
    private Features features;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTermsofService() {
        return termsofService;
    }

    public void setTermsofService(String termsofService) {
        this.termsofService = termsofService;
    }

    public Features getFeatures() {
        return features;
    }

    public void setFeatures(Features features) {
        this.features = features;
    }
}
