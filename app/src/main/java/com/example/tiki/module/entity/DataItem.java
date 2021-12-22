package com.example.tiki.module.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataItem{

	@SerializedName("original_price")
	private int originalPrice;

	@SerializedName("short_description")
	private String shortDescription;

	@SerializedName("rating_average")
	private double ratingAverage;

	@SerializedName("discount")
	private int discount;

	@SerializedName("review_count")
	private int reviewCount;

	@SerializedName("has_ebook")
	private boolean hasEbook;

	@SerializedName("inventory")
	private Inventory inventory;

	@SerializedName("thumbnail_url")
	private String thumbnailUrl;

	@SerializedName("type")
	private String type;

	@SerializedName("discount_rate")
	private int discountRate;

	@SerializedName("is_flower")
	private boolean isFlower;

	@SerializedName("url_attendant_input_form")
	private String urlAttendantInputForm;

	@SerializedName("inventory_status")
	private String inventoryStatus;

	@SerializedName("seller_product_id")
	private int sellerProductId;

	@SerializedName("quantity_sold")
	private QuantitySold quantitySold;

	@SerializedName("price")
	private int price;

	@SerializedName("option_color")
	private List<OptionColorItem> optionColor;

	@SerializedName("thumbnail_width")
	private int thumbnailWidth;

	@SerializedName("id")
	private int id;

	@SerializedName("installment_info")
	private InstallmentInfo installmentInfo;

	@SerializedName("salable_type")
	private String salableType;

	@SerializedName("sku")
	private String sku;

	@SerializedName("url_path")
	private String urlPath;

	@SerializedName("order_count")
	private int orderCount;

	@SerializedName("brand_name")
	private String brandName;

	@SerializedName("list_price")
	private int listPrice;

	@SerializedName("badges_new")
	private List<BadgesNewItem> badgesNew;

	@SerializedName("thumbnail_height")
	private int thumbnailHeight;

	@SerializedName("url_key")
	private String urlKey;

	@SerializedName("badges")
	private List<BadgesItem> badges;

	@SerializedName("stock_item")
	private StockItem stockItem;

	@SerializedName("favourite_count")
	private int favouriteCount;

	@SerializedName("name")
	private String name;

	@SerializedName("is_gift_card")
	private boolean isGiftCard;

	@SerializedName("productset_id")
	private int productsetId;

	@SerializedName("bundle_deal")
	private boolean bundleDeal;

	@SerializedName("video_url")
	private String videoUrl;

	@SerializedName("freegift_items")
	private List<FreegiftItemsItem> freegiftItems;

	public int getOriginalPrice(){
		return originalPrice;
	}

	public String getShortDescription(){
		return shortDescription;
	}

	public double getRatingAverage(){
		return ratingAverage;
	}

	public int getDiscount(){
		return discount;
	}

	public int getReviewCount(){
		return reviewCount;
	}

	public boolean isHasEbook(){
		return hasEbook;
	}

	public Inventory getInventory(){
		return inventory;
	}

	public String getThumbnailUrl(){
		return thumbnailUrl;
	}

	public String getType(){
		return type;
	}

	public int getDiscountRate(){
		return discountRate;
	}

	public boolean isIsFlower(){
		return isFlower;
	}

	public String getUrlAttendantInputForm(){
		return urlAttendantInputForm;
	}

	public String getInventoryStatus(){
		return inventoryStatus;
	}

	public int getSellerProductId(){
		return sellerProductId;
	}

	public QuantitySold getQuantitySold(){
		return quantitySold;
	}

	public int getPrice(){
		return price;
	}

	public List<OptionColorItem> getOptionColor(){
		return optionColor;
	}

	public int getThumbnailWidth(){
		return thumbnailWidth;
	}

	public int getId(){
		return id;
	}

	public InstallmentInfo getInstallmentInfo(){
		return installmentInfo;
	}

	public String getSalableType(){
		return salableType;
	}

	public String getSku(){
		return sku;
	}

	public String getUrlPath(){
		return urlPath;
	}

	public int getOrderCount(){
		return orderCount;
	}

	public String getBrandName(){
		return brandName;
	}

	public int getListPrice(){
		return listPrice;
	}

	public List<BadgesNewItem> getBadgesNew(){
		return badgesNew;
	}

	public int getThumbnailHeight(){
		return thumbnailHeight;
	}

	public String getUrlKey(){
		return urlKey;
	}

	public List<BadgesItem> getBadges(){
		return badges;
	}

	public StockItem getStockItem(){
		return stockItem;
	}

	public int getFavouriteCount(){
		return favouriteCount;
	}

	public String getName(){
		return name;
	}

	public boolean isIsGiftCard(){
		return isGiftCard;
	}

	public int getProductsetId(){
		return productsetId;
	}

	public boolean isBundleDeal(){
		return bundleDeal;
	}

	public String getVideoUrl(){
		return videoUrl;
	}

	public List<FreegiftItemsItem> getFreegiftItems(){
		return freegiftItems;
	}
}