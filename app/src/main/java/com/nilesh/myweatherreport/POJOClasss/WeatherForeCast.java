package com.nilesh.myweatherreport.POJOClasss;

import android.databinding.BaseObservable;

import java.util.ArrayList;

import com.nilesh.myweatherreport.BR;

/**
 * Created by Nilesh on 7/22/2016.
 */
public class WeatherForeCast extends BaseObservable {




    public Dates date;


    public Dates getDate() {
        return date;
    }

    public void setDate(Dates date) {
        this.date = date;
    }

    public static class Dates
    {

        String day;

        public Dates(String s) {

            day=s;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }
    }

    public City city;


    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public  static class City extends BaseObservable
    {


        public String id;
        public String name;
        public Coord coord;



        public  class Coord
        {

            public String lat;
            public String lon;


            public Coord(String lon, String lat) {
                this.lon = lon;
                this.lat = lat;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLon() {
                return lon;
            }

            public void setLon(String lon) {
                this.lon = lon;
            }
        }





        public City(String s) {


            this.name = s;


        }

        public City(String id, String name,  String country, String population) {
            this.id = id;
            this.name = name;

            this.country = country;
            this.population = population;
        }



        public String country;
        public String population;


        public String getPopulation() {
            return population;
        }

        public void setPopulation(String population) {
            this.population = population;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;

            notifyPropertyChanged(BR.city);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }


    }


    String cod;


    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }




    String message;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }






    public List[] list;

    public static class List {


        String dt;

        public List(String humidity) {


            this.humidity=humidity;
        }


        public String getDt() {
            return dt;
        }

        public void setDt(String dt) {
            this.dt = dt;
        }
        public Temp temp;



        public  static class Temp
        {
            String day;
            String min;
            String max;
            String night;
            String eve;
            String morn;

            public Temp(String day, String min, String max ) {


                this.day=day;
                this.min=min;
                this.max=max;
            }

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }

            public String getMin() {
                return min;
            }

            public void setMin(String min) {
                this.min = min;
                //  notifyPropertyChanged(BR.temp);
            }

            public String getMax() {
                return max;
            }

            public void setMax(String max)
            {
                this.max = max;
                //  notifyPropertyChanged(BR.temp);
            }

            public String getNight() {
                return night;
            }

            public void setNight(String night) {
                this.night = night;
            }

            public String getEve() {
                return eve;
            }

            public void setEve(String eve) {
                this.eve = eve;
            }

            public String getMorn() {
                return morn;
            }

            public void setMorn(String morn) {
                this.morn = morn;
            }
        }





        String pressure;
        String humidity;




        public String getPressure() {
            return pressure;
        }

        public void setPressure(String pressure) {
            this.pressure = pressure;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;

        }


        public Weather[] weather;


        public  class Weather {

            String id, main, description, icon;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMain() {
                return main;
            }

            public void setMain(String main) {
                this.main = main;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }


        }

        String speed;
        String deg;
        String cloud;







        public String getSpeed() {
            return speed;
        }

        public void setSpeed(String speed) {
            this.speed = speed;
        }

        public String getDeg() {
            return deg;
        }

        public void setDeg(String deg) {
            this.deg = deg;
        }

        public String getCloud() {
            return cloud;
        }

        public void setCloud(String cloud) {
            this.cloud = cloud;
        }
    }


}
