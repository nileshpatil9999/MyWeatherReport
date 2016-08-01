package com.nilesh.myweatherreport;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;

/**
 * Created by Nilesh on 30-07-2016.
 */
import android.app.Service;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.*;


public class GPSTracker extends Service implements LocationListener {

    private final Context context;
    boolean cangetlocation=false;

    private boolean isGPSenabled,isNetworkenabled;
    protected LocationManager lm;


    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters


    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute


    Location location;
    private double latitude,longitude;

    public GPSTracker(android.support.v4.app.FragmentActivity fragmentActivity) {

        context=fragmentActivity;

        getLocation();

    }

    public Location getLocation() {
try{
lm=(LocationManager)context.getSystemService(Context.LOCATION_SERVICE);


       isGPSenabled=lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
       isNetworkenabled= lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if(isGPSenabled) {
            cangetlocation=true;

            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

            if (lm != null) {
                location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (location != null) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();

                }
            }
        }else if(isNetworkenabled)
        {
            cangetlocation=true;
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

            if (lm != null) {
                location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if (location != null) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();

                }
            }
        }


}catch (Exception e)
{
    e.printStackTrace();
}
        return location;

    }


    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public boolean isEnabled() {

        return cangetlocation;



    }

    public double getlatitude() {

        if(location!=null)
        {
            latitude=location.getLatitude();
        }
        return latitude;
    }

    public double getlongitude() {

        if(location!=null)
        {
            longitude=location.getLongitude();
        }
        return longitude;
    }

    public void showSettingsAlert(){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);


        // Setting Dialog Title
        alertDialog.setTitle("GPS is settings");

        // Setting Dialog Message
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");

        // Setting Icon to Dialog
        //alertDialog.setIcon(R.drawable.delete);

        // On pressing Settings button
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        });

        // on pressing cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });

        // Showing Alert Message
        alertDialog.show();

    }
}
