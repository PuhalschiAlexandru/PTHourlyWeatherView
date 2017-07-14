package com.tapptitude.hourlyweatherview.networking;

import com.google.gson.Gson;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alexpuhalschi on 13/07/2017.
 */

public class ApiHelper {

    private static ApiHelper apiHelper;
    private WeatherApi weatherApi;
    private Gson mGson;

    private ApiHelper() {
        init();
    }

    public static ApiHelper getInstance() {
        return apiHelper == null ? new ApiHelper() : apiHelper;
    }

    public WeatherApi getApi() {
        return weatherApi;
    }

    private void init() {
        this.mGson = new Gson();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.wunderground.com/api/")
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .build();

        weatherApi = retrofit.create(WeatherApi.class);
    }
}
