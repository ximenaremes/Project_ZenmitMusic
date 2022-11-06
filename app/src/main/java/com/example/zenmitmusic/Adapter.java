package com.example.zenmitmusic;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public interface Adapter {
    @NonNull
    AdapterT.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType);

    void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position);

    void onBindViewHolder(@NonNull AdapterT.Viewholder viewholder, int position);

    int getItemCount();

    void filterList(ArrayList<Music> songList);
}
