package com.nilesh.myweatherreport;

import com.nilesh.myweatherreport.POJOClasss.CurrentWeather;
import com.nilesh.myweatherreport.POJOClasss.WeatherForeCast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherDetailsInterface {




    @GET("/data/2.5/weather?appid=fef0a988d87fc9da61e9429d0ab50482")
    Call<CurrentWeather> getCurrentWeather(@Query("q") String cityname);

    @GET("/data/2.5/weather?appid=fef0a988d87fc9da61e9429d0ab50482")
    Call<CurrentWeather> getCurrentWeather(@Query("lat") String latitude,@Query("lon") String longitude);


    @GET("/data/2.5/forecast/daily?mode=json&units=metric&cnt=16&appid=fef0a988d87fc9da61e9429d0ab50482")
    Call<WeatherForeCast> getWeatherForeCast(@Query("lat") String latitude, @Query("lon") String longitude);


    @GET("/data/2.5/forecast/daily?mode=json&units=metric&cnt=16&appid=fef0a988d87fc9da61e9429d0ab50482")
    Call<WeatherForeCast> getWeatherForeCast(@Query("q") String cityname);
}

