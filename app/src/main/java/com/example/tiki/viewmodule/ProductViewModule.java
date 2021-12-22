package com.example.tiki.viewmodule;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

import com.example.tiki.module.entity.ItemsItem;
import com.example.tiki.module.entity.MetaData;
import com.example.tiki.module.entity.Response;
import com.example.tiki.module.service.ApiProduct;
import com.example.tiki.retofits.RetrofitProduct;
import com.example.tiki.viewmodule.CategoryModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.http.PUT;

public class ProductViewModule extends ViewModel implements LifecycleObserver {
    private final String TAG=getClass().getSimpleName();
    private boolean isload=false;
    private MutableLiveData<MetaData> metaData ;
    private MutableLiveData<List<CategoryModel>> categories = new MutableLiveData<>();
//////////////////////////////////
    private MutableLiveData<List<ItemsItem>> mListLiveDataItems;
    private List<ItemsItem> lItems;

    public ProductViewModule() {
        super();
        mListLiveDataItems=new MutableLiveData<>();
        metaData=new MutableLiveData<>();
        InitData();
    }


    public MutableLiveData<List<ItemsItem>> getmListItems() {
        return mListLiveDataItems;
    }

    public List<ItemsItem> getlItems() {
        return lItems;
    }
    public void InitData(){
        MakeAPI();
        //get api
    }
    public void MakeAPI(){

        RetrofitProduct retrofitProduct = RetrofitProduct.getInstance();
        Call<Response> call = retrofitProduct.getApiProduct().getCategory(0,20);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Response r =response.body();
                List<ItemsItem> temp1 =r.getAllData().getMetaData().getListItems();
                setlItems(temp1);
                printProduct(r.getAllData().getMetaData().getListItems(),r.getAllData().getMetaData());
                isload=true;
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }
    public void printProduct(List<ItemsItem> ls, MetaData m){
        metaData.postValue(m);
        mListLiveDataItems.setValue(lItems);
        setIsload(true);
    }
    public MutableLiveData<List<CategoryModel>> getCategories() {
        return categories;
    }


    public MutableLiveData<List<ItemsItem>> getmListLiveDataItems() {
        return mListLiveDataItems;
    }



    public void setlItems(List<ItemsItem> lItems) {
        this.lItems = lItems;
    }

    public MutableLiveData<MetaData> getMetaData() {
        return metaData;
    }


    public boolean getIsload() {
        return isload;
    }

    public void setIsload(boolean isload) {
        this.isload = isload;
    }
}
