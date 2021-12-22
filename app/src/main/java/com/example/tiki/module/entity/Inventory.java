package com.example.tiki.module.entity;

import com.google.gson.annotations.SerializedName;

public class Inventory{

	@SerializedName("fulfillment_type")
	private String fulfillmentType;

	public String getFulfillmentType(){
		return fulfillmentType;
	}
}