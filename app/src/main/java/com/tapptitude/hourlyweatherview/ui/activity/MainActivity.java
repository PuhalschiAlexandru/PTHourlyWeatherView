package com.tapptitude.hourlyweatherview.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.tapptitude.hourlyweatherview.R;
import com.tapptitude.hourlyweatherview.model.WeatherResponse;
import com.tapptitude.hourlyweatherview.networking.ApiHelper;
import com.tapptitude.hourlyweatherview.ui.widget.CustomDiagram;


import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static final String DATA_FEATURE = "hourly";
    public static final String COUNTRY = "RO";
    public static final String CITY = "Cluj-Napoca";

    @BindView(R.id.am_cd_diagram_of_values)
    CustomDiagram mCustomDiagram;
    int[] mWeatherData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getForecast();
    }

    private void getForecast() {
        ApiHelper.getInstance().getApi()
                .getData(getString(R.string.weather_underground_key), DATA_FEATURE, COUNTRY, CITY)
                .enqueue(new Callback<WeatherResponse>() {
                    @Override
                    public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                        if (response.isSuccessful()) {
                            WeatherResponse weatherResponse = response.body();
                            mWeatherData = new int[weatherResponse.getHourlyForecast().size() - 1];
                            for (int i = 0; i < weatherResponse.getHourlyForecast().size() - 1; i++) {
                                mWeatherData[i] = ((Integer.parseInt(weatherResponse
                                        .getHourlyForecast().get(i).getTemp().getMetric())));
                                Log.d("WEATHER_DATA", "" + Integer.parseInt(weatherResponse
                                        .getHourlyForecast().get(i).getTemp().getMetric()));
                            }
                            mCustomDiagram.setData(mWeatherData);
                            mCustomDiagram.invalidate();
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherResponse> call, Throwable t) {
                    }
                });
    }
}
