package com.solvers.model;

import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.solvers.utils.Common;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

public class TodayList implements Serializable {

    String todayImage,title,subTitle,description;
    public TodayList(String todayImage, String title, String subTitle, String description){
        this.title = title;
        this.todayImage = todayImage;
        this.subTitle = subTitle;
        this.description = description;
    }

    public String getTodayImage() {
        return todayImage;
    }

    public void setTodayImage(String todayImage) {
        this.todayImage = todayImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @BindingAdapter({"android:src"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(Uri.parse(imageUrl))
                .resize(Common.getScreenWidth(),Common.getScreenWidth())
                .centerCrop()
                .into(view);
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadDetailImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .resize(Common.getScreenWidth(),Common.getScreenWidth())
                .centerCrop()
                .into(view);
    }
}
