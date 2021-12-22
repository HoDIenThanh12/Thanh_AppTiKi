package com.example.tiki.adapper;

import static android.widget.Toast.makeText;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tiki.MainActivity;
import com.example.tiki.R;
import com.example.tiki.databinding.ItemCardProductBinding;
import com.example.tiki.module.entity.ItemsItem;
import com.example.tiki.module.entity.Response;
import com.example.tiki.module.service.ItemCnClick;
import com.example.tiki.view.DetailTrendingProduct;
import com.example.tiki.view.ViewProduct;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapperProduct extends RecyclerView.Adapter<AdapperProduct.AdapperProductViewHolder>  {

     private static Context mcontext;
     private List<ItemsItem> lItem;
     private ItemCnClick clickListener;

    public AdapperProduct(List<ItemsItem> lItem) {
        this.lItem = lItem;
    }

    public AdapperProduct(Context mcontext) {
        this.mcontext = mcontext;
    }
    public void setData(List<ItemsItem> l){
        this.lItem=l;
        notifyDataSetChanged();
    }

    public AdapperProduct(Context mcontext, List<ItemsItem> lItem, ItemCnClick clickListener) {
        this.mcontext = mcontext;
        this.lItem = lItem;
        this.clickListener = clickListener;
    }

    public AdapperProduct(Context mcontext, List<ItemsItem> lItem) {
        this.mcontext = mcontext;
        this.lItem = lItem;
    }

    public AdapperProduct(List<ItemsItem> lItem, ItemCnClick clickListener) {
        this.lItem = lItem;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public AdapperProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        ItemCardProductBinding binding = ItemCardProductBinding.inflate(layoutInflater, parent,false);
        return new AdapperProductViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull AdapperProductViewHolder holder, int position) {
        ItemsItem u =lItem.get(position);
        if(u!=null)
            holder.getBind(u);
    }

    @Override
    public int getItemCount() {
        if(lItem!=null){
            return lItem.size();
        }
        return 0;
    }

    public void release() {
        mcontext=null;
    }

    //khởi tạo class viewholder
    public class AdapperProductViewHolder extends RecyclerView.ViewHolder {
        private ItemCardProductBinding binding;

        public AdapperProductViewHolder(@NonNull ItemCardProductBinding itemCardProductBinding) {
            super(itemCardProductBinding.getRoot());
            this.binding=itemCardProductBinding;
        }
        public void getBind(ItemsItem itemsItem){
            this.binding.setShowtrend(itemsItem);
            this.binding.imgProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   clickListener.ItemClick(itemsItem);
                }
            });
        }
    }
}
