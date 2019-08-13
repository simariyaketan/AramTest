package com.solvers.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.databinding.DataBindingUtil;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;

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

    private TodayListAdaper todayListAdaper; //Recycleview List Adapter
    private ActivityMainBinding activityMainBinding;//Main Activity Bind variable
    private ArrayList<TodayList> todayListArrayList;// Today ArrayList
    public static int DETAIL_ACTVITY_REQUEST = 1;//Set Activity Result
    private ItemListTodayBinding itemListTodayBinding; //Layout Binding
    private SpringAnimation springAnim;//Spring Animation

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
        //set layout binding variable
        this.itemListTodayBinding = itemListTodayBinding;
        Intent tdi = new Intent(MainActivity.this, TodayDetailActivity.class);
        tdi.putExtra("todayListString",todayListString);
        Pair<View, String> p1 = Pair.create((View) itemListTodayBinding.layoutItemRowMain, "image");
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, p1);
        startActivityForResult(tdi, DETAIL_ACTVITY_REQUEST, options.toBundle());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DETAIL_ACTVITY_REQUEST) {
            /*Spring Animation*/
            if (itemListTodayBinding != null) {
                springAnim = new SpringAnimation(itemListTodayBinding.getRoot(), SpringAnimation.TRANSLATION_Y);
                SpringForce springForce = new SpringForce();
                springForce.setFinalPosition(-20f);
                springForce.setStiffness(SpringForce.STIFFNESS_VERY_LOW);
                springAnim.setSpring(springForce);
                springAnim.start();


                springAnim.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                    @Override
                    public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                        if (springAnim.getSpring().getFinalPosition() == -20f) {
                            SpringForce springForce = new SpringForce();
                            springForce.setFinalPosition(0f);
                            springForce.setStiffness(SpringForce.STIFFNESS_VERY_LOW);
                            springForce.setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);
                            springAnim.setSpring(springForce);
                            springAnim.start();
                        }
                    }
                });
            }
        }
    }
}
