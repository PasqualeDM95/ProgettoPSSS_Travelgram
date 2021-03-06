package com.psss.travelgram.view.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.psss.travelgram.R;
import com.psss.travelgram.view.activity.MemoryInfoActivity;

import java.util.ArrayList;


public class MemoryAdapter extends RecyclerView.Adapter<MemoryAdapter.MyViewHolder> {
    private ArrayList<String> imageLinks;
    private ArrayList<String> usernames;
    private ArrayList<String> countries;
    private ArrayList<String> memoryIDs;
    private Context context;


    // ---------- classe innestata ----------
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView username;
        public TextView country;

        // costruttore
        public MyViewHolder(View v) {
            super(v);
            image = v.findViewById(R.id.image);
            username = v.findViewById(R.id.username);
            country = v.findViewById(R.id.country);

            username.setVisibility(View.GONE);
            country.setVisibility(View.GONE);
        }
    }
    // ---------- fine classe innestata ----------


    // costruttore
    public MemoryAdapter(
            ArrayList<String> memoryIDs,
            ArrayList<String> imageLinks,
            ArrayList<String> usernames,
            ArrayList<String> countries,
            Context context) {
        this.imageLinks = imageLinks;
        this.memoryIDs  = memoryIDs;
        this.usernames  = usernames;
        this.countries  = countries;
        this.context    = context;
    }


    // creazione delle views
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.journal_item, parent, false);
        return new MyViewHolder(v);
    }


    // Riempimento di una singola view
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        if(usernames != null){
            holder.username.setVisibility(View.VISIBLE);
            holder.username.setText(usernames.get(position));
            holder.itemView.findViewById(R.id.username_shadow).setVisibility(View.VISIBLE);
        }

        if(countries != null){
            holder.country.setVisibility(View.VISIBLE);
            holder.country.setText(countries.get(position));
            holder.itemView.findViewById(R.id.country_shadow).setVisibility(View.VISIBLE);
        }

        // immagine
        Glide.with(context)
                .load(imageLinks.get(position))
                .apply(new RequestOptions().override(700))      // immagine a dimensione ridotta
                .thumbnail(0.2f)                                // thumbnail per il caricamento
                .into(holder.image);

        // il click sulla view apre le info della Memory
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MemoryInfoActivity.class);
                intent.putExtra("memID", memoryIDs.get(position));
                // se username è null vuol dire che la memory è del current User
                if(usernames == null)
                    intent.putExtra("isMine", true);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return imageLinks.size();
    }
}
