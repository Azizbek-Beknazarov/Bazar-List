package com.example.roomdemo2.ShowActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.roomdemo2.MainData;
import com.example.roomdemo2.R;
import com.example.roomdemo2.RoomDB;

import java.util.List;

public class FAVAdapter extends RecyclerView.Adapter<FAVAdapter.ViewHolder> {
    private List<MainData> mList;

    private Context mContext;

    public FAVAdapter(List<MainData> list, Context context) {
        mList = list;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MainData data = mList.get(position);
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
