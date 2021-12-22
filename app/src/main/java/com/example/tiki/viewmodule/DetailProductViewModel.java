package com.example.tiki.viewmodule;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

import com.example.tiki.module.entity.DataItem;
import com.example.tiki.module.entity.ItemsItem;
import com.example.tiki.module.entity.MetaData;
import com.example.tiki.module.entity.Response;
import com.example.tiki.retofits.RetrofitProduct;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class DetailProductViewModel  extends ViewModel {
    private final String TAG=getClass().getSimpleName();

    private MutableLiveData<CategoryModel> mListLiveDataCategory=new MutableLiveData<>();
    private MutableLiveData<List<DataItem>> mListLiveDataItems=new MutableLiveData<>();
    private List<DataItem> lDataItems;
    private MutableLiveData<MetaData> metaData = new MutableLiveData<>();
    private DetailProductViewModelFactory detailProductViewModelFactory;

    public  DetailProductViewModel(){
        super();
        mListLiveDataItems=new MutableLiveData<>();
        mListLiveDataCategory=new MutableLiveData<>();
    }

    public DetailProductViewModel(DetailProductViewModelFactory detailProductViewModelFactory) {
        this.detailProductViewModelFactory = detailProductViewModelFactory;
        Init(1);
        Log.d("khởi tạo có factory -->>> ","");
    }

    public  DetailProductViewModel(CategoryModel categoryModel){
        this.mListLiveDataCategory.setValue(categoryModel);
    }

    public DetailProductViewModel(MutableLiveData<CategoryModel> mListLiveDataCategory) {
        this.mListLiveDataCategory = mListLiveDataCategory;
    }

    public DetailProductViewModel(List<DataItem> lDataItems) {
        this.lDataItems = lDataItems;
    }



    public void Init(int id){
        MakeAPI(id);
    }
    public void loadData(int categoryID, int cusor, int limit){
        RetrofitProduct retrofitProduct= RetrofitProduct.getInstance();
    }
    public void MakeAPI(int id){

        RetrofitProduct retrofitProduct= RetrofitProduct.getInstance();
        Call<Response> call= retrofitProduct.getApiProduct().getProduct(id,0,50);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Response r=response.body();
                MetaData s=r.getAllData().getMetaData();
                Log.d(TAG,"sussces----> thành công! link: "+call.request());
                List<DataItem> di=r.getAllData().getData();
                setlDataItems(di);
                ganMuteableData(di,s);
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.d(TAG,"fail----> lõi!");
            }
        });

    }
    public void ganMuteableData(List<DataItem>l, MetaData s){
        setlDataItems(l);
        metaData.setValue(s);
        mListLiveDataItems.setValue(l);
    }

    public MutableLiveData<List<DataItem>> getmListLiveDataItems() {
        return mListLiveDataItems;
    }

    public void setmListLiveDataItems(MutableLiveData<List<DataItem>> mListLiveDataItems) {
        this.mListLiveDataItems = mListLiveDataItems;
    }

    public List<DataItem> getlDataItems() {
        return lDataItems;
    }


    public void setlDataItems(List<DataItem> lDataItems) {
        this.lDataItems = lDataItems;
    }

    public MutableLiveData<CategoryModel> getmListLiveDataCategory() {
        return mListLiveDataCategory;
    }

    public void setmListLiveDataCategory(MutableLiveData<CategoryModel> mListLiveDataCategory) {
        this.mListLiveDataCategory = mListLiveDataCategory;
    }

    public DetailProductViewModelFactory getDetailProductViewModelFactory() {
        return detailProductViewModelFactory;
    }

    public void setDetailProductViewModelFactory(DetailProductViewModelFactory detailProductViewModelFactory) {
        Init(detailProductViewModelFactory.getCategoryModel().getCategoryId());
        this.detailProductViewModelFactory = detailProductViewModelFactory;
    }

    public MutableLiveData<MetaData> getMetaData() {
        return metaData;
    }
}
