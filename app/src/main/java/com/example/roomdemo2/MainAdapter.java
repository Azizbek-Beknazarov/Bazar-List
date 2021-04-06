package com.example.roomdemo2;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdemo2.ShowActivity.FAVList;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    //    Initialize variable
    private List<MainData> datalist;
    private Activity context;
    private RoomDB database;
//Create constructer


    public MainAdapter(Activity context, List<MainData> datalist) {
        this.datalist = datalist;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Initialize view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_main
                , parent, false);

        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {

//        Initialize main data
        MainData mainData = datalist.get(position);
//        Initialize database
        database = RoomDB.getInstance(context);
//        set text on textView
        holder.mTextView.setText(mainData.getText());

        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Initialize MainData
                MainData d = datalist.get(holder.getAdapterPosition());
//                get ID
                int sID = d.getID();
//                get text
                String sText = d.getText();
//                Create dialog
                Dialog dialog = new Dialog(context);
//                set content view
                dialog.setContentView(R.layout.dialog_update);
//                Initialize width
                int width = WindowManager.LayoutParams.MATCH_PARENT;
//                Initialize height
                int height = WindowManager.LayoutParams.WRAP_CONTENT;
//                set layout
                dialog.getWindow().setLayout(width, height);
                dialog.show();

//                Initialize and assign variables
                final EditText editText = dialog.findViewById(R.id.edit_text);
                Button buttonUpdate = dialog.findViewById(R.id.btn_update);

//                set text on edit Text
                editText.setText(sText);

                buttonUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        dialog dismiss
                        dialog.dismiss();
//                        get updated text from edit text
                        String uText = editText.getText().toString().trim();
//                        update text in database
                        database.mainDao().update(sID, uText);
//                        notify when data updated
                        datalist.clear();
                        datalist.addAll(database.mainDao().getAll());
                        notifyDataSetChanged();
                    }
                });
            }
        });

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.delete_dialog_item);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.show();

                Button yes = dialog.findViewById(R.id.yes);
                Button no = dialog.findViewById(R.id.no);

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
//                        Initialize MainData
                        MainData d = datalist.get(holder.getAdapterPosition());
//                delete text from database
                        database.mainDao().delete(d);
//                Notify when database is deleted
                        int position = holder.getAdapterPosition();
                        datalist.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, datalist.size());
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

        int check = mainData.getIs_checked();
        if (check == 1)
            holder.mCheckBox.setChecked(true);
        else
            holder.mCheckBox.setChecked(false);

        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                MainData d = datalist.get(holder.getAdapterPosition());
                int sID = d.getID();

                if (isChecked) {
                    Log.d("@@@", "1");
                    database.mainDao().updatefav(sID, 1);
                    datalist.clear();
                    datalist.addAll(database.mainDao().getAll());

                } else {
                    Log.d("@@@", "0");
                    database.mainDao().updatefav(sID, 0);
                    datalist.clear();
                    datalist.addAll(database.mainDao().getAll());

                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    //Main View Holder class
    public class MainViewHolder extends RecyclerView.ViewHolder {
        //    Initialize variable
        TextView mTextView;
        ImageView btn_edit, btn_delete;
        CheckBox mCheckBox;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
//            Assign variable
            mTextView = itemView.findViewById(R.id.text_view);
            btn_edit = itemView.findViewById(R.id.btn_edit);
            btn_delete = itemView.findViewById(R.id.btn_delete);
            mCheckBox = itemView.findViewById(R.id.checkbox);
        }
    }
}
