package com.example.roomdemo2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

//Define dtable name
@Entity(tableName = "table_name")
public class MainData implements Serializable {
    //Create ID column
    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "is_checked")
    private int is_checked = 0;

    public int getIs_checked() {
        return is_checked;
    }

    public void setIs_checked(int is_checked) {
        this.is_checked = is_checked;
    }

    //    Create text column
    @ColumnInfo(name = "text")
    private String text;

//    Generate getter and setter


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
