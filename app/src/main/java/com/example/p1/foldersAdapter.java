package com.example.p1;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class foldersAdapter extends RecyclerView.Adapter<foldersAdapter.MyViewHolder> {
    private ArrayList<folders> folderslist;
    private OnItemClickListener mListener;





    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public foldersAdapter(ArrayList<folders> folderslist){
        this.folderslist = folderslist;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView text;
        public MyViewHolder(final View view, OnItemClickListener listener){
            super(view);
            img = view.findViewById(R.id.folderImage);
            text = view.findViewById(R.id.folderText);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }


    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public foldersAdapter.MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_recycler, parent, false);
        return new MyViewHolder(itemView, mListener);

    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull foldersAdapter.MyViewHolder holder, int position) {
        Drawable ThumbNail = folderslist.get(position).getThumbNail();
        String folderName = folderslist.get(position).getFolderName();
        holder.img.setImageDrawable(ThumbNail);
        holder.text.setText(folderName);
    }

    @Override
    public int getItemCount() {
        return folderslist.size();
    }
}
