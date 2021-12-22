package com.example.tiki.module.entity;

import com.google.gson.annotations.SerializedName;

public class OptionColorItem{

	@SerializedName("original_price")
	private int originalPrice;

	@SerializedName("thumbnail")
	private String thumbnail;

	@SerializedName("small_thumbnail")
	private String smallThumbnail;

	@SerializedName("price")
	private int price;

	@SerializedName("list_price")
	private int listPrice;

	@SerializedName("display_name")
	private String displayName;

	@SerializedName("is_default")
	private int isDefault;

	@SerializedName("spid")
	private int spid;

	@SerializedName("url_path")
	private String urlPath;

	public int getOriginalPrice(){
		return originalPrice;
	}

	public String getThumbnail(){
		return thumbnail;
	}

	public String getSmallThumbnail(){
		return smallThumbnail;
	}

	public int getPrice(){
		return price;
	}

	public int getListPrice(){
		return listPrice;
	}

	public String getDisplayName(){
		return displayName;
	}

	public int getIsDefault(){
		return isDefault;
	}

	public int getSpid(){
		return spid;
	}

	public String getUrlPath(){
		return urlPath;
	}
}