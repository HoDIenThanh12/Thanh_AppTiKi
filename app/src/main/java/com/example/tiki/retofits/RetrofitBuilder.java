package com.example.tiki.retofits;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder <T> {
    private static String DEFAULT_URL = "https://api.tiki.vn/";

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(80, TimeUnit.MILLISECONDS)
            .readTimeout(80, TimeUnit.SECONDS)
            .build();

    public static <T> T getClient(Class<T> serviceClass) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(DEFAULT_URL)
                .client(okHttpClient)
                .build()
                .create(serviceClass);
    }
}