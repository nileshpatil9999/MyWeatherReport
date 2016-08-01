package com.nilesh.myweatherreport;

/**
 * Created by Nilesh on 27-07-2016.
 */
import android.content.Context;


 class MyContext {


    private static MyContext instance = null;





    private static Context appcontext;



    private MyContext(){
    }

  void  init(Context context){
        appcontext = context;

    }



    static Context getApplicationContext(){

        return appcontext;
    }


     public static MyContext getInstance() {

         if(instance == null)
             new MyContext();

         return instance;
     }
 }
