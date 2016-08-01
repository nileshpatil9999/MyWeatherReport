package com.nilesh.myweatherreport.POJOClasss;

import android.databinding.BaseObservable;

import com.nilesh.myweatherreport.BR;


/**
 * Created by Nilesh on 7/23/2016.
 */
public class CurrentWeather extends BaseObservable {


    public Coord coord;

    public CurrentWeather(String name) {

        this.name = name;
    }


    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    private static class Coord {

        String lon;
        String lat;

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


    public Weather[] weather;


    public Weather[] getWeather() {
        return weather;
    }

    public void setWeather(Weather[] weather) {
        this.weather = weather;
    }

    private static class Weather {
        String id;
        String main;
        String description;
        String icon;

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


    String base;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Main main;


    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public static class Main extends BaseObservable {

        String temp;
        String pressure;
        String humidity;
        String temp_min;
        String temp_max;
        String sea_level;
        String grnd_level;

        public Main(String temp, String temp_min, String temp_max, String humidity) {

            this.temp = temp;
            this.temp_min = temp_min;
            this.temp_max = temp_max;
            this.humidity=humidity;


            //   notifyPropertyChanged(BR.temp);
        }


        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
            notifyPropertyChanged(BR.main);
        }

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
            notifyPropertyChanged(BR.main);
        }

        public String getTemp_min() {
            return temp_min;
        }

        public void setTemp_min(String temp_min) {
            this.temp_min = temp_min;
            notifyPropertyChanged(BR.main);
        }

        public String getTemp_max() {
            return temp_max;
        }

        public void setTemp_max(String temp_max) {
            this.temp_max = temp_max;
            notifyPropertyChanged(BR.main);
        }

        public String getSea_level() {
            return sea_level;
        }

        public void setSea_level(String sea_level) {
            this.sea_level = sea_level;
        }

        public String getGrnd_level() {
            return grnd_level;
        }

        public void setGrnd_level(String grnd_level) {
            this.grnd_level = grnd_level;
        }

    }


    public Wind wind;


    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    private static class Wind {

        String speed;
        String deg;

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
    }


    public Clouds clouds;


    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    private static class Clouds {
        String all;


        public String getAll() {
            return all;
        }

        public void setAll(String all) {
            this.all = all;
        }
    }


    String dt, id, name, cod;

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.currentweather);
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    Sys sys;

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public static class Sys extends BaseObservable {
        String message, country, sunrise, sunset;

        public Sys(String sunrise, String sunset) {


            this.sunrise = sunrise;
            this.sunset = sunset;


        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
            notifyPropertyChanged(BR.sys);

        }

        public String getSunrise() {
            return sunrise;
        }

        public void setSunrise(String sunrise) {
            this.sunrise = sunrise;
            notifyPropertyChanged(BR.sys);
        }

        public String getSunset() {
            return sunset;
        }

        public void setSunset(String sunset) {
            this.sunset = sunset;
            notifyPropertyChanged(BR.sys);
        }
    }


}
