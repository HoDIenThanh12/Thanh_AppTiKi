package com.example.tiki.module.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MetaData{
	@SerializedName("type")
	private String type;

	@SerializedName("more_link_text")
	private String moreLinkText;

	@SerializedName("more_link")
	private String moreLink;

	@SerializedName("title")
	private String title;

	@SerializedName("sub_title")
	private String subTitle;

	@SerializedName("title_icon")
	private com.example.tiki.module.entity.TitleIcon titleIcon;

	@SerializedName("items")
	private List<ItemsItem> items;

	@SerializedName("background_image")
	private String backgroundImage;


	public MetaData() {
	}

	public String getBackgroundImage(){
		return backgroundImage;
	}

	public String getSubTitle(){
		return subTitle;
	}

	public String getMoreLink(){
		return moreLink;
	}

	public com.example.tiki.module.entity.TitleIcon getTitleIcon(){
		return titleIcon;
	}

	public String getType(){
		return type;
	}

	public String getTitle(){
		return title;
	}

	public String getMoreLinkText(){
		return moreLinkText;
	}

	public List<ItemsItem> getListItems(){
		return items;
	}
}