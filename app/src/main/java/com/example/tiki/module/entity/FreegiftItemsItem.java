package com.example.tiki.module.entity;

import com.google.gson.annotations.SerializedName;

public class FreegiftItemsItem{

	@SerializedName("masterId")
	private int masterId;

	@SerializedName("thumbnail")
	private String thumbnail;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	public int getMasterId(){
		return masterId;
	}

	public String getThumbnail(){
		return thumbnail;
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}
}