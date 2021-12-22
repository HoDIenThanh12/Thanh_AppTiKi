package com.example.tiki.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.tiki.R;
import com.example.tiki.adapper.AdapperDetailProduct;
import com.example.tiki.databinding.ActivityDetailTrendingProductBinding;
import com.example.tiki.databinding.ActivityViewProductBinding;
import com.example.tiki.db.ConvertBinding;
import com.example.tiki.module.entity.DataItem;
import com.example.tiki.module.entity.ItemsItem;
import com.example.tiki.module.entity.Response;
import com.example.tiki.retofits.RetrofitProduct;
import com.example.tiki.viewmodule.CategoryModel;
import com.example.tiki.viewmodule.DetailProductViewModel;
import com.example.tiki.viewmodule.DetailProductViewModelFactory;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class DetailTrendingProduct extends AppCompatActivity {
    private final String TAG=getClass().getSimpleName();
    private DetailProductViewModel detailProductViewModel;
    private AdapperDetailProduct adapperDetailProduct;
    private List<DataItem> listData=new ArrayList<>();
    private RecyclerView rcvDetailProduct;

    private ActivityDetailTrendingProductBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent =getIntent();
        Gson gson=new Gson();
        CategoryModel item=gson.fromJson(intent.getStringExtra("items"), CategoryModel.class);
        String url=intent.getStringExtra("urlBackground");
//        Log.d("đã convert", "->>> "+intent.getStringExtra("items"));
        Log.d("đã convert ", "background ->>> "+intent.getStringExtra("urlBackground"));
        DetailProductViewModelFactory factory = new DetailProductViewModelFactory(item);

        binding= DataBindingUtil.setContentView(this, R.layout.activity_detail_trending_product);

        GridLayoutManager linearLayoutManager = new GridLayoutManager(this,2);

        binding.rcvDetailProduct.setLayoutManager(linearLayoutManager);
        ConvertBinding.blurImageUrl(binding.imgbDetailProduct,url);
        detailProductViewModel=new ViewModelProvider(this, factory).get(DetailProductViewModel.class);

        detailProductViewModel.getmListLiveDataCategory().observe(this, new Observer<CategoryModel>() {
            @Override
            public void onChanged(CategoryModel dataItems) {
                detailProductViewModel.setDetailProductViewModelFactory(factory);
                if(dataItems==null)
                    Log.d(TAG,"sussec----> "+"id: "+dataItems.getCategoryId());
                else
                    Log.d(TAG,"sussec----> "+"toang rồi: ");
            }
        });
        detailProductViewModel.getmListLiveDataItems().observe(this, new Observer<List<DataItem>>() {
            @Override
            public void onChanged(List<DataItem> dataItems) {
                adapperDetailProduct =new AdapperDetailProduct(dataItems);
                binding.rcvDetailProduct.setAdapter(adapperDetailProduct);
            }
        });
    }
}