package com.example.tiki.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.tiki.R;
import com.example.tiki.adapper.AdapperProduct;
import com.example.tiki.databinding.ActivityViewProductBinding;
import com.example.tiki.db.ConvertBinding;
import com.example.tiki.module.entity.ItemsItem;
import com.example.tiki.module.entity.MetaData;
import com.example.tiki.module.service.ItemCnClick;
import com.example.tiki.viewmodule.ProductViewModule;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ViewProduct extends AppCompatActivity {
    private final String TAG=getClass().getSimpleName();
    private MutableLiveData<MetaData> metaData = new MutableLiveData<>();
    private List<ItemsItem> lItems= new ArrayList<>();

    private AdapperProduct adapperProduct;
    private ProductViewModule productViewModule;
    private boolean temp=false;
    private ConvertBinding convertBinding;
    private ActivityViewProductBinding binding;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_product);
        productViewModule =new ViewModelProvider(this).get(ProductViewModule.class);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        binding.rcvProduct.setLayoutManager(linearLayoutManager);
        binding.setLifecycleOwner(this);
        this.getLifecycle().addObserver(productViewModule);
        productViewModule=new ViewModelProvider(this).get(ProductViewModule.class);
        productViewModule.getmListItems().observe(this, new Observer<List<ItemsItem>>() {
            @Override
            public void onChanged(List<ItemsItem> itemsItems) {
                adapperProduct =new AdapperProduct(itemsItems, new ItemCnClick() {
                    @Override
                    public void ItemClick(ItemsItem i) {
                        convertBinding.ItemClick(i, DetailTrendingProduct.class , ViewProduct.this, productViewModule.getMetaData().getValue().getBackgroundImage());
                    }
                });
                binding.rcvProduct.setAdapter(adapperProduct);
            }
        });
        productViewModule.getMetaData().observe(this, new Observer<MetaData>() {
            @Override
            public void onChanged(MetaData metaData) {
                if(metaData!=null){
                    ConvertBinding.blurImageUrl(binding.imageViewBackground, metaData.getBackgroundImage());
                    String title=metaData.getTitle();
                    binding.tv.setText(title);
                    //Log.d("isload-->>"," "+productViewModule.getIsload());
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(adapperProduct!=null)
            adapperProduct.release();

    }
}