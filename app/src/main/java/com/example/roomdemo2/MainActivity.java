package com.example.roomdemo2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.roomdemo2.ShowActivity.FAVList;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText mEditText;
    Button mAdd, mReset;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();

        List<MainData> listData;
        LinearLayoutManager linearLayoutManager;
        RoomDB database;
        MainAdapter adapter;

//        initialize database
        database = RoomDB.getInstance(this);
//        store database value in data list
        listData = database.mainDao().getAll();


//        Initialize linear layout manager
        linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
//        initialize adapter
        adapter = new MainAdapter(MainActivity.this, listData);
//        set adapter
        mRecyclerView.setAdapter(adapter);

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sText = mEditText.getText().toString().trim();
//                check condition
                if (!sText.equals("")) {

                    MainData mainData = new MainData();
                    mainData.setText(sText);
//                    insert text in database
                    database.mainDao().insert(mainData);
                    mEditText.setText("");
//                    notify when data is inserted
                    listData.clear();
                    listData.addAll(database.mainDao().getAll());
                    adapter.notifyDataSetChanged();
                }
            }
        });

        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                delete all data from database

                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.dialog_delete);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.show();

                Button yes = dialog.findViewById(R.id.yes);
                Button no = dialog.findViewById(R.id.no);

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        database.mainDao().reset(listData);
                        listData.clear();
                        listData.addAll(database.mainDao().getAll());
                        adapter.notifyDataSetChanged();
                    }
                });
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    public void setupUI() {
        mEditText = findViewById(R.id.edit_text);
        mEditText.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        mAdd = findViewById(R.id.btn_add);
        mReset = findViewById(R.id.btn_reset);
        mRecyclerView = findViewById(R.id.recycler_view);
    }

    public void goToFavList(View view) {
        startActivity(new Intent(MainActivity.this, FAVList.class));
    }
}