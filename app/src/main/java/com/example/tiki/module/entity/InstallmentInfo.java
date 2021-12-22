package com.example.tiki.module.entity;

import com.google.gson.annotations.SerializedName;

public class InstallmentInfo{

	@SerializedName("month")
	private String month;

	@SerializedName("price")
	private int price;

	@SerializedName("redirect_url")
	private String redirectUrl;

	public String getMonth(){
		return month;
	}

	public int getPrice(){
		return price;
	}

	public String getRedirectUrl(){
		return redirectUrl;
	}
}