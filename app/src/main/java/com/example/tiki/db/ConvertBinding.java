package com.example.tiki.db;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tiki.R;
import com.example.tiki.module.entity.BadgesItem;
import com.example.tiki.module.entity.BadgesNewItem;
import com.example.tiki.module.entity.DataItem;
import com.example.tiki.module.entity.ItemsItem;
import com.example.tiki.viewmodule.ProductViewModule;
import com.google.gson.Gson;
import com.jgabrielfreitas.core.BlurImageView;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ConvertBinding {
    @androidx.databinding.BindingAdapter("imgProductTrend")
    public static void imgProductTrend(ImageView image, String url) {
        Glide.with(image.getContext())
                .load(url)
                .placeholder(R.drawable.cho)
                .error(R.drawable.cho)
                .into(image);
    }
    @androidx.databinding.BindingAdapter("blurImageUrl")
    public static void blurImageUrl(BlurImageView image, String url) {
        Log.d("", "sussces----> link backgroung: " + url);
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                Bitmap bitmap = Glide.with(image.getContext())
                        .asBitmap()
                        .placeholder(R.drawable.cho)
                        .error(R.drawable.cho)
                        .load(url)
                        .submit().get();
                new Handler(Looper.getMainLooper()).post(() -> {
                    image.setImageBitmap(bitmap);
                    image.setBlur(10);
                });
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @androidx.databinding.BindingAdapter("isVisible")
    public static void setVisibility(View view, boolean isVisible) {
        if (isVisible) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.INVISIBLE);
        }
    }

    @androidx.databinding.BindingAdapter("ratingValue")
    public static void setRating(RatingBar view, String rating) {
        if (view != null) {
            float rate = Float.parseFloat(rating);
            view.setRating(rate);
        }
    }


    //@androidx.databinding.BindingAdapter("ItemClick")
    public static void ItemClick(ItemsItem i, Class nameClass, Context context, String url) {
        String x = new Gson().toJson(i);
        Intent intent = new Intent(context, nameClass);
        intent.putExtra("urlBackground",url);
        intent.putExtra("items", x);
        context.startActivity(intent);
    }
    public static void convertPrice(DataItem price, TextView tv, TextView tv1){
        NumberFormat numberFormat= NumberFormat.getCurrencyInstance();
        numberFormat.setMaximumFractionDigits(0);
        numberFormat.setCurrency(Currency.getInstance(new Locale("vi", "VN")));
        if(price.getPrice()>0) {
            tv.setTextColor(Color.RED);
            tv1.setText("  -"+price.getDiscountRate()+"%");
        }
        else
        {
            tv.setTextColor(Color.BLACK);
            tv1.setText("  -"+price.getDiscountRate()+"%");

        }
        tv.setText(numberFormat.format(price.getPrice())+ "  ");
    }

    public static void Evaluate (RatingBar ra, String s){
        if(ra!=null){
            float rate=Float.parseFloat(s);
            ra.setRating(rate);
        }
    }
    public static void convertBadgeNew(List<BadgesNewItem> badgesNewItem, ImageView imgBadge, ImageView imgBadgePrice){
        boolean isTikiNow=false;
        boolean isPrice =false;
        //Log.d("code","-->>>: "+badgesNewItem.get(0).getCode());
        for(BadgesNewItem i : badgesNewItem){
            //Log.d("code","nhảy số -->>>: "+i.getIcon());
            if(i.getCode().equalsIgnoreCase("tikinow")){
                String url=i.getIcon();
                ConvertBinding.imgProductTrend(imgBadge, url);
                isTikiNow=true;
            }
            else{
                if(i.getCode().equalsIgnoreCase("is_best_price_guaranteed")){
                    String url=i.getIcon();
                    ConvertBinding.imgProductTrend(imgBadgePrice, url);
                    isPrice=true;
                }
            }
        }
        if(!isPrice)
            imgBadgePrice.setVisibility(View.VISIBLE);
        if(!isTikiNow)
            imgBadge.setVisibility(View.VISIBLE);
    }
}
