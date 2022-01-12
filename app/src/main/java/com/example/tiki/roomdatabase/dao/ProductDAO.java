package com.example.tiki.roomdatabase.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.tiki.roomdatabase.entitys.ProductAPI;

import java.util.List;

@Dao
public interface ProductDAO {
    @Insert()
    public void InsertDataAPI();

    @Query("SELECT * FROM Locations")
    List<ProductAPI> getAllProductAPI();

}
