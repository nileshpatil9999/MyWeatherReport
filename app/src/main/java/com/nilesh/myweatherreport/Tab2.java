package com.nilesh.myweatherreport;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nilesh.myweatherreport.POJOClasss.WeatherForeCast;
import com.nilesh.myweatherreport.databinding.Fragment2Binding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nilesh on 7/24/2016.
 */
public class Tab2 extends Fragment {


    String latitude, longitude;
    GPSTracker tracker;

    Fragment2Binding binding;



    private RecyclerView recyclerview;
    private ForeCastAdapter adapter;

private WeatherForeCast.Dates dobj=null;
    private WeatherForeCast.List.Temp forcastobj = null;
    private WeatherForeCast.List listobj = null;
    List<WeatherForeCast.List> listobjlist = new ArrayList<>();
    List<WeatherForeCast.Dates> datesList=new ArrayList<>();

    List<WeatherForeCast.List.Temp> tempobjlist = new ArrayList<>();


    CharSequence cityname = null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

Log.v("inside" ,"oncreateview");

        if (getArguments() != null) {
            cityname = getArguments().getCharSequence("city");
            getWeatherForecast();
        }
        else
        {
            tracker = new GPSTracker(getActivity());

            if (tracker.isEnabled()) {


                double lat = tracker.getlatitude();
                double longi = tracker.getlongitude();
                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);

                getWeatherForecast();





            } else {

                tracker.showSettingsAlert();
            }

        }

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment2, container, false);

        View v = binding.getRoot();


        recyclerview = (RecyclerView) v.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager lmanager = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(lmanager);





        return v;
    }

    private void getWeatherForecast() {


        Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.openweathermap.org/").addConverterFactory(GsonConverterFactory.create(gson)).build();


        WeatherDetailsInterface api = retrofit.create(WeatherDetailsInterface.class);


        Call<WeatherForeCast> call = null;


        if (cityname != null) {

            call = api.getWeatherForeCast((String) cityname);
            Log.v("wethrforcast by citynm:", call.request().toString());
        } else {
            call = api.getWeatherForeCast(latitude, longitude);
            Log.v("wethrforecast by latlon",call.request().toString());
        }

        if (cityname != null || (latitude != null && longitude != null && !latitude.equals(0.0) && !longitude.equals(0.0))) {
            call.enqueue(new Callback<WeatherForeCast>() {
                @Override
                public void onResponse(Call<WeatherForeCast> call, Response<WeatherForeCast> response) {


                    WeatherForeCast.City cityname = new WeatherForeCast.City(response.body().city.getName());

                    binding.setCity(cityname);


                    Calendar d = Calendar.getInstance();

                    ArrayList<String> darr = new ArrayList<String>();
                    int j = 1;
                    while (j < 17) {

                        d.add(Calendar.DATE, -1);




                         dobj=new WeatherForeCast.Dates(String.valueOf(d.get(Calendar.DATE)) + "/" + String.valueOf(d.get(Calendar.MONTH)+1) + "/" + String.valueOf(d.get(Calendar.YEAR)));


                        datesList.add(dobj);
                        j++;
                    }


                    if (response.body().list != null) {
                        for (int i = 0; i < response.body().list.length; i++) {


                            forcastobj = new WeatherForeCast.List.Temp(response.body().list[i].temp.getDay(), response.body().list[i].temp.getMin(),
                                    response.body().list[i].temp.getMax());
                            listobj = new WeatherForeCast.List(response.body().list[i].getHumidity());


                            tempobjlist.add(forcastobj);
                            listobjlist.add(listobj);


                        }





                        adapter = new ForeCastAdapter(getActivity(), tempobjlist, listobjlist, datesList);

                        recyclerview.setAdapter(adapter);

                    }


                }

                @Override
                public void onFailure(Call<WeatherForeCast> call, Throwable t) {

                }
            });
        }


    }


}
