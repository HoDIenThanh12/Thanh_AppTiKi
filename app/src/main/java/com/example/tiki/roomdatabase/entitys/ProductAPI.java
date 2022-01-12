package com.example.tiki.roomdatabase.entitys;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "location_TK")
public class ProductAPI {
    @PrimaryKey(autoGenerate = true)
    private int _id;
    @ColumnInfo(name = "name_Product")
    private String _nameProduct;
    @ColumnInfo(name = "category_ID")
    private int _categoryId;
}
