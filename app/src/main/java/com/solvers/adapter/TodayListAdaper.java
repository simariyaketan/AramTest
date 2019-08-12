package com.solvers.adapter;

import android.app.Activity;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.solvers.R;
import com.solvers.databinding.ItemListTodayBinding;
import com.solvers.model.TodayList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TodayListAdaper extends RecyclerView.Adapter<TodayListAdaper.TodayListViewHolder>
        implements View.OnClickListener  {

    //Item Click Listner
    TodayListClickListner todayListClickListner;

    DisplayMetrics displayMetrics = new DisplayMetrics();

    int height;//Get Device height
    Activity activity;
    ArrayList<TodayList> todayListArrayList;
    public TodayListAdaper(Activity activity,ArrayList<TodayList> todayListArrayList){
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        this.todayListArrayList = todayListArrayList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public TodayListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListTodayBinding itemListTodayBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_list_today,parent,false);
        TodayListViewHolder todayListViewHolder = new TodayListViewHolder(itemListTodayBinding);
        itemListTodayBinding.layoutItemRowMain.getLayoutParams().height = (int) (height * 0.65);
        itemListTodayBinding.layoutItemRowMain.setOnClickListener(this);
        return todayListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TodayListViewHolder todayListViewHolder, int position) {
        TodayList todayList = todayListArrayList.get(position);

        todayListViewHolder.itemListTodayBinding.layoutItemRowMain.setTag(todayListViewHolder);

        todayListViewHolder.bind(todayList);
    }

    @Override
    public int getItemCount() {
        return todayListArrayList.size();
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        TodayListViewHolder todayListViewHolder = (TodayListViewHolder) view.getTag();

        int position = todayListViewHolder.getLayoutPosition();
        if(viewId == R.id.layoutItemRowMain){
            if(todayListClickListner != null){
                todayListClickListner.TodayListDetailPage(position, todayListViewHolder.itemListTodayBinding);
            }
        }
    }

    public class TodayListViewHolder extends RecyclerView.ViewHolder {
        ItemListTodayBinding itemListTodayBinding;
        public TodayListViewHolder(ItemListTodayBinding itemListTodayBinding) {
            super(itemListTodayBinding.getRoot());
            this.itemListTodayBinding = itemListTodayBinding;
        }

        public void bind(TodayList todayList){
            itemListTodayBinding.setTodaylist(todayList);
        }
    }

    public void setTodayListClickListner(TodayListClickListner todayListClickListner){
        this.todayListClickListner = todayListClickListner;
    }

    /**
     *Item click listner interface
     */
    public interface TodayListClickListner {
        void TodayListDetailPage(int positoin,ItemListTodayBinding itemListTodayBinding);
    }
}
