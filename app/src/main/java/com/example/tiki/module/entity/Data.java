package com.example.tiki.module.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("next_page")
    private String nextPage;

    @SerializedName("data")
    private List<DataItem> data;

    @SerializedName("meta_data")
    private MetaData metaData;

    public String getNextPage(){
        return nextPage;
    }

    public List<DataItem> getData(){
        return data;
    }

    public MetaData getMetaData(){
        return metaData;
    }
}
