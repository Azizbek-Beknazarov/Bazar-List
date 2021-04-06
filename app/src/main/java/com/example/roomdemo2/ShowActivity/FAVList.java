package com.example.roomdemo2.ShowActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.roomdemo2.MainData;
import com.example.roomdemo2.R;
import com.example.roomdemo2.RoomDB;

import java.util.List;

public class FAVList extends AppCompatActivity {
    private static RoomDB sRoomDB;
    private RecyclerView mRecyclerView;
    private List<MainData> mList;
    private FAVAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_a_v_list);

        sRoomDB=RoomDB.getInstance(this);
        mList=sRoomDB.mainDao().getAllFAV();
        mRecyclerView=findViewById(R.id.fav_rec);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setHasFixedSize(true);
        mAdapter=new FAVAdapter(mList,this);
        mRecyclerView.setAdapter(mAdapter);

    }
}