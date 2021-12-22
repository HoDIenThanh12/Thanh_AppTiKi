package com.example.tiki.module.entity;

import com.google.gson.annotations.SerializedName;

public class QuantitySold{

	@SerializedName("text")
	private String text;

	@SerializedName("value")
	private int value;

	public String getText(){
		return text;
	}

	public int getValue(){
		return value;
	}
}