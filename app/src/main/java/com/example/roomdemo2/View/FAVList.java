package com.example.roomdemo2.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.roomdemo2.Adapter.FAVAdapter;
import com.example.roomdemo2.Model.BazarDB;
import com.example.roomdemo2.Model.BazarData;
import com.example.roomdemo2.R;

import java.util.List;

public class FAVList extends AppCompatActivity {
    private List<BazarData> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_a_v_list);

    }
}