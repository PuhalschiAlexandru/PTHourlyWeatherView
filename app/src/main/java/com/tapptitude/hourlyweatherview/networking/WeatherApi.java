package com.tapptitude.hourlyweatherview.networking;

import com.tapptitude.hourlyweatherview.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by alexpuhalschi on 13/07/2017.
 */

public interface WeatherApi {

    @GET("{api_key}/{data_feature}/q/{country}/{city}.json")
    Call<WeatherResponse> getData(@Path("api_key") String apiKey,
                                  @Path("data_feature") String dataFeature,
                                  @Path("country") String country,
                                  @Path("city") String city);
}
