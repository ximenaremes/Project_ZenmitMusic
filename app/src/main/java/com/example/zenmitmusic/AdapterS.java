package com.example.zenmitmusic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterS extends RecyclerView.Adapter<AdapterS.ViewHolder>{

    private ArrayList<Music> songList;
    private AdapterS.RecyclerViewClickListener listener;

    public AdapterS(ArrayList<Music> songList, AdapterS.RecyclerViewClickListener listener) {
        this.songList = songList;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView imageView;
        private TextView title;
        private TextView artiste;

        public ViewHolder(@NonNull View itemView) {
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

    @NonNull
    @Override
    public AdapterS.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false );
        return new AdapterS.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Music mData = songList.get(position);
        holder.title.setText(mData.getTitle());
        holder.artiste.setText(mData.getArtiste());
        if (mData.getImageIcon2().isEmpty()) {
            holder.imageView.setImageResource(R.drawable.akcent);
        } else {
            Picasso.get().load(mData.getImageIcon2()).into(holder.imageView);
        }    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }
}


