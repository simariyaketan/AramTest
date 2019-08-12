package com.solvers.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.solvers.R;
import com.solvers.adapter.TodayListAdaper;
import com.solvers.databinding.ActivityMainBinding;
import com.solvers.databinding.ItemListTodayBinding;
import com.solvers.model.TodayList;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TodayListAdaper.TodayListClickListner {

    TodayListAdaper todayListAdaper; //Recycleview List Adapter
    ActivityMainBinding activityMainBinding;//Main Activity Bind variable
    ArrayList<TodayList> todayListArrayList;// Today ArrayList

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        //Today model array
        todayListArrayList = new ArrayList<>();
        for(int ti = 0; ti < 10; ti++){
            TodayList todayList = new TodayList("http://cdn.focus-home.com/admin/games/fear_the_wolves/edition/standard/pc.png",
                    "Star Trek",

                    "Fleet Command",
                    "MMO Strategy Scifi Warfare");
            todayListArrayList.add(todayList);
        }

        todayListAdaper = new TodayListAdaper(this,todayListArrayList);
        todayListAdaper.setTodayListClickListner(this);
        activityMainBinding.recycleViewTodayList.setAdapter(todayListAdaper);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void TodayListDetailPage(int positoin, ItemListTodayBinding itemListTodayBinding) {
        TodayList todayList = todayListArrayList.get(positoin);
        Gson gson = new Gson();
        String todayListString = gson.toJson(todayList);

        Intent tdi = new Intent(MainActivity.this, TodayDetailActivity.class);
        tdi.putExtra("todayListString",todayListString);
        Pair<View, String> p1 = Pair.create((View) itemListTodayBinding.layoutItemRowMain, "image");
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, p1);
        startActivity(tdi, options.toBundle());
    }
}
