package com.nilesh.myweatherreport;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nilesh.myweatherreport.POJOClasss.WeatherForeCast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nilesh on 7/26/2016.
 */
public class ForeCastAdapter  extends RecyclerView.Adapter<ForeCastAdapter.MyViewHolder>{




    private Context context;
    private List<WeatherForeCast.List> lobj;
    private List<WeatherForeCast.List.Temp> tobj;
    private List<WeatherForeCast.Dates> dates;




    public ForeCastAdapter(FragmentActivity activity, List<WeatherForeCast.List.Temp> tempobjlist, List<WeatherForeCast.List> listobjlist, List<WeatherForeCast.Dates> darr) {

        context=activity;
        lobj=listobjlist;
        tobj=tempobjlist;
        dates=darr;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.tempdetailsrow,parent,false);







        MyViewHolder holder=new MyViewHolder(v);
       return holder;



    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {




        final WeatherForeCast.Dates date=dates.get(position);
        final WeatherForeCast.List.Temp o = tobj.get(position);
        final WeatherForeCast.List o1=lobj.get(position);




        holder.getBinding().setVariable(com.nilesh.myweatherreport.BR.list,o1);
        holder.getBinding().setVariable(com.nilesh.myweatherreport.BR.temp,o);
        holder.getBinding().setVariable(com.nilesh.myweatherreport.BR.day,date);
        holder.getBinding().executePendingBindings();






    }

    @Override
    public int getItemCount() {
        return tobj.size();
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder{


        private ViewDataBinding binding;


        public ViewDataBinding getBinding() {
            return binding;
        }



        public MyViewHolder(View itemView) {
            super(itemView);
            binding= DataBindingUtil.bind(itemView);

        }







    }


    @BindingAdapter({"bind:font"})
    public static void setFont(TextView tv, String fontname)
   {


        tv.setTypeface(Typeface.createFromAsset(tv.getContext().getAssets(), "fonts/"+fontname));

    }

}
