package com.example.roomdemo2.Model;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;


@Dao
public interface BazarDao {

    @Insert(onConflict = REPLACE)
    void insert(BazarData bazarData);

    @Delete
    void delete(BazarData bazarData);

    @Query("DELETE FROM bazar_list")
    void reset();

    @Query("UPDATE bazar_list SET text =:sText WHERE ID=:sID")
    void updateText(int sID, String sText);

    @Query("UPDATE bazar_list SET is_checked =:check WHERE ID=:sID")
    void updateCheckbox(int sID, int check);

    @Query("SELECT * FROM bazar_list ORDER BY is_checked")
    LiveData<List<BazarData>> getAllList();

    @Query("SELECT *FROM bazar_list WHERE is_checked=1")
    List<BazarData> getAllChecked();
}