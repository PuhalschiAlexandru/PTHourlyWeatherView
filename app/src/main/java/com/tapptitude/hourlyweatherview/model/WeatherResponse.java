
package com.tapptitude.hourlyweatherview.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class WeatherResponse {

    @SerializedName("response")
    private Response response;
    @SerializedName("hourly_forecast")
    private List<HourlyForecast> hourlyForecast = null;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public List<HourlyForecast> getHourlyForecast() {
        return hourlyForecast;
    }

    public void setHourlyForecast(List<HourlyForecast> hourlyForecast) {
        this.hourlyForecast = hourlyForecast;
    }
}
