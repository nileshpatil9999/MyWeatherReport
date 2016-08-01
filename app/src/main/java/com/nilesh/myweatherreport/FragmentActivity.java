package com.nilesh.myweatherreport;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import java.util.ArrayList;

/**
 * Created by Nilesh on 7/24/2016.
 */
public class FragmentActivity extends AppCompatActivity implements PlaceSelectionListener, ConnectivityReceiver.ConnectivityReceiverListener {

private static final int REQ_ALL_PERMISSIONS=100;
    FragmentManager fm = getSupportFragmentManager();
    int REQUEST_SELECT_PLACE = 0;
    String TAG = "searchresult:";
    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[] = {"Weather Now", "Weather Forecast"};
    int Numboftabs = 2;

    ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mainlayout);



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.statusbarcolor));

        }
        String[] Permissions=new  String[]{android.Manifest.permission.ACCESS_FINE_LOCATION};


        if(!haspermissions(this,Permissions))
        {
            ActivityCompat.requestPermissions(this,Permissions,REQ_ALL_PERMISSIONS);
        }
        else
        { toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setBackgroundColor(ContextCompat.getColor(this,R.color.toolbarcolor));
            toolbar.setTitleTextColor(ContextCompat.getColor(this,android.R.color.white));


            setSupportActionBar(toolbar);






            checkinternetconnection();


            fragments = new ArrayList<>();


            fragments.add(new Tab1());
            fragments.add(new Tab2());


            // Creating The Toolbar and setting it as the Toolbar for the activity


            // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
            adapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles, Numboftabs, fragments);

            // Assigning ViewPager View and setting the adapter
            pager = (ViewPager) findViewById(R.id.pager);
            pager.setAdapter(adapter);

            // Assiging the Sliding Tab Layout View
            tabs = (SlidingTabLayout) findViewById(R.id.tabs);
            tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

            // Setting Custom Color for the Scroll bar indicator of the Tab View
            tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
                @Override
                public int getIndicatorColor(int position) {
                    return getResources().getColor(R.color.wallet_holo_blue_light);
                }
            });

            // Setting the ViewPager For the SlidingTabsLayout
            tabs.setViewPager(pager);


            pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    pager.invalidate();
                    adapter.notifyDataSetChanged();

                }

                @Override
                public void onPageSelected(int position) {

                    pager.invalidate();
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onPageScrollStateChanged(int state) {


                    pager.invalidate();
                    adapter.notifyDataSetChanged();

                }
            });


        }


    }



    private boolean haspermissions(FragmentActivity fragmentActivity, String[] permissions) {


        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && fragmentActivity!=null && permissions!=null)
        {
            for(String permission:permissions)
            {
                if(ActivityCompat.checkSelfPermission(fragmentActivity,permission)!= PackageManager.PERMISSION_GRANTED)
                {
                    return false;
                }
            }
        }
        return true;

    }

    private void checkinternetconnection() {

        boolean isconnected = ConnectivityReceiver.isconnected();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainmenu, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_search) {
            // Method #3
            try {
                Intent intent = new PlaceAutocomplete.IntentBuilder
                        (PlaceAutocomplete.MODE_OVERLAY)

                        .build(FragmentActivity.this);
                startActivityForResult(intent, REQUEST_SELECT_PLACE);
            } catch (GooglePlayServicesRepairableException |
                    GooglePlayServicesNotAvailableException e) {
                e.printStackTrace();
            }
            return true;
        }else if(id==R.id.refresh)
        {





            Fragment fr1 = new Tab1();
            Fragment fr2 = new Tab2();

            fr1.setArguments(null);
            fr2.setArguments(null);
            fragments = new ArrayList<>();
            fragments.add(fr1);
            fragments.add(fr2);
            adapter = null;

            adapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles, Numboftabs, fragments);

            pager.setAdapter(adapter);

        }




        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPlaceSelected(Place place) {
        Log.i(TAG, "Place Selected: " + place.getName());
      //  Toast.makeText(getApplicationContext(), place.getName(), Toast.LENGTH_LONG).show();


        if (!TextUtils.isEmpty(place.getAttributions())) {

          //  Toast.makeText(getApplicationContext(), Html.fromHtml(place.getAttributions().toString()), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onError(Status status) {

        Log.e(TAG, "onError: Status = " + status.toString());
        Toast.makeText(this, "Place selection failed: " + status.getStatusMessage(),
                Toast.LENGTH_SHORT).show();

    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SELECT_PLACE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                this.onPlaceSelected(place);


                Bundle b = new Bundle();
                b.putCharSequence("city", place.getName());




                Fragment fr1 = new Tab1();
                Fragment fr2 = new Tab2();

                fr1.setArguments(b);
                fr2.setArguments(b);
                fragments = new ArrayList<>();
                fragments.add(fr1);
                fragments.add(fr2);
                adapter = null;

                adapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles, Numboftabs, fragments);

                pager.setAdapter(adapter);


            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                this.onError(status);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();


        MyApplication.getInstance().setConnectivityListener(this);

    }

    @Override
    public void onNetworkConnectionchanged(boolean isconnected) {

        if(!isconnected) {
            Toast.makeText(getApplicationContext(), "Internet connection is not available.", Toast.LENGTH_LONG).show();
        }else
        {
            Toast.makeText(getApplicationContext(), "Internet connection is  available.", Toast.LENGTH_LONG).show();
        }
    }
}