package com.nilesh.myweatherreport;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Nilesh on 30-07-2016.
 */
public class ConnectivityReceiver  extends BroadcastReceiver   {


   public static ConnectivityReceiverListener connectivityReceiverListener;


    public ConnectivityReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        ConnectivityManager cm=(ConnectivityManager)MyApplication.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo=cm.getActiveNetworkInfo();

        boolean isconnected=netinfo!=null && netinfo.isConnectedOrConnecting();

        if(connectivityReceiverListener!=null)
        {
            connectivityReceiverListener.onNetworkConnectionchanged(isconnected);
        }

    }

    public static boolean isconnected() {

        ConnectivityManager cm=(ConnectivityManager)MyApplication.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo=cm.getActiveNetworkInfo();

        return netinfo!=null && netinfo.isConnectedOrConnecting();



    }

    public interface ConnectivityReceiverListener
    {
        void onNetworkConnectionchanged(boolean isconnected);
    }


}
