package com.example.roomdemo2.Adapter;

import android.app.Activity;
import android.app.Dialog;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdemo2.Model.BazarData;
import com.example.roomdemo2.Model.BazarDB;
import com.example.roomdemo2.R;
import com.example.roomdemo2.View.MainActivity;
import com.example.roomdemo2.ViewModel.BazarViewModel;

import java.util.ArrayList;
import java.util.List;

public class BazarAdapter extends RecyclerView.Adapter<BazarAdapter.MainViewHolder> {
    private List<BazarData> datalist=new ArrayList<>();
    private Activity context;
    BazarViewModel mViewModel=ViewModelProviders.of((MainActivity)context).get(BazarViewModel.class);

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_main
                , parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        BazarData currentData = datalist.get(position);
        holder.mTextView.setText(currentData.getText());

        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BazarData d = datalist.get(holder.getAdapterPosition());
                int sID = d.getID();
                String sText = d.getText();

                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_update);
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                int height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setLayout(width, height);
                dialog.show();

                final EditText editText = dialog.findViewById(R.id.edit_text);
                Button buttonUpdate = dialog.findViewById(R.id.btn_update);

                editText.setText(sText);

                buttonUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        String uText = editText.getText().toString().trim();
                mViewModel.u // MANA SHU YERDA ISHLATISHIM KERAK
//                        database.bazarDao().updateText(sID, uText);
//                        notify when data updated
//                        datalist.clear();
//                        datalist.addAll(database.bazarDao().getAllList());
//                        notifyDataSetChanged();
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
                        BazarData d = datalist.get(holder.getAdapterPosition());

//                        database.bazarDao().delete(d);
//                Notify when database is deleted
//                        int position = holder.getAdapterPosition();
//                        datalist.remove(position);
//                        notifyItemRemoved(position);
//                        notifyItemRangeChanged(position, datalist.size());
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

        int check = currentData.getIs_checked();
        if (check == 1)
            holder.mCheckBox.setChecked(true);
        else
            holder.mCheckBox.setChecked(false);

        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                BazarData d = datalist.get(holder.getAdapterPosition());
                int sID = d.getID();

                if (isChecked) {
                    Log.d("@@@", "1");
//                    database.bazarDao().updateCheckbox(sID, 1);
//                    datalist.clear();
//                    datalist.addAll(database.bazarDao().getAllList());

                } else {
                    Log.d("@@@", "0");
//                    database.bazarDao().updateCheckbox(sID, 0);
//                    datalist.clear();
//                    datalist.addAll(database.bazarDao().getAllList());

                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public void setBazar(List<BazarData> bazar) {
        this.datalist = bazar;
        notifyDataSetChanged();
    }

    public BazarData getBazarAt(int position) {
        return datalist.get(position);
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        ImageView btn_edit, btn_delete;
        CheckBox mCheckBox;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextView = itemView.findViewById(R.id.text_view);
            btn_edit = itemView.findViewById(R.id.btn_edit);
            btn_delete = itemView.findViewById(R.id.btn_delete);
            mCheckBox = itemView.findViewById(R.id.checkbox);
        }
    }
}
