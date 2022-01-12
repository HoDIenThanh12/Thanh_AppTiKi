package com.example.tiki.roomdatabase.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.tiki.roomdatabase.dao.LocationDAO;
import com.example.tiki.roomdatabase.entitys.Locations;

@Database(entities = {Locations.class}, version = 1)
public abstract class DataBaseLocation extends RoomDatabase {

//    static Migration migration_from_1to2 =new Migration(1,2) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("ALTER TABLE user ADD COLUMN _year TEXT");
//        }
//    };

    private static final String DATABASE_NAME  = "user.db";
    private static DataBaseLocation instance;



    public static synchronized DataBaseLocation getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(), DataBaseLocation.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    //.addMigrations(migration_from_1to2)
                    .build();
        }
        return instance;
    }
    public abstract LocationDAO loactionDAO();
}
