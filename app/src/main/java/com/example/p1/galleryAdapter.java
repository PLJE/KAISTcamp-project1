package com.example.p1;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class galleryAdapter extends RecyclerView.Adapter<galleryAdapter.MyViewHolder> {
    private ArrayList<cats> catslist;

    public galleryAdapter(ArrayList<cats> catslist){
        this.catslist = catslist;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;

        public MyViewHolder(final View view){
            super(view);
            img = view.findViewById(R.id.imageView1);
        }
    }


    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public galleryAdapter.MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull galleryAdapter.MyViewHolder holder, int position) {
        Drawable cats = catslist.get(position).getCats();
        holder.img.setImageDrawable(cats);
    }

    @Override
    public int getItemCount() {
        return catslist.size();
    }
}
