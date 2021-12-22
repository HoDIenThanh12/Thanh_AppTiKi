package com.example.tiki.module.entity;

import com.google.gson.annotations.SerializedName;

public class BadgesItem{

	@SerializedName("code")
	private String code;

	@SerializedName("month")
	private String month;

	@SerializedName("price")
	private int price;

	@SerializedName("text")
	private String text;

	public String getCode(){
		return code;
	}

	public String getMonth(){
		return month;
	}

	public int getPrice(){
		return price;
	}

	public String getText(){
		return text;
	}
}