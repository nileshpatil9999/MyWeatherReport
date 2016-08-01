package com.nilesh.myweatherreport.POJOClasss;

/**
 * Created by Nilesh on 7/26/2016.
 */
public class ForecastObject {

    String temp;
    String mintemp;
    String maxtemp;
    String humidity;


    public ForecastObject(String mintemp, String maxtemp, String humidity, String temp) {
        this.mintemp = mintemp;
        this.maxtemp = maxtemp;
        this.humidity = humidity;
        this.temp = temp;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getMintemp() {
        return mintemp;
    }

    public void setMintemp(String mintemp) {
        this.mintemp = mintemp;
    }

    public String getMaxtemp() {
        return maxtemp;
    }

    public void setMaxtemp(String maxtemp) {
        this.maxtemp = maxtemp;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
}
