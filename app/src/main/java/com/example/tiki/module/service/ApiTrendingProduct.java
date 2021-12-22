package com.example.tiki.module.service;



import com.example.tiki.module.entity.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiTrendingProduct {


    @GET("shopping-trend/api/trendings/hub?")
    Call<Response> getCategory(
            @Query("cursor") int cursor,
            @Query("limit") int limit);


    @GET("hub/category_id/{id}?")
    Call<Response> getProduct(
            @Path("id") int id,
            @Query("cursor") int cursor,
            @Query("limit") int limit
    );
}
