package com.example.tiki.module.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemsItem{

	@SerializedName("category_id")
	private int categoryId;

	@SerializedName("images")
	private List<String> images;

//	@SerializedName("images")
//	private String images2;



	@SerializedName("title")
	private String title;


//	public String getImages2() {
//		return images2;
//	}
//
//	public void setImages2(String images2) {
//		this.images2 = images2;
//	}

//	public ItemsItem(String images2, int categoryId, String title) {
//		this.images2 = images2;
//		this.categoryId = categoryId;
//		this.title = title;
//	}

	public ItemsItem(int categoryId, String title) {
		this.categoryId = categoryId;
		this.title = title;
	}

	public ItemsItem(List<String> images, int categoryId, String title) {
		this.images = images;
		this.categoryId = categoryId;
		this.title = title;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getImages(){
		return images;
	}

	public int getCategoryId(){
		return categoryId;
	}

	public String getTitle(){
		return title;
	}

	@Override
	public String toString() {
		return "ItemsItem{" +
				"images=" + images +
				", categoryId=" + categoryId +
				", title='" + title + '\'' +
				'}';
	}
}