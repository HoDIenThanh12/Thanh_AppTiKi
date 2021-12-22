package com.example.tiki.retofits;


import com.example.tiki.module.service.ApiProduct;
import com.example.tiki.module.service.ApiTrendingProduct;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofits {
    private ApiProduct jsonAPI;

    private static Retrofits instance =null;

    private Retrofits(){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(jsonAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
                jsonAPI = retrofit.create(ApiProduct.class);

    }


    public static synchronized Retrofits getInstance() {
        if (instance == null) {
            instance = new Retrofits();
        }
        return instance;
    }

    public ApiProduct getJsonAPI() {
        return jsonAPI;
    }
}
