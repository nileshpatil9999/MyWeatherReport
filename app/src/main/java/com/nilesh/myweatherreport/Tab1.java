package com.nilesh.myweatherreport;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Typeface;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nilesh.myweatherreport.POJOClasss.CurrentWeather;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nilesh.myweatherreport.databinding.ActivityMainBinding;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Tab1 extends Fragment {

    GPSTracker tracker;
    String latitude,longitude;

    ActivityMainBinding binding;
    View rootView;
    CharSequence cityname = null;


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.activity_main, container, false);
        rootView = binding.getRoot();




        if (getArguments() != null) {
            cityname = getArguments().getCharSequence("city");
            getcurrentweatherreport();
        }else
        {

            tracker=new GPSTracker(getActivity());

            if(tracker.isEnabled())
            {



                double lat=tracker.getlatitude();
                double longi=tracker.getlongitude();
                latitude=  String.valueOf(lat);
                longitude=  String.valueOf(longi);

                getcurrentweatherreport();






            }
            else
            {

                tracker.showSettingsAlert();
            }


        }





        return rootView;

    }


    private void getcurrentweatherreport() {


        final Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        WeatherDetailsInterface api = retrofit.create(WeatherDetailsInterface.class);

        Call<CurrentWeather> call = null;

        if (cityname != null) {

            call = api.getCurrentWeather(cityname.toString());
            Log.v("curntwther by citynm ",call.request().toString());
        } else {
            if(latitude!=null && longitude!=null && !latitude.equals(0.0) && !longitude.equals(0.0)) {
                call = api.getCurrentWeather(latitude, longitude);
                Log.v("curntwther by lat & lon",call.request().toString());
            }
        }


        if(cityname!=null || (latitude!=null && longitude!=null )) {
            call.enqueue(new Callback<CurrentWeather>() {
                @Override
                public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {


                    CurrentWeather weather = new CurrentWeather(response.body().getName());


                    binding.setCurrentweather(weather);


                    String sunrise = response.body().getSys().getSunrise();
                    String sunset = response.body().getSys().getSunset();


                    Calendar d = Calendar.getInstance();


                    d.setTimeInMillis(Long.valueOf(sunrise));
                    Calendar d1 = Calendar.getInstance();
                    d1.setTimeInMillis(Long.valueOf(sunset));


                    CurrentWeather.Sys s = new CurrentWeather.Sys(String.valueOf(d.get(Calendar.HOUR_OF_DAY)) + ":" + String.valueOf(d.get(Calendar.MINUTE)), String.valueOf(d1.get(Calendar.HOUR_OF_DAY)) + ":" + String.valueOf(d1.get(Calendar.MINUTE)));

                    binding.setSys(s);

                    String currenttempkelvin = response.body().getMain().getTemp();
                    String mintempkelvin = response.body().getMain().getTemp_min();
                    String maxtempkelvin = response.body().getMain().getTemp_max();

                    Double curtempcel = round(Double.valueOf(currenttempkelvin) - 273.15, 2);
                    Double mintempcel = round(Double.valueOf(mintempkelvin) - 273.15, 2);


                    Double maxtempcel = round(Double.valueOf(maxtempkelvin) - 273.0, 2);


                    CurrentWeather.Main m = new CurrentWeather.Main(String.valueOf(curtempcel), String.valueOf(mintempcel), String.valueOf(maxtempcel), response.body().getMain().getHumidity());

                    binding.setMain(m);


                }

                @Override
                public void onFailure(Call<CurrentWeather> call, Throwable t) {

                }
            });
        }


    }

    private Double round(double v, int i) {


        if (i < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, i);
        v = v * factor;
        long tmp = Math.round(v);
        return (double) tmp / factor;

    }






}
