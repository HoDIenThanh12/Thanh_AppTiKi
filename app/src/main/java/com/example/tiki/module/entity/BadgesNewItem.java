package com.example.tiki.module.entity;

import com.google.gson.annotations.SerializedName;

public class BadgesNewItem{

	@SerializedName("icon_width")
	private int iconWidth;

	@SerializedName("code")
	private String code;

	@SerializedName("icon_height")
	private int iconHeight;

	@SerializedName("icon")
	private String icon;

	@SerializedName("placement")
	private String placement;

	@SerializedName("text")
	private String text;

	@SerializedName("type")
	private String type;

	@SerializedName("text_color")
	private String textColor;

	public int getIconWidth(){
		return iconWidth;
	}

	public String getCode(){
		return code;
	}

	public int getIconHeight(){
		return iconHeight;
	}

	public String getIcon(){
		return icon;
	}

	public String getPlacement(){
		return placement;
	}

	public String getText(){
		return text;
	}

	public String getType(){
		return type;
	}

	public String getTextColor(){
		return textColor;
	}
}