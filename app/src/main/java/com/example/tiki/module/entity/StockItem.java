package com.example.tiki.module.entity;

import com.google.gson.annotations.SerializedName;

public class StockItem{

	@SerializedName("max_sale_qty")
	private int maxSaleQty;

	@SerializedName("min_sale_qty")
	private int minSaleQty;

	@SerializedName("preorder_date")
	private boolean preorderDate;

	@SerializedName("qty")
	private int qty;

	public int getMaxSaleQty(){
		return maxSaleQty;
	}

	public int getMinSaleQty(){
		return minSaleQty;
	}

	public boolean isPreorderDate(){
		return preorderDate;
	}

	public int getQty(){
		return qty;
	}
}