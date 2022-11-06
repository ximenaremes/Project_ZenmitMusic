package com.example.zenmitmusic;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterT extends RecyclerView.Adapter<AdapterT.Viewholder> {

    private final RecyclerViewClickListener listener;
    private ArrayList<Music> songList;

    AdapterT(ArrayList<Music> songList, RecyclerViewClickListener listener) {
        this.songList = songList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.items, viewGroup,false );
        return new Viewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int position) {
        int resource = songList.get(position).getImageIcon();
        String title = songList.get(position).getTitle();
        String artiste = songList.get(position).getArtiste();
        viewholder.setData(resource, title, artiste);
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ImageView imageView;
        private final TextView title;
        private final TextView artiste;

        Viewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.songTitle);
            artiste = itemView.findViewById(R.id.artisteName);
            itemView.setOnClickListener(this);
        }

        private void setData(int imageResource, String songTitle, String artisteName ) {
            imageView.setImageResource(imageResource);
            title.setText(songTitle);
            artiste.setText(artisteName);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(itemView, getAdapterPosition());
        }
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }


    public void filterList(ArrayList<Music> songList){
        this.songList = songList;
        notifyDataSetChanged();
    }


}
