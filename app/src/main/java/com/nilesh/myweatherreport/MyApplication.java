package com.nilesh.myweatherreport;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by Nilesh on 30-07-2016.
 */
public class MyApplication extends Application {

    private static MyApplication myinstance;


    @Override
    public void onCreate() {
        super.onCreate();

        myinstance=this;
    }

    public static synchronized MyApplication getInstance()
    {

        return myinstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener)
    {
        ConnectivityReceiver.connectivityReceiverListener=listener;

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
