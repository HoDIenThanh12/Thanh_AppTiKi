package com.example.tiki.module.entity;

import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("data")
    private Data data;

    @SerializedName("status")
    private int status;

    public Data getAllData(){
        return data;
    }

    public int getStatus(){
        return status;
    }
}
