package com.solvers.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.DisplayMetrics;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.solvers.R;
import com.solvers.databinding.ActivityTodayDetailBinding;
import com.solvers.model.TodayList;

public class TodayDetailActivity extends AppCompatActivity {

    ActivityTodayDetailBinding activityTodayDetailBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTodayDetailBinding = DataBindingUtil.setContentView(this,R.layout.activity_today_detail);

        //Get String from intent
        String todayListString = getIntent().getStringExtra("todayListString");
        Gson gson = new Gson();

        //String conver to object
        TypeToken<TodayList> token = new TypeToken<TodayList>() {};
        TodayList todayList = gson.fromJson(todayListString,token.getType());
        //set data in layout binding
        activityTodayDetailBinding.setTodaylist(todayList);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        activityTodayDetailBinding.layoutItemRowMain.getLayoutParams().height = (int) (height * 0.65);


    }
}
