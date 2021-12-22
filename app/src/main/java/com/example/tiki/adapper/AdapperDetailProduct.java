package com.example.tiki.adapper;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiki.R;
import com.example.tiki.databinding.ItemDetailTrendingProductBinding;
import com.example.tiki.db.ConvertBinding;
import com.example.tiki.module.entity.Data;
import com.example.tiki.module.entity.DataItem;
import com.example.tiki.module.entity.ItemsItem;
import com.example.tiki.module.service.ItemCnClick;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class AdapperDetailProduct extends RecyclerView.Adapter<AdapperDetailProduct.AdapperDetailProductViewHolder> {

    private static Context mcontext;
    private List<DataItem> lDataItem;
    private ItemCnClick clickListener;
    private int id;

    public AdapperDetailProduct(Context m, List<DataItem>l)
    {
        this.lDataItem=l;
        mcontext=m;
    }

    public AdapperDetailProduct(List<DataItem> lDataItem) {
        this.lDataItem = lDataItem;
    }

    @NonNull
    @Override
    public AdapperDetailProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(parent.getContext());
        ItemDetailTrendingProductBinding itemdetailbinding= ItemDetailTrendingProductBinding.inflate(layoutInflater,parent,false);
        return new AdapperDetailProductViewHolder(itemdetailbinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapperDetailProductViewHolder holder, int position) {
        DataItem t=lDataItem.get(position);
        if(t!=null)
            holder.getBinding(t);
    }

    @Override
    public int getItemCount() {
        if(lDataItem!=null)
            return lDataItem.size();
        return 0;
    }



    public class AdapperDetailProductViewHolder extends RecyclerView.ViewHolder {
            private  ItemDetailTrendingProductBinding binding;
        public AdapperDetailProductViewHolder(@NonNull ItemDetailTrendingProductBinding itemdetailbinding) {
            super(itemdetailbinding.getRoot());
            this.binding=itemdetailbinding;
        }
        public void getBinding(DataItem dataItem){
            this.binding.setDetailProduct(dataItem);
            ConvertBinding.convertPrice(dataItem, this.binding.priceDetailProduct, this.binding.giamgiaDetailProduct);
            ConvertBinding.Evaluate(this.binding.dgiaDetailProduct,String.valueOf(dataItem.getRatingAverage()));
            ConvertBinding.convertBadgeNew(dataItem.getBadgesNew(), this.binding.imgBadge, this.binding.imgBadgePrice);
            this.binding.crard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(binding.crard.getContext(), ""+dataItem.getName(),Toast.LENGTH_LONG).show();
                    //Log.d("nhấn cardview","--->>>" +dataItem.getName() );
                }
            });
        }
    }

    public static Context getMcontext() {
        return mcontext;
    }

    public static void setMcontext(Context mcontext) {
        AdapperDetailProduct.mcontext = mcontext;
    }

    public List<DataItem> getlDataItem() {
        return lDataItem;
    }

    public void setlDataItem(List<DataItem> lDataItem) {
        this.lDataItem = lDataItem;
    }
    public void loadImageURL(ImageView img, String url){
        Picasso.get().load(url+"").into(img);
    }

}


