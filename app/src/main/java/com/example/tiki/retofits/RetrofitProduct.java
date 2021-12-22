package com.example.tiki.retofits;

import com.example.tiki.module.service.ApiProduct;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitProduct {
    private ApiProduct api;

    private static RetrofitProduct instance = null;

    private RetrofitProduct (){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
                api = retrofit.create(ApiProduct.class);
    }

    public static synchronized RetrofitProduct getInstance() {
        if (instance == null) {
            instance = new RetrofitProduct();
        }
        return instance;
    }

    public ApiProduct getApiProduct() {
        return api;
    }
}
