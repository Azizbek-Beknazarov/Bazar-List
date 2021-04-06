package com.example.roomdemo2;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;


@Dao
public interface MainDao {
    //    Insert query
    @Insert(onConflict = REPLACE)
    void insert(MainData mainData);

    //Delete query
    @Delete
    void delete(MainData mainData);

    //    Delete all query
    @Delete
    void reset(List<MainData> mainData);

    //    Update query
    @Query("UPDATE table_name SET text =:sText WHERE ID=:sID")
    void update(int sID, String sText);

    @Query("UPDATE table_name SET is_checked =:check WHERE ID=:sID")
    void updatefav(int sID, int check);

    //    GEt all data query
    @Query("SELECT * FROM table_name")
    List<MainData> getAll();

    @Query("SELECT *FROM table_name WHERE is_checked=1")
    List<MainData> getAllFAV();
}