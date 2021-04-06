package com.example.roomdemo2;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//Add database enteties
@Database(entities = {MainData.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    //Create database instance
    private static RoomDB database;
    //    Define database name
    private static String DATABASE_NAME = "database";

    public synchronized static RoomDB getInstance(Context context) {
//        Check condition
        if (database == null) {
            database = Room.databaseBuilder(context.getApplicationContext()
                    , RoomDB.class, DATABASE_NAME)
                    .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return database;
    }
//    Create Dao
    public abstract MainDao mainDao() ;
}
