package com.solvers.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;

import android.os.Build;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.util.DisplayMetrics;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.solvers.R;
import com.solvers.databinding.ActivityTodayDetailBinding;
import com.solvers.model.TodayList;

public class TodayDetailActivity extends AppCompatActivity {

    ActivityTodayDetailBinding activityTodayDetailBinding;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTodayDetailBinding = DataBindingUtil.setContentView(this,R.layout.activity_today_detail);

        //Set Transition Bound Animation
        ChangeBounds bounds = new ChangeBounds();
        bounds.setDuration(500);
        getWindow().setSharedElementEnterTransition(bounds);

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

        //Detail Page Close Click
        activityTodayDetailBinding.imgCloseDetailPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        ActivityCompat.finishAfterTransition(this);
    }
}
