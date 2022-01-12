package com.example.tiki.roomdatabase.dao;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.tiki.roomdatabase.entitys.Locations;

@Dao
public interface LocationDAO {
    @Query("SELECT * FROM location_TK")
    Locations getLocations();

    @Insert(onConflict = REPLACE)
    void insertLocationTk(Locations locationTk);

    @Query("Delete from location_TK")
    void deleLocation();
}
