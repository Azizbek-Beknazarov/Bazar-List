package com.example.roomdemo2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdemo2.Model.BazarData;
import com.example.roomdemo2.R;

import java.util.List;

public class FAVAdapter extends RecyclerView.Adapter<FAVAdapter.ViewHolder> {
    private List<BazarData> mList;

    private Context mContext;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BazarData data = mList.get(position);
        holder.mShow.setText(data.getText());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mShow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mShow = itemView.findViewById(R.id.showtv);
        }
    }
}
